package com.tgdd.entities;

import java.io.Serializable;

import org.springframework.beans.propertyeditors.InputSourceEditor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO implements Serializable {
	private long categoryId;
	private String categoryName;

}
