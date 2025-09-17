package com.Encora.Blogs_Project;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;

@WebMvcTest(BlogerController.class)
@AutoConfigureMockMvc(addFilters = false)


public class BlogControllerTest {


	@Autowired
	public MockMvc mockmvc;
	
	private Blog_info blog_info;
	
	@MockBean
	private Blog_service BS;
	
	@BeforeEach
	void setup()
	{
		blog_info =new Blog_info();
		blog_info.setId(1L);
		blog_info.setname("kiran");
		blog_info.settopic("Sports");
		blog_info.setdate("20/09/2025");
		blog_info.setcontent("plying");
		
	}
	
	
	@Test
	@DisplayName("GET--------/Blog GET PRODUCTS ENDPOINT")
	public void getBlogEndPoint()throws Exception
	{
		
		when(BS.getAllBloginfo()).thenReturn(List.of(blog_info));
		
		mockmvc.perform(get("/blog"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].name").value("kiran"));
	}
	
	
	/*
	 * @Test
	 * 
	 * @DisplayName("get -- /products/id GET PRODUCTS BY ID ENDPOINT") public void
	 * getProductByIdEndPointTest() throws Exception {
	 * when(BS.getBlogById(1L)).thenReturn(Optional.of(product));
	 * 
	 * mockmvc.perform(get("/products/1")) .andExpect(status().isOk())
	 * .andExpect(jsonPath("$.name").value("laptop"))
	 * .andExpect(jsonPath("$.price").value(20000.00)); }
	 */
	
	@Test
	@DisplayName("POST -- /blog POST blog info")
	public void addBlogEndpointTest() throws Exception {
	    var myBlog = new Blog_info(3L, "Kiran", "20/2/2025", "Sports", "playing");

	    var jsonRequest = """
	        {"name":"Kiran","date":"20/2/2025","topic":"Sports","content":"playing"}
	        """;

	    when(BS.addBlog(any(Blog_info.class))).thenReturn(myBlog);

	    mockmvc.perform(post("/blog")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(jsonRequest))
	        .andExpect(status().isCreated())
	        .andExpect(jsonPath("$.name").value("Kiran"))
	        .andExpect(jsonPath("$.topic").value("Sports"))
	        .andExpect(jsonPath("$.date").value("20/2/2025"))
	        .andExpect(jsonPath("$.content").value("playing"));
	}

	
	
}