package com.fakestore.api;

//POJO Class --cannot have any parent class

/*
 * Private class vars
 * Public const. --Default and Param. const.
 * Public getter/setter
 * 
 *  {
        "id": 1,
        "title": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
        "price": 109.95,
        "description": "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
        "category": "men's clothing",
        "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
        "rating": {
            "rate": 3.9,
            "count": 120
        }
    }
 */

public class Product {

	// 1. private class vars
	private int id;
	private String title;
	private Float price;
	private String description;
	private String category;
	private String image;

	private Rating rating; // class var of product class--non-primitive data type---create inner class --see at bottom

	// 2. default const. --imp. for de-serialization
	public Product() {

	}

	// 3. Param const.
	public Product(int id, String title, Float price, String description, String category, String image,
			Rating rating) {

		this.id = id;
		this.title = title;
		this.price = price;
		this.description = description;
		this.category = category;
		this.image = image;
		this.rating = rating;
	}

	// 4. Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	// Rating class:static class--don't want to create separate class--Rating is part of product class--inner class of product class
	public static class Rating {

		// 1. private class vars
		private double rate;
		private int count;

		// Can we create default const. of static class? Yes
		// 2. default const.
		public Rating() {

		}

		// 3. Param. const
		public Rating(double rate, int count) {
			this.rate = rate;
			this.count = count;
		}

		// 4. getter and setter
		public double getRate() {
			return rate;
		}

		public void setRate(double rate) {
			this.rate = rate;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

	}

}
