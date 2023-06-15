package com.board.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TMemberDTO {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String uuid;
	private LocalDate joinDate;
}
