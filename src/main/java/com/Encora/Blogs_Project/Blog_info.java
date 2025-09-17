package com.Encora.Blogs_Project;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Blog_info")
public class Blog_info {
	
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	    private Long id;
	
	public Blog_info(Long id, String name, String date, String topic, String content) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.topic = topic;
		this.content = content;
	}
	
	public Blog_info() {
		
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String date;
	
	@Column(nullable=false)
	private String topic;
	
	@Column(nullable=false)
	private String content;
	
	public String getname() {
		return name;
		
	}
	public void setname(String name) {
		this.name=name;
	}
	
	public String getdate() {
		return date;
		
	}
	public void setdate(String date) {
		this.date=date;
	}
	public String getTopic() {
		return topic;
		
	}
	public void settopic(String Topic) {
		this.topic=Topic;
	}
	public String getcontent() {
		return content;
		
	}
	public void setcontent(String content) {
		this.content=content;
	}

	public Object thenReturn(Blog_info myBl) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
