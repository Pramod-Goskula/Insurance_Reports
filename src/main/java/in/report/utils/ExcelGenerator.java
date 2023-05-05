package in.report.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import in.report.entity.CitizenPlan;

@Component
public class ExcelGenerator {

	public boolean getExcelReport(HttpServletResponse response,List<CitizenPlan> all,File file)throws Exception {
	   
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

		for (CitizenPlan plan : all) {
			Row dataRow = sheet.createRow(dataRowIndex);

			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getGender());
			dataRow.createCell(3).setCellValue(plan.getPlanName());
			dataRow.createCell(4).setCellValue(plan.getPlanStatus());
			if (null != plan.getPlanStartDate()) {
				dataRow.createCell(5).setCellValue(plan.getPlanStartDate() + "");
			} else {
				dataRow.createCell(5).setCellValue("N/A");
			}
			if (null != plan.getPlanEndDate()) {
				dataRow.createCell(6).setCellValue(plan.getPlanEndDate() + "");
			} else {
				dataRow.createCell(6).setCellValue("N/A");
			}

			if (null != plan.getBenefitAmount()) {
				dataRow.createCell(7).setCellValue(plan.getBenefitAmount());
			} else {
				dataRow.createCell(7).setCellValue("N/A");
			}
			dataRowIndex++;
		}

		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);
		workbook.close();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		
		return true;

	}
}
