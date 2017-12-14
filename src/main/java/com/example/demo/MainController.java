package com.example.demo;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Recipe;
import repository.RecipeRepository;



@RestController
public class MainController{
		
	 @Autowired RecipeRepository repository;

	 
	 @RequestMapping(method = RequestMethod.GET,value="/api")
	 public List<Recipe> greet() {
		List<Recipe> recipeList=repository.getRecipeList();	
		return recipeList;
	 }
	 
	 /*@RequestMapping(method = RequestMethod.GET,value="/review/{moviename}")
	 public List<MovieReviews> getReviews(@PathVariable String moviename) {
		List<MovieReviews> movieReviewList=repository.getUserReviewsForMovie(moviename);	
		return movieReviewList;
	 }*/
	
	 
}
