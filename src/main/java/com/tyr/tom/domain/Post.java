package com.tyr.tom.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue
	private long id;

	@NotNull(message = "{Post.title.NotNull}")
	@Size(max = 500, message = "{Post.title.size}")
	@Column(nullable = false, length = 500)
	private String title;

	@NotNull(message = "{Post.content.NotNull}")
	@Size(max = 5000, message = "{Post.content.size}")
	@Column(nullable = false, length = 5000)
	private String content;

	@Column(name = "creation_date", nullable = false, updatable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date creationDate = new Date();

	@NotNull(message = "{Post.category.NotNull}")
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false, insertable = true)
	private Category category;

	@OneToMany(mappedBy = "post", orphanRemoval = true)
	private List<Comment> commentList;

	public Post() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

}
