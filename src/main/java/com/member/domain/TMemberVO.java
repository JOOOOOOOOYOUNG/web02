package com.member.domain;

import java.sql.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TMemberVO {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String uuid;
	private Date joinDate;
}
