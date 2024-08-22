/**
 * 파일 업로드 화면 제공
 * 파일 업로드 실제 처리 제공
 * 파일 다운로드 실제 처리 제공
 * 단, 클라우드상 스토리지 저장은 생략, 데이터베이스 연동 생략
 * URL prefix "/file", 컨트롤러 설정
 * 테스트 편의상 jwt, csrf 설정 고려
 */
package com.web.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.web.demo.dto.FileUploadResultDto;
import com.web.demo.dto.MemberDto;
import com.web.demo.service.PostService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 롬복에서 제공하는 자바 로깅 API
@Slf4j
@RequestMapping("/file")
@Controller
public class FileApiController {
	// 파일 업로드 화면 제공
	// ~/file/upload
	@GetMapping("/upload")
	public String upload() {
		return "file_process"; // file_process.html 생성
	}
	// 파일 업로드 실제 처리 제공
	// 일반데이터 + 파일 동시 전송
	@PostMapping("/upload")	
	@ResponseBody
	public ResponseEntity<FileUploadResultDto> upload( 
			MemberDto member, // 나이, 이름, 주소 정보를 통으로 받아서 전달
			@RequestParam("uploadFile") MultipartFile uploadFile, // 파일을 받아서 전달
			HttpServletRequest req) {
		// 0. 폼 데이터 확인
		log.info(member.toString());
		
		// 1. 파일 저장할 경로 획득 (여기서는 톰켓내부, 실제는 클라우드의 스토리지 선택)
		String path     = req.getServletContext().getRealPath(""); // 저장한 위치(서버측)
		log.info("path:" + path);
		String filename = uploadFile.getOriginalFilename();	// 파일명
		log.info("filename:" + filename);
		
		// 2. 저장 -> 클라우드상의 스토리지에 저장 권장
		try {
			log.info(path + "/" + filename);
			File file = new File(path + "/" + filename);
			// 자바프로그램(판단기준) -> 하드디스크로 데이터 전송
			BufferedOutputStream bos =  new BufferedOutputStream( new FileOutputStream(file) );
			// 데이터가 흘러감 : MultipartFile -> 출력스트림 -> file
			bos.write(uploadFile.getBytes());
			bos.close();			
		} catch (Exception e) {
			log.error("파일 저장시 오류 :" + e.getMessage());
			FileUploadResultDto res = FileUploadResultDto.builder()
					.code(-1).message(e.getMessage()).build();
			return ResponseEntity.status(HttpStatus.OK).body( res );
		}
	
		// 3. 결과 응답 -> dto -> json
		//return "응답";
		// dto 생성(데이터 세팅) -> json 응답
		FileUploadResultDto res = FileUploadResultDto.builder().code(1).message(filename).build();
		return ResponseEntity.status(HttpStatus.OK).body( res );
	}
//	// 파일 다운로드 실제 처리 제공
//	@RequestMapping("/download")
//	public String download() {
//		return "";
//	}
}






