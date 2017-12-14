package repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Recipe;

@Repository
public class RecipeRepository{
	@Autowired private SessionFactory sessionFactory;	
	public List<Recipe> getRecipeList(){
		List<Recipe> recipeList = null;
		try {
			Session session= null;
			session = sessionFactory.openSession();
			session.beginTransaction();
			String hql = "from Recipe";
			Query query = session.createQuery(hql);
			recipeList = query.list();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return recipeList;	
	}
}