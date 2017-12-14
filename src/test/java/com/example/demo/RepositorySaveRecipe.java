package com.example.demo;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Recipe;
import repository.RecipeRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RepositorySaveRecipe {

    
	SimpleJpaRepository<Recipe, Integer> repo;

	@Mock EntityManager em;
	@Mock CriteriaBuilder builder;
	@Mock CriteriaQuery<Recipe> criteriaQuery;
	@Mock CriteriaQuery<Long> countCriteriaQuery;
	@Mock TypedQuery<Recipe> query;
	@Mock TypedQuery<Long> countQuery;
	@Mock JpaEntityInformation<Recipe, Long> information;
	@Mock CrudMethodMetadata metadata;


	@Before
	public void setUp() {

		when(em.getDelegate()).thenReturn(em);

		when(information.getJavaType()).thenReturn(Recipe.class);
		when(em.getCriteriaBuilder()).thenReturn(builder);

		when(builder.createQuery(Recipe.class)).thenReturn(criteriaQuery);
		when(builder.createQuery(Long.class)).thenReturn(countCriteriaQuery);

		when(em.createQuery(criteriaQuery)).thenReturn(query);
		when(em.createQuery(countCriteriaQuery)).thenReturn(countQuery);

		repo = new SimpleJpaRepository<Recipe, Integer>(information, em);
		repo.setRepositoryMethodMetadata(metadata);
	}

	@Test 
	public void testgetallrecipes() {
		repo.findAll();
		verify(query).getResultList();
	}
    
	@Test
	public void testaddrecipe() {

		Recipe recipe = new Recipe();

		when(em.contains(recipe)).thenReturn(false);

		repo.save(recipe);

		verify(em).getTransaction();
	}

   
}