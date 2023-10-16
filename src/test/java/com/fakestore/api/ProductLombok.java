package com.fakestore.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//No need to do anything, Lombok will create no param. const., all arg const, and data(getter and setter).
//POJO class created with the help of Lombok

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLombok {
	
	private int id;
	private String title;
	private Float price;
	private String description;
	private String category;
	private String image;
	private Rating rating; 
	
	//Rating class - Inner class
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Rating {
		
		private double rate;
		private int count;
	}
	
	
}
