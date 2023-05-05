package in.report.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.report.entity.CitizenPlan;
import in.report.repo.CitizenPlanRepository;
import in.report.request.SearchRequest;
import in.report.utils.EmailSenderUtils;
import in.report.utils.ExcelGenerator;
import in.report.utils.PDFGenerator;

@Service
public class ServiceImplement implements ReportService {

	@Autowired
	private CitizenPlanRepository planRepo;
	@Autowired
	private ExcelGenerator excelGeneraor;
	@Autowired
	private EmailSenderUtils emailSender;
	@Autowired
	private PDFGenerator  pdfGenerator;

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
		 File file = new File("citizenPlans.xls");
		 excelGeneraor.getExcelReport(response,all,file);
		
			
			String to="pramodgoskula@gmail.com";
			String subject=" Check Insurance excel Report";
			String body="<h3>This is a Generated Excel Report</h3>";
			
			boolean sendEmail = emailSender.sendEmail(to, subject, body,file);
			if(sendEmail) {
			file.delete();
			return true;
			
			}
	return false;
	}

	@Override
	public boolean exportPDF(HttpServletResponse response) throws Exception {
		 List<CitizenPlan> all = planRepo.findAll();
		 File file = new File("citizenPlans.pdf");
		 pdfGenerator.pdfGenerator(response,all,file);
			String to="pramodgoskula@gmail.com";
			String subject=" Check Insurance PDF Report";
			String body="<h3>Insurance PDF Report</h3>";
			
			boolean sendEmail = emailSender.sendEmail(to, subject, body,file);
			if(sendEmail) {
			file.delete();
			return true;
			
			}
		return true;
	}

}
