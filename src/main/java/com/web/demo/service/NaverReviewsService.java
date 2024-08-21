package com.web.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.demo.entity.NaverReviews;
import com.web.demo.repository.NaverReviewsRepository;

@Service
public class NaverReviewsService {
	@Autowired
	private NaverReviewsRepository naverReviewsRepository;
	// 페이징 계산시 기준이 되는 페이지당 게시물의 개수 => 환경변수도 가능, 10개로 설정
	private final int PAGE_SIZE = 10;
	/**
	 * 입력 : 특정 페이지 번호
	 * 결과 : 해당페이지에 속한 게시물 데이터(DTO제공, 페이징 객체를 활용 제공)
	 */
	public Page<NaverReviews> getList(int page) {
		/*
		 	기능 정리
		 	PageRequest -> 페이지번호, 페이지당 데이터 개수, 정렬조건, ..-> SQL 생성
		 	Pageable 	-> SQL 실행하는 기능 담당, 레파지토리가 sql 실행시 관여 -> 결과를 제공
		 	Page -> 페이지번호, 페이지당 데이터개수, 정렬조건 부합하는 결과 셋을 가지고 있다
		 	차후 보안 이슈로 조정 => NaverReviews는 엔티티이다 DTO로 대체 처리 혹은 교환처리
		*/
		// 데이터 정렬은 미포함 상태
		// 내부적으로 특정 위치에서 특정 개수만큼 jpa를 통해 데이터를 획득하여 Pageable 형태로 반환
		Pageable pageable = PageRequest.of(page, PAGE_SIZE);
		return naverReviewsRepository.findAll( pageable );
	}
}












