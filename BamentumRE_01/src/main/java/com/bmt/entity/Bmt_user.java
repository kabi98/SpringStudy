package com.bmt.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Bmt_user {
	private String email;
	private String pw;
	private String nick;
	private String profile;
	private String info;
	private Date joindate;
}
