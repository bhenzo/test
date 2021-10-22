package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Mate;
import com.example.demo.services.PDFService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class MainRestController {

	private PDFService pdfsrv;

	public MainRestController(@Qualifier("PDFServiceJasperImpl") PDFService pdfsrv) {
		this.pdfsrv = pdfsrv;
	}

	
	public ResponseEntity<String> testFallback(Throwable t){
		return new ResponseEntity<String>("Test down", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/generate")
	public ResponseEntity<byte[]> generate() {

		byte[] contents = null;
		try {
			contents = pdfsrv.generatePDF("test");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Report size: " + contents.length);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
	
		String filename = "output.pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		return response;

	}
	
}
