package com.Encora.Blogs_Project.Repotest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Encora.Blogs_Project.Blog_info;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class BlogControllerRepo {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	@DisplayName("Integration test - POST and GET product through REST API")
	public void createAndGetBlogTest() {
		Blog_info Blog = new Blog_info();
		Blog.setname("Kiran");
		Blog.setcontent("Playing");
		Blog.setdate("15/11/2025");
		Blog.settopic("Sports");
		
		// post product
		
		ResponseEntity<Blog_info> postResponse = restTemplate.postForEntity("/blog", Blog, Blog_info.class);
		assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		Blog_info created = postResponse.getBody();
		assertThat(created).isNotNull();  
		assertThat(created.getname()).isEqualTo("Kiran");
		
		// get product by id
		/*
		 * ResponseEntity<Blog_info> getResponse =
		 * restTemplate.getForEntity("/blog/"+created.getId(), Blog_info.class);
		 * assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		 * assertThat(getResponse.getBody().getname()).isEqualTo("Kiran");
		 */
		
	}	
	}