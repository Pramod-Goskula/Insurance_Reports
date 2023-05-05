package in.report.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.report.request.SearchRequest;
import in.report.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService service;

	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response,Model model) throws Exception {
		response.setContentType("application/pdf");
		response.addHeader("content-Disposition", "attachment;filename=citizenPlans.pdf;");
		model.addAttribute("msg", "text");
		boolean status = service.exportPDF(response);
		if (status) {
			model.addAttribute("success", "Excel Report Attachment Sent to your Email ");
		}
	}

	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response, Model model) throws Exception {
		response.setContentType("application/octect-stream");
		response.addHeader("content-Disposition", "attachment;filename=citizenPlans.xls;");
		boolean status = service.exportExcel(response);
		if (status) {
			model.addAttribute("success", "Excel Report Attachment Sent to your Email ");
		}
	}

	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest request, Model model) {
		model.addAttribute("plans", service.search(request));
		init(model);
		return "index";
	}

	@GetMapping("/")
	public String displayPage(Model model) {
		model.addAttribute("search", new SearchRequest());
		init(model);
		return "index";
	}

	private void init(Model model) {
		model.addAttribute("names", service.getPlanName());
		model.addAttribute("status", service.getPlanStatus());
	}

}
