package in.report.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import in.report.entity.CitizenPlan;
import in.report.request.SearchRequest;

public interface ReportService {

	public List<String> getPlanName();

	public List<String> getPlanStatus();

	public List<CitizenPlan> search(SearchRequest request);

	public boolean exportExcel(HttpServletResponse response) throws Exception;

	public boolean exportPDF(HttpServletResponse response) throws Exception;

}
