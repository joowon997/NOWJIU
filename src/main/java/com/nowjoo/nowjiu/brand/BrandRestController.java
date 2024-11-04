package com.nowjoo.nowjiu.brand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nowjoo.nowjiu.brand.domain.BrandImage;
import com.nowjoo.nowjiu.brand.service.BrandService;

@RestController
@RequestMapping("/api")
public class BrandRestController {
	
	private BrandService brandService;
	
	public BrandRestController(
			BrandService brandService) {
		this.brandService = brandService;
	}
	
	//브랜드 이미지 추가
		@PostMapping("/brand-image-add")
		public Map<String, String> addGoods(
				 @RequestParam("brandId") int brandId
				, @RequestParam("image") MultipartFile image
				){
			
			BrandImage brandImage = brandService.addBrandImage(image, brandId);
			
			Map<String, String> resultMap = new HashMap<>();
			
			if (brandImage != null) {
				resultMap.put("result", "success");
			} else {
				resultMap.put("result", "fail");
			}
			
			return resultMap;
		}
}
