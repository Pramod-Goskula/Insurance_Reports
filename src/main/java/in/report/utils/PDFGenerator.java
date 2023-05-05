package in.report.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.report.entity.CitizenPlan;

@Component
public class PDFGenerator {

	public boolean pdfGenerator(HttpServletResponse response,List<CitizenPlan> all,File file) throws Exception {
		Document document =new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(file));
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);
		document.open();
		Paragraph paragraph=new Paragraph("Citizen Plan Data" ,fontTiltle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(paragraph);
		PdfPTable table=new PdfPTable(8);
		
		table.setWidthPercentage(100f);
		
		table.setSpacingBefore(8);
		
		table.addCell("Citizen ID");
		table.addCell("Citizen Name");
		table.addCell("Gender");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		table.addCell("benefit Amount");
		
		
		
		for(CitizenPlan plan:all) {
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getGender());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			table.addCell(plan.getPlanStartDate()+"");
			table.addCell(plan.getPlanEndDate()+"");
			table.addCell(plan.getBenefitAmount()+"");
			
		}
		
		document.add(table);
		document.close();
		
		return true;
	}
}
