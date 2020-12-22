package com.cs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.cs.entity.Employee;
import com.cs.repository.EmployeeRepository;

@Component
public class WriteToFile implements CommandLineRunner {

	private final int COINS_NUMBER = 100;
	private List<String> headers = List.of("ФИО", "Название компании", "Зарплата", "Название департамента");

	@Autowired
	private EmployeeRepository employeeRepository;
	
	Workbook workbook = new XSSFWorkbook();
	Sheet sheet = workbook.createSheet("Employee");

	public void run(String... args) {
		List<Employee> employees = employeeRepository.findAll(new Sort(Sort.Direction.ASC, "lastName", "firstName", "middleName"));
		addColumnHeaders();
		addDataToColumn(employees);
		addEmployeesInfo(employees);
		alignColumnsWidth();
		writeToFile();
	}
	
	private void addColumnHeaders() {
		Row headerRow = sheet.createRow(0);
		headers.forEach(header -> {
			headerRow.createCell(headers.indexOf(header)).setCellValue(header);
		});
	}

	private void addDataToColumn(List<Employee> employees) {
		Cell cell;
		int rowNum = 1;
		for (Employee employee : employees) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(employee.getLastName() + " " + employee.getFirstName() + " " + employee.getMiddleName());
			row.createCell(1).setCellValue(employee.getDepartment().getCompany().getCompanyName());
			CreationHelper createHelper = workbook.getCreationHelper();
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#,##0.00"));
			cell = row.createCell(2);
			cell.setCellValue(employee.getSalary() / COINS_NUMBER);
			cell.setCellStyle(cellStyle);
			row.createCell(3).setCellValue(employee.getDepartment().getDepartmentName());
		}
	}
	
	void addEmployeesInfo(List<Employee> employees) {
		int rowNum=employees.size()+2;
		Row salarySum = sheet.createRow(rowNum++);
		salarySum.createCell(0).setCellValue("Общая сумма по зарплатам");
		salarySum.createCell(1).setCellValue(employeeRepository.sumSalary() / COINS_NUMBER);
		Row employeesNumberR = sheet.createRow(rowNum++);
		employeesNumberR.createCell(0).setCellValue("Количество сотрудников");
		employeesNumberR.createCell(1).setCellValue(employees.size());
	}
	
	void alignColumnsWidth() {
		for (int i = 0; i < headers.size(); i++) {
			sheet.autoSizeColumn(i);
		}
	}
	
	void writeToFile() {
		try {
			FileOutputStream fileOut = new FileOutputStream("report.xlsx");
			workbook.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
