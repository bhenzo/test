package com.example.demo.services;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
@Qualifier("PDFServiceJasperImpl")
public class PDFServiceImpl implements PDFService {

	@Override
	public byte[] generatePDF(String data) throws Exception {

		// InputStream is = new ClassPathResource("report.jrxml").getInputStream();
		//InputStream is = new ClassPathResource("report.jrxml").getInputStream();
//		InputStream issub = new ClassPathResource("subreport.jrxml").getInputStream();
		// Create a document and add a page to it
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage( page );

		// Create a new font object selecting one of the PDF base fonts
		PDFont font = PDType1Font.HELVETICA_BOLD;

		// Start a new content stream which will "hold" the to be created content
		PDPageContentStream contentStream = new PDPageContentStream(document, page);

		// Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
		contentStream.beginText();
		contentStream.setFont( font, 12 );
		contentStream.newLineAtOffset( 100, 700 );
		contentStream.showText("HOLA HOLA MANOLA");
		contentStream.endText();

		// Make sure that the content stream is closed:
		contentStream.close();

		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		document.save(bout);
		document.close();
		byte[] respBytes = bout.toByteArray();
		// Save the results and ensure that the document is properly closed:
		return respBytes;
	}

}
