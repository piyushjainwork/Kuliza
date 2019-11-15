package com.kuliza.Assignment.ServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuliza.Assignment.Model.Employee;
import com.kuliza.Assignment.Pojo.EmployeePojo;
import com.kuliza.Assignment.Repository.EmployeeDao;
import com.kuliza.Assignment.Service.EmpService;

@Service
public class EmployeeServiceImplementation implements EmpService {

	private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImplementation.class);

	@Autowired
	EmployeeDao empRepo;

	public String postEmpDetails(EmployeePojo empdetail) {

		logger.debug("Entering to create a employee");
		Employee emp = new Employee();

		emp.setName(empdetail.getName());
		emp.setPhone(empdetail.getPhone());
		emp.setSalary(empdetail.getSalary());
		emp.setAge(empdetail.getAge());

		empRepo.save(emp);
		logger.debug("Employee Details Posted now exiting from post employee");

		return "Employee details poseted";
	}

	public List<Employee> getEmpDetails() {

		return empRepo.findAll();
	}

	public Employee getbyid(long empid) {
		logger.debug("Entering to get a employee by emp id");

		Employee emp = empRepo.findById(empid);
		if (emp == null) {
			return null;
		}
		logger.debug("completing  get a employee by emp id");
		return empRepo.findById(empid);

	}

	public Employee saveOrUpdate(EmployeePojo empdetail, long id) {
		logger.debug("Entering to save Or update employee ");

		Employee emp = empRepo.findById(id);

		System.out.println("Piyush");

		if (emp != null) {
			emp.setAge(empdetail.getAge());
			emp.setName(empdetail.getName());
			emp.setPhone(empdetail.getPhone());
			emp.setSalary(empdetail.getSalary());
			empRepo.save(emp);
			logger.debug("Completed  Updating a employee");
			return emp;
		} else {
			return null;
		}

	}

	public String deleteEmployee(long id) {
		logger.debug("Entering to delete a employee details ");

		Employee emp = empRepo.findById(id);

		if (emp != null) {
			empRepo.deleteById(id);
			logger.debug("Completed deleting a employee");
			return "user Deleted";
		}

		else {

			logger.debug("Employee do not exist");
			return "User Not Found";
		}

	}

}