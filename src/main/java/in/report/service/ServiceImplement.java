package in.report.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.report.entity.CitizenPlan;
import in.report.repo.CitizenPlanRepository;
import in.report.request.SearchRequest;

@Service
public class ServiceImplement implements ReportService {

	@Autowired
	private CitizenPlanRepository planRepo;

	@Override
	public List<String> getPlanName() {
		return planRepo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		return planRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();
		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}

		if (null != request.getPlanStartDate() && !"".equals(request.getPlanStartDate())) {
			String startDate = request.getPlanStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");

			// convert String to LocalDate
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(localDate);
		}
		if (null != request.getPlanEndDate() && !"".equals(request.getPlanEndDate())) {
			String startDate = request.getPlanEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");

			// convert String to LocalDate
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanEndDate(localDate);
		}
		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		
		List<CitizenPlan> all = planRepo.findAll();
		Workbook workbook = new HSSFWorkbook();
		
		Sheet sheet = workbook.createSheet("CitizenPlan-Data");
		Row row = sheet.createRow(0);
		
		row.createCell(0).setCellValue("Citizen ID");
		row.createCell(1).setCellValue("Citizen Name");
		row.createCell(2).setCellValue("Gender");
		row.createCell(3).setCellValue("Plan Name");
		row.createCell(4).setCellValue("Plan Status");
		row.createCell(5).setCellValue("Plan Start Date");
		row.createCell(6).setCellValue("Plan End Date");
		row.createCell(7).setCellValue("Benefit Amount");

		int dataRowIndex = 1;
		
		for(CitizenPlan plan:all) {
			Row dataRow = sheet.createRow(dataRowIndex);
			
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getGender());
			dataRow.createCell(3).setCellValue(plan.getPlanName());
			dataRow.createCell(4).setCellValue(plan.getPlanStatus());
			if(null!=plan.getPlanStartDate()) {
			dataRow.createCell(5).setCellValue(plan.getPlanStartDate()+"");
			}
			else {
				dataRow.createCell(5).setCellValue("N/A");
			}
			if(null!=plan.getPlanEndDate()) {
			dataRow.createCell(6).setCellValue(plan.getPlanEndDate()+"");
			}
			else{
				dataRow.createCell(6).setCellValue("N/A");
			} 
				
			if(null!=plan.getBenefitAmount()) {
			dataRow.createCell(7).setCellValue(plan.getBenefitAmount());
			}
			else {
				dataRow.createCell(7).setCellValue("N/A");
			}
			dataRowIndex++;
		}
		
//		FileOutputStream fileOutputStream = new FileOutputStream(new File("CitizenPlan.xls"));
//		workbook.write(fileOutputStream);
//		workbook.close();
//		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		
		
		return true;
	}

	@Override
	public boolean exportPDF(HttpServletResponse response) throws Exception {

		Document document =new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		Paragraph p=new Paragraph("Citizen Plan Data");
		document.add(p);
		PdfPTable table=new PdfPTable(8);
		
		table.addCell("Citizen ID");
		table.addCell("Citizen Name");
		table.addCell("Gender");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		table.addCell("benefit Amount");
		
		List<CitizenPlan> all = planRepo.findAll();
		
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
