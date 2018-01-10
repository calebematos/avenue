package com.avenue.resource;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.avenue.event.NewResourceEvent;
import com.avenue.model.Image;
import com.avenue.service.ImageService;

@Component
@Path("/images")
@Produces(MediaType.APPLICATION_JSON)
public class ImageResource {

	@Autowired
	private ImageService imageService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@POST
	@Transactional
	public Response saveImage(@RequestParam("file") MultipartFile upload, @Context HttpServletResponse response) {
		Image newImage = imageService.saveImage(upload);
		if (newImage != null) {
			publisher.publishEvent(new NewResourceEvent(this, response, newImage.getId()));
			return Response.status(Status.CREATED).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	@PUT
	@Transactional
	@Path("/{id}")
	public Response updateProduct(@PathParam("id") Long imageId, Image image) {
		Image updatedImage = imageService.updateProduct(imageId, image);
		return Response.ok(updatedImage).build();
	}

	@DELETE
	@Transactional
	@Path("/{id}")
	public Response deleteProduct(@PathParam("id") Long imageId) {
		imageService.remove(imageId);
		return Response.noContent().build();
	}

}
