package com.web.demo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 사용자 정보를 FormData 형식으로 통으로 보냈다
 * 사용자 정보를 통으로 받아줄 형식이 필요 => DTO로 받겟다
 * 통신단에서 사용하는 DTO
 */
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberDto {
	private int age;
	private String name;
	private String addr;
	@Builder
	public MemberDto(int age, String name, String addr) {
		super();
		this.age = age;
		this.name = name;
		this.addr = addr;
	}
}
















