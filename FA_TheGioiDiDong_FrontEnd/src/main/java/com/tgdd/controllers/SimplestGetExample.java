package com.tgdd.controllers;

import org.springframework.web.client.RestTemplate;

import com.tgdd.entities.CategoryDTO;

public class SimplestGetExample {
	static final String URL_EMPLOYEES = "http://localhost:8001/categories?format=json";

//	static final String URL_EMPLOYEES_XML = "http://localhost:8080/employees.xml";
//	static final String URL_EMPLOYEES_JSON = "http://localhost:8080/employees.json";

	public static void main(String[] args) {

		RestTemplate restTemplate = new RestTemplate();

		CategoryDTO[] list = restTemplate.getForObject(URL_EMPLOYEES, CategoryDTO[].class);

		if (list != null) {
			for (CategoryDTO e : list) {
				System.out.println("CategoryID: " + e.getCategoryId() + " - " + e.getCategoryName());
			}
		}
	}
}
