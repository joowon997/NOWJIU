package com.nowjoo.nowjiu.administrator.dto;

import java.util.List;

import com.nowjoo.nowjiu.brand.domain.Brand;
import com.nowjoo.nowjiu.category.domian.Category;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddGoodsdDto {
	private List<Category> categoryList;
	private List<Brand> brandList;
}
