package com.tgdd.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.tgdd.entities.CategoryDTO;

import javassist.expr.NewArray;

@Controller
@RequestMapping("admin/categories")
public class AdminController {

	@GetMapping("")
	public String list(ModelMap model) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<CategoryDTO[]> entity = new HttpEntity<CategoryDTO[]>(headers);
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "http://localhost:8001/categories";
		ResponseEntity<CategoryDTO[]> response = restTemplate.exchange(fooResourceUrl, //
				HttpMethod.GET, entity, CategoryDTO[].class);
		HttpStatus statusCode = response.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);
		if (statusCode == HttpStatus.OK) {
			CategoryDTO[] category = response.getBody();
			model.addAttribute("listCategories", category);
			model.addAttribute("id", category);
			if (category != null) {
				for (CategoryDTO e : category) {
					System.out.println("Category: " + e.getCategoryId() + " - " + e.getCategoryName());

				}
			}

		}

		return "list";
	}

	@GetMapping("addCategory")
	public String addCategory(ModelMap model) {
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

//	@GetMapping("/findCategory")
//	public CategoryDTO

//edit
	@RequestMapping(value = "/{categoryId}/edit", method = RequestMethod.GET)
	public String getCategory(@PathVariable("id") long id, CategoryDTO category, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		String URL_UPDATE_CATEGORYsss = "http://localhost:8001/categories/{categoryId}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<CategoryDTO> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<CategoryDTO> responseEntity = restTemplate.exchange(URL_UPDATE_CATEGORYsss + id, HttpMethod.GET,
				requestEntity, CategoryDTO.class);
//	        ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity(baseURL + id, UserDTO.class);
		System.out.println("Status Code: " + responseEntity.getStatusCode());
		System.out.println("Response Header: " + responseEntity.getHeaders());

		return "addOrEddit" + category.getCategoryId();
	}

	@RequestMapping(value = "/{categoryId}/edit", method = RequestMethod.POST)
	public String saveEditCategory(Model model, @PathVariable("id") long categoryId,
			@ModelAttribute("categoriesForm") @RequestBody CategoryDTO categoryName) {
		String URL_UPDATE_CATEGORY = "http://localhost:8001/categories/{categoryId}";

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		RestTemplate restTemplate = new RestTemplate();

		// Dữ liệu đính kèm theo yêu cầu.
		HttpEntity<CategoryDTO> requestBody = new HttpEntity<>(categoryName, headers);

		restTemplate.exchange(URL_UPDATE_CATEGORY, HttpMethod.PUT, requestBody, Void.class);
		model.addAttribute("add", false);
		return "redirect:/admin/categories/" + String.valueOf(categoryName.getCategoryId());
	}
}
