package com.avenue.resource;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
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

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response saveImage(@FormDataParam("file") InputStream file,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @Context HttpServletResponse response) {
		Image newImage = imageService.saveImage(file, fileDetail);
		if (newImage != null) {
			publisher.publishEvent(new NewResourceEvent(this, response, newImage.getId()));
			return Response.status(Status.CREATED).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	@Path("/upload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream file,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		System.out.println("teste");

		return null;
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
