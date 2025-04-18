package service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import Dao.ArticleDao;
import entity.Article;
import entity.Category;
import exception.ResourceAlreadyExistException;
import exception.ResourseNotFoundException;

public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService() throws SQLException {
		articleDao = new ArticleDao();
	}
	
	public void addArticle(String name,Category category,LocalDate createdDate,String creatorName) throws SQLException
	{
		Optional<Article> existingArticle = articleDao.findAll().stream()
		.filter(article -> article.getName().equalsIgnoreCase(name))
		.findFirst();
		
		if(existingArticle.isEmpty())
		{
		
		Article newArticle = new Article(null, name, category, createdDate, creatorName);
		boolean status = articleDao.save(newArticle);
		if(status)
			System.out.println("Article added successfully...");
		else
			System.out.println("Article failed to add ...");
		}
		else {
			throw new ResourceAlreadyExistException("Article already exist with same name : "+name);
		}
	}

	public void displayAllArticles() throws SQLException
	{
		articleDao.findAll().stream().forEach(article -> System.out.println(article));

	}
	public void displayArticleDetails(Integer id) throws SQLException
	{
		Article foundArticle = articleDao.findById(id);
		if(foundArticle != null)
		{
			System.out.println(foundArticle);
		}else
			throw new ResourseNotFoundException("Article not found of id : " +id );
	}
	
	public void updateArticle(int id, String name, Category category, LocalDate createdDate, String creatorName) throws SQLException,ResourceNotFoundException {
		Article art = articleDao.findById(id);
		if (art != null) {
		Boolean status = articleDao.update(new Article(id, name, category, createdDate, creatorName));
		if (status)
			System.out.println("Article deleted Sucessfully!!");
		else
			System.out.println("Article failed to delete!!");
		}
		else
			throw new ResourceNotFoundException("Article not found with id " + id);
	
		
	}
	
	public void deleteArticle(int id) throws SQLException {
		Boolean status = articleDao.delete(id);
		if (status)
			System.out.println("Article deleted Sucessfully!!");
		else
			System.out.println("Article failed to delete!!");
		
	}



}
