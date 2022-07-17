package com.tgdd.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.tgdd.entities.CategoryDTO;
import com.tgdd.entities.CategoryList;

import lombok.Data;

@Controller
@RequestMapping("admin/categories")
public class AdminController {

	@RequestMapping
	public String list(ModelMap model) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<CategoryDTO[]> entity = new HttpEntity<CategoryDTO[]>(headers);

		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "http://localhost:8001/categories?format=json";
		ResponseEntity<CategoryDTO[]> response = restTemplate.exchange(fooResourceUrl, //
				HttpMethod.GET, entity, CategoryDTO[].class);
		HttpStatus statusCode = response.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);
		if (statusCode == HttpStatus.OK) {
			CategoryDTO[] category = response.getBody();
			model.addAttribute("listCategories", category);

			if (category != null) {
				for (CategoryDTO e : category) {
					System.out.println("Category: " + e.getCategoryId() + " - " + e.getCategoryName());

				}
			}

		}

		return "list";
	}

	@GetMapping("addCategory")
	public String ShowAddCategory(ModelMap model) {
		model.addAttribute("add", true);
		model.addAttribute("categoriesForm", new CategoryDTO());
		return "addOrEdit";
	}

	@PostMapping("addCategory")
	public String saveCategory(Model model, @ModelAttribute("categoriesForm") CategoryDTO category) {
		String fooResourceUrl1 = "http://localhost:8001/categories";

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_XML_VALUE);
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();

		// Dữ liệu đính kèm theo yêu cầu.
		HttpEntity<CategoryDTO> requestBody = new HttpEntity<>(category, headers);

		// Gửi yêu cầu với phương thức POST.
		CategoryDTO c = restTemplate.postForObject(fooResourceUrl1, requestBody, CategoryDTO.class);

		System.out.println("Employee created: " + c.getCategoryId());
		model.addAttribute("add", true);

		return "redirect:/admin/categories";
	}

	@GetMapping("edit")
	public String EditCategory(ModelMap model) {

		model.addAttribute("categoriesFormEdit", new CategoryDTO());
		return "edit";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		CategoryDTO category = new CategoryDTO();
		if (id.isPresent()) {
			category.setCategoryId(id.get());
			HttpEntity<CategoryDTO> entity = new HttpEntity<CategoryDTO>(category, headers);

			category = restTemplate
					.exchange("http://localhost:8001/categories", HttpMethod.POST, entity, CategoryDTO.class).getBody();

		}
		model.addAttribute("categoriesFormEdit", category);

		return "edit";
	}

	@RequestMapping(path = "/delete/{id}")
	public String deleteCategoryId(Model model, @PathVariable("id") Long id) {

		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		CategoryDTO category = new CategoryDTO();
//		category.setCategoryId(id);
//		HttpEntity entity = new HttpEntity(category, headers);
//		restTemplate.exchange("http://localhost:8001/categories", HttpMethod.POST, entity, String.class).getBody();

		String url = "http://localhost:8001/categories/" + id + "";

		restTemplate.delete(url);
		return "redirect:/admin/categories";
	}

}
