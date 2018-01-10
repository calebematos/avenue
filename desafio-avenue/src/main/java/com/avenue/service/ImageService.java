package com.avenue.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.avenue.model.Image;
import com.avenue.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

	@Value("${system.tmp-dir}")
	private String tmpDir;

	public Image saveImage(MultipartFile upload) {
		Image image = new Image();
		try {
			String name = upload.getOriginalFilename();
			File folder = getFolder();
			String path = saveImageInFolder(folder, upload);
			image.setName(name);
			image.setPath(path);
			return imageRepository.save(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Image updateProduct(Long imageId, Image image) {
		Image imageSaved = imageRepository.findById(imageId);
		BeanUtils.copyProperties(image, imageSaved, "id");
		return imageRepository.save(imageSaved);
	}

	public void remove(Long imageId) {
		imageRepository.delete(imageId);
	}

	private String saveImageInFolder(File path, MultipartFile upload) throws IOException {
		String fileName = "dimensionamento_qlp_temp_" + System.currentTimeMillis();
		File file = new File(path, fileName + ".");

		try (OutputStream os = Files.newOutputStream(file.toPath())) {
			IOUtils.copyLarge(upload.getInputStream(), os);
			os.flush();
		}
		return file.getAbsolutePath();
	}

	private File getFolder() {
		File path = new File(tmpDir);
		if (!path.exists())
			path.mkdir();

		return path;
	}
}
