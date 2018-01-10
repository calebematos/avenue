package com.avenue.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.avenue.event.NewResourceEvent;

public class NewResourceListener implements ApplicationListener<NewResourceEvent>{

	@Override
	public void onApplicationEvent(NewResourceEvent event) {
		
		HttpServletResponse response = event.getResponse();
		Long code = event.getCode();
		
		adicionarHeaderLocation(response, code);
	}

	private void adicionarHeaderLocation(HttpServletResponse response, Long code) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}")
				.buildAndExpand(code).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}




}
