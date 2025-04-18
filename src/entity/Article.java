package entity;

import java.time.LocalDate;

public class Article {
	private Integer id;
	private String name;
	private Category category;
	private LocalDate createdDate;
	private String creatorName;
	public Article() {
		// TODO Auto-generated constructor stub
	}
	public Article(Integer id, String name, Category category, LocalDate createdDate, String creatorName) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.createdDate = createdDate;
		this.creatorName = creatorName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", category=" + category + ", creatorName=" + creatorName + "]";
	}

	
}
