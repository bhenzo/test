package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.PDFService;

@RestController
public class MainRestController {

	private PDFService pdfsrv;
	private Logger log = LoggerFactory.getLogger(MainRestController.class);

	public MainRestController(@Qualifier("PDFServiceJasperImpl") PDFService pdfsrv) {
		this.pdfsrv = pdfsrv;
	}

	@GetMapping("/")
	public ResponseEntity<String> home() {
		log.debug("Logeando un mensaje");
		log.info("Logeando un INFO");
		log.error("Logeando un error");
		return ResponseEntity.ok("Hola 3!!");
	}
	
	@GetMapping("/tetsbranch")
	public ResponseEntity<String> testBranch() {
		log.debug("Logeando un mensaje desde test branch");
	
		return ResponseEntity.ok("Hola 4!!");
	}

	@GetMapping("/test")
	public ResponseEntity<String> test() {
		log.info("func: test()");
		
		try {
			byte[] contents = pdfsrv.generatePDF("test");
			log.info("PDF generado, tamaño: {}", contents.length);
		} catch (Exception e) {
			log.error("Error al generar PDF: {}", e.getMessage());
			e.printStackTrace();
		}
		
		return ResponseEntity.ok("Hola 2!!");
	}

	@GetMapping("/generate")
	public ResponseEntity<byte[]> generate() {
		log.info("Ejecutando GENERATE");
		try {
			byte[] contents = null;

			contents = pdfsrv.generatePDF("test");

			System.out.println("Report size: " + contents.length);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);

			String filename = "output.pdf";
			headers.setContentDispositionFormData(filename, filename);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			log.error("Se produjo un ERROR: {}", e.getMessage());
			e.printStackTrace();
		}
		return null;

	}

}
