package com.tyr.tom.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue
	private long id;

	@NotNull(message = "{Category.categoryName.NotNull}")
	@Size(min = 1, max = 250, message = "{Category.categoryName.Size}")
	@Column(name = "category_name", unique = true, nullable = false)
	private String categoryName;

	@Size(max = 500, message = "{Category.description.Size}")
	@Column(length = 500)
	private String description;

	@OneToMany(mappedBy = "category", orphanRemoval = true)
	private List<Post> postList;

	public Category() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
