package com.cs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String companyName;
	private String address;
	private String country;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Company() {
		super();
	}

	public Company(long id, String companyName, String address, String country) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.address = address;
		this.country = country;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", address=" + address + ", country=" + country
				+ "]";
	}

}
