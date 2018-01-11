package com.avenue.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.avenue.model.Image;
import com.avenue.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

	@Value("${system.tmp-dir}")
	private String tmpDir;

	public Image saveImage(InputStream file, FormDataContentDisposition fileDetail) {

		Image image = new Image();
		try {
			String name = fileDetail.getFileName();
			File folder = getFolder();
			String path = saveImageInFolder(folder, file, name);
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

	private String saveImageInFolder(File path, InputStream upload, String name) throws IOException {
		File file = new File(path, name);

		try (OutputStream os = Files.newOutputStream(file.toPath())) {
			IOUtils.copyLarge(upload, os);
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
