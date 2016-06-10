package com.minseokism.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Column(nullable = false)
	private String id;
	
	@Column(nullable = false)
	private String pwd;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String name;
}
