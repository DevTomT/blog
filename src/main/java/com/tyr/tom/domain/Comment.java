package com.tyr.tom.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue
	private long id;

	@NotNull(message = "{Comment.content.NotNull}")
	@Size(max = 5000, message = "{Comment.content.Size}")
	@Column(nullable = false, length = 5000)
	private String content;

	@NotNull(message = "{Comment.signature.NotNull}")
	@Size(max = 50, message = "{Comment.signature.Size}")
	@Column(nullable = false, length = 50)
	private String signature;

	@Column(name = "creation_date", nullable = false, updatable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date creationDate = new Date();

	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false, insertable = true)
	private Post post;

	public Comment() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
