package in.report.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.report.entity.CitizenPlan;
import in.report.repo.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private CitizenPlanRepository repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		repo.deleteAll();
		
		CitizenPlan c1 = new CitizenPlan();
		c1.setCitizenName("Jhon");
		c1.setGender("Male");
		c1.setPlanName("Cash");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmount(5000.00);
		
		CitizenPlan c2 = new CitizenPlan();
		c2.setCitizenName("Smith");
		c2.setGender("Male");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Denail");
		c2.setDenailReason("Rental Income");
		
		CitizenPlan c3 = new CitizenPlan();
		c3.setCitizenName("Cathy");
		c3.setGender("Fe-Male");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Terminated");
		c3.setPlanStartDate(LocalDate.now().minusMonths(4));
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		c3.setBenefitAmount(6000.00);
		c3.setTerminatedDate(LocalDate.now());
		c3.setTerminatedReason("Employed");
		
		CitizenPlan c4 = new CitizenPlan();
		c4.setCitizenName("David");
		c4.setGender("Male");
		c4.setPlanName("Food");
		c4.setPlanStatus("Approved");
		c4.setPlanStartDate(LocalDate.now());
		c4.setPlanEndDate(LocalDate.now().plusMonths(6));
		c4.setBenefitAmount(4000.00);
		
		CitizenPlan c5 = new CitizenPlan();
		c5.setCitizenName("Kumar");
		c5.setGender("Male");
		c5.setPlanName("Food");
		c5.setPlanStatus("Denail");
		c5.setDenailReason("Property Income");
		
		CitizenPlan c6 = new CitizenPlan();
		c6.setCitizenName("Sruthi");
		c6.setGender("Fe-Male");
		c6.setPlanName("Food");
		c6.setPlanStatus("Terminated");
		c6.setPlanStartDate(LocalDate.now().minusMonths(4));
		c6.setPlanEndDate(LocalDate.now().plusMonths(6));
		c6.setBenefitAmount(5000.00);
		c6.setTerminatedDate(LocalDate.now());
		c6.setTerminatedReason("Employed");
		
		CitizenPlan c7 = new CitizenPlan();
		c7.setCitizenName("Sunil");
		c7.setGender("Male");
		c7.setPlanName("Medical");
		c7.setPlanStatus("Approved");
		c7.setPlanStartDate(LocalDate.now());
		c7.setPlanEndDate(LocalDate.now().plusMonths(6));
		c7.setBenefitAmount(7000.00);
		
		CitizenPlan c8 = new CitizenPlan();
		c8.setCitizenName("Lokesh");
		c8.setGender("Male");
		c8.setPlanName("Medical");
		c8.setPlanStatus("Denail");
		c8.setDenailReason("Rental Income");
		
		CitizenPlan c9 = new CitizenPlan();
		c9.setCitizenName("Supriya");
		c9.setGender("Fe-Male");
		c9.setPlanName("Medical");
		c9.setPlanStatus("Terminated");
		c9.setPlanStartDate(LocalDate.now().minusMonths(4));
		c9.setPlanEndDate(LocalDate.now().plusMonths(6));
		c9.setBenefitAmount(6000.00);
		c9.setTerminatedDate(LocalDate.now());
		c9.setTerminatedReason("Govt Job");
		
		CitizenPlan c10 = new CitizenPlan();
		c10.setCitizenName("Sai Teja");
		c10.setGender("Male");
		c10.setPlanName("Employment");
		c10.setPlanStatus("Approved");
		c10.setPlanStartDate(LocalDate.now());
		c10.setPlanEndDate(LocalDate.now().plusMonths(6));
		c10.setBenefitAmount(6000.00);
		
		CitizenPlan c11 = new CitizenPlan();
		c11.setCitizenName("Lokesh");
		c11.setGender("Male");
		c11.setPlanName("Employment");
		c11.setPlanStatus("Denail");
		c11.setDenailReason("Property Income");
		
		CitizenPlan c12 = new CitizenPlan();
		c12.setCitizenName("Srija");
		c12.setGender("Fe-Male");
		c12.setPlanName("Employment");
		c12.setPlanStatus("Terminated");
		c12.setPlanStartDate(LocalDate.now().minusMonths(4));
		c12.setPlanEndDate(LocalDate.now().plusMonths(6));
		c12.setBenefitAmount(5000.00);
		c12.setTerminatedDate(LocalDate.now());
		c12.setTerminatedReason("Govt Job");
		
		List<CitizenPlan> list = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
		repo.saveAll(list);
	}
}
