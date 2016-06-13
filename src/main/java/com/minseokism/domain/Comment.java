package com.minseokism.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	@Id
	@GeneratedValue
	@Column(name = "com_no")
	private Integer no;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private Date regDate;
	
	@Column(nullable = false)
	private Date modDate;
}
