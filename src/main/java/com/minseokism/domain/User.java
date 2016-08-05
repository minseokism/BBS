package com.minseokism.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue
	@Column(name = "user_no")
	private Integer no;
	
	@Pattern(regexp = "^[a-z0-9][a-z0-9_-]{4,29}$")
	@Column(nullable = false)
	private String id;

	@Column(nullable = false)
	private String pwd;
	
	@Pattern(regexp = "^[a-zA-Z0-9_-[+]]+@[a-zA-Z0-9//.]+$")
	@Column(nullable = false)
	private String email;
	
	@Pattern(regexp = "^[가-힣ㅣa-zA-Z0-9]{0,19}$")
	@Column(nullable = false)
	private String name;
	
	private int state;
}
