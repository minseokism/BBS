package com.minseokism.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "token")
@Data
@NoArgsConstructor
public class Token {
	@Id
	@GeneratedValue
	@Column(name = "token_no")
	private int tokenNo;
	
	@Column(name= "token_id")
	private String tokenId;
	
	private String token;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_no")
	private User user;	
	
	public Token(String tokenId, String token, User user) {
		this.tokenId = tokenId;
		this.token = token;
		this.user = user;
	}
	
}
