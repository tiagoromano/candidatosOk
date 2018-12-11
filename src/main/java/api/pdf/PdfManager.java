package api.pdf;

import java.io.File;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.*;

import cronapi.CronapiMetaData;
import cronapi.CronapiMetaData.CategoryType;
import cronapi.CronapiMetaData.ObjectType;
import cronapi.Var;

/**
 * Classe que representa ...
 * 
 * @author tiago.romano@techne.com.br tiago.romano@techne.com.br
 * @version 1.0
 * @since 2018-12-10
 *
 */
 
@CronapiMetaData(category = CategoryType.CONVERSION, categoryTags = { "Conversão", "Convert" })
public class PdfManager {

	@CronapiMetaData(type = "function", name = "Converter PDF para Texto", nameTags = {
			"PdfToText" }, description = "Converter PDF para Texto", params = {
					"Pdf para converter" }, paramsType = { ObjectType.UNKNOWN }, returnType = ObjectType.STRING)
	public static Var byteToText(Var content) throws Exception {	

		String parsedText = "";
		try {
			byte[] byteArray = content.getObjectAsByteArray();
			if (byteArray != null) {
				try (RandomAccessRead randomAccessRead = (RandomAccessRead) new RandomAccessBuffer(content.getObjectAsByteArray())) {
					PDFTextStripper pdfStripper = new PDFTextStripper();
					PDDocument pdDoc = PDDocument.load(content.getObjectAsByteArray());
					parsedText = pdfStripper.getText(pdDoc);
				}
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		return Var.valueOf(parsedText);
	}

}
