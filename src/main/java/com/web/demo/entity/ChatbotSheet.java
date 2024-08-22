/**
 * 챗봇 시트 (질문에 대한 대답 메뉴얼 데이터)
 */
package com.web.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class ChatbotSheet {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator6")
    @SequenceGenerator(name = "sequence_generator6", sequenceName = "sequence_name6", allocationSize = 1)
	private Long id;
	
	@Column(length = 2048)
	private String question;
	
	@Column(length = 2048)
	private String answer;
	
	private Integer label;
}











