package com.project.skin.category.validator;

import org.springframework.stereotype.Component;

import com.project.skin.category.provider.CategoryProvider;
import com.project.skin.global.error.exception.DuplicateCategoryCodeException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CreateCategoryValidate {
	private final CategoryProvider categoryProvider;

	public void validateCategoryCode(String code) {
		if (categoryProvider.findCategoryByCode(code).isEmpty()) {
			throw new DuplicateCategoryCodeException();
		}
	}
}
