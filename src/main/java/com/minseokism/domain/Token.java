package com.minseokism.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "token")
@Data
@RequiredArgsConstructor
public class Token {
	@Id
	@GeneratedValue
	@Column(name = "token_no")
	private int tokenNo;
	
	@Column(name= "token_id")
	@NotNull
	private String tokenId;
	
	@NotNull
	private String token;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_no")
	@NotNull
	private User user;
	
	public Token(String tokenId, String token, User user) {
		this.tokenId = tokenId;
		this.token = token;
		this.user = user;
	}
}
