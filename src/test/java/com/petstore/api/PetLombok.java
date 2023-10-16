package com.petstore.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 {
  "id": 100,
  "category": {
    "id": 1,
    "name": "Labra"
  },
  "name": "Bozo",
  "photoUrls": [
    "https://www.dog1.com",
    "https://www.dog2.com"
  ],
  "tags": [
    {
      "id": 10,
      "name": "Red"
    }
  ],
  "status": "available"
}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetLombok {
	
	private Integer id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private List<Tag> tags;
	private String status;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Category{
		private Integer id;
		private String name;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Tag{
		private Integer id;
		private String name;
	}
	
	
	
	

}
