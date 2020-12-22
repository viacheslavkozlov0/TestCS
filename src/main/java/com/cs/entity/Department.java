package com.cs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;

	private String departmentName;

	@ManyToOne
	@JoinColumn(name = "companyId")
	private Company company;

	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Department() {
		super();
	}

	public Department(long id, String departmentName, Company company, String description) {
		super();
		this.id = id;
		this.departmentName = departmentName;
		this.company = company;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName + ", company=" + company + ", description="
				+ description + "]";
	}

}
