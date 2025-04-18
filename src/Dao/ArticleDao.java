package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.museum.utils.JdbcUtils;

import entity.Article;
import entity.Category;


public class ArticleDao implements JdbcDao<Article, Integer> {

	private Connection con;
	private String query;
	private PreparedStatement pstm;
	private Statement stm;
	
	public ArticleDao() throws SQLException {
		con = JdbcUtils.getDbConnection();
	}
	@Override
	public Boolean save(Article article) throws SQLException {
		query = "insert into article values(?,?,?,?,?);";
		pstm = con.prepareStatement(query);
		pstm.setInt(1, 0);
		pstm.setString(2, article.getName());
		pstm.setString(3, article.getCategory().toString());
		pstm.setDate(4, Date.valueOf(article.getCreatedDate()));
		pstm.setString(5, article.getCreatorName());
		int count = pstm.executeUpdate();
		if(count>0)
		{
			System.out.println(count + "row inserted");
		return true;
	}
	else
		return false;
}
		
	

	@Override
	public Collection<Article> findAll() throws SQLException {
		List<Article> articles = new ArrayList<Article>();
		query = "select * from article;";
		stm = con.createStatement();
		ResultSet rs = stm.executeQuery(query);
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			Category category = Category.valueOf(rs.getString("category"));
			LocalDate date = rs.getDate("date_created").toLocalDate();
			String creatorName = rs.getString("creator_name");
			Article article = new Article(id,name,category,date,creatorName);		
			articles.add(article);
		}
		
		return articles;
	}

	@Override
	public Article findById(Integer key) throws SQLException {
		Article foundArticle = null;
		query = "select * from article where id = ?";
		pstm = con.prepareStatement(query);
		pstm.setInt(1, key);
		ResultSet rs =  pstm.executeQuery();
		while(rs.next())
		{
			int id = rs.getInt("id");
			String name = rs.getString("name");
			Category category = Category.valueOf(rs.getString("category"));
			LocalDate date = rs.getDate("date_created").toLocalDate();
			String creatorName = rs.getString("creator_name");
			foundArticle = new Article(id,name,category,date,creatorName);
		}
			
		return foundArticle;
	}
	
	@Override
	public void update(Article article) throws SQLException{
		query = "update article set name = ?, category = ?,date_created  = ?,creator_name = ? where  id  = ?";
		pstm = con.prepareStatement(query);
		pstm.setString(1, article.getName());
		pstm.setString(2, article.getCategory().toString());
		pstm.setDate(3, Date.valueOf(article.getCreatedDate()));
		pstm.setString(4, article.getCreatorName());
		pstm.setInt(5, article.getId());
		int count = pstm.executeUpdate();

		if (count > 0) {
			System.out.println(count + " rows affected!");
			
		}
	}
		
		@Override
		public Boolean delete(Integer id) throws SQLException {
			query = "delete from article where id = ?";
			pstm = con.prepareStatement(query);
			pstm.setInt(1, id);

			int count = pstm.executeUpdate();

			if (count > 0) {
				System.out.println(count + " rows affected!");
				return true;
			}
			return false;
		


		
		
	}
	

}
