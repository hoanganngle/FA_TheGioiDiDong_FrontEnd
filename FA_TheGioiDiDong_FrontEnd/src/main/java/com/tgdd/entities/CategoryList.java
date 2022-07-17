package com.tgdd.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CategoryList {
	private List<CategoryDTO> categorydto;

	public CategoryList() {
		categorydto = new ArrayList<>();
	}
}
