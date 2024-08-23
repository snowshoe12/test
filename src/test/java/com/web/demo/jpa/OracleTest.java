package com.web.demo.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.web.demo.entity.NaverReviews;
import com.web.demo.service.NaverReviewsService;

@SpringBootTest
public class OracleTest {
	@Autowired
	private NaverReviewsService naverReviewsService;
	
	@Test
	void test() {
		Page<NaverReviews> paging = naverReviewsService.getList(1);
	}
}
