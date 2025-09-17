package com.Encora.Blogs_Project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Blog_service {
	@Autowired
	private Blog_Repo BR;

	
	   
	 
	    // Get all products
	    public List<Blog_info> getAllBloginfo() {
	        return BR.findAll();
	    }
	    
	    public  Blog_info addBlog(Blog_info blog) {
	        return BR.save(blog);
	    }
	 
	    // Delete product by ID
	    public  void  deleteBlog(Long id) {
	        BR.deleteById(id);
	    }
	   public Optional<Blog_info> getBlogById(Long id) {
	        return BR.findById(id);
	    }
	   public List<Blog_info> filterByName(String name) {
		    return BR.filterByName(name);
		}
}
