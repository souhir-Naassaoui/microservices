package com.example.demo.entities;



import java.util.Date;

import lombok.Data;

@Data
public class Events {
	private Long Id;
	private String title;
	private Date date;
	private String lieux;
}