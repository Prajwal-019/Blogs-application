package com.Encora.Blogs_Project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/blog")
@Tag(name=" Blog Management",description="Blog endpoints",externalDocs = @ExternalDocumentation(url="https://google.com",description="MORE DETAIL INFO"))
public class BlogerController {

@Autowired
private Blog_service Bs;


	
@GetMapping

@Operation(summary="list of all Products ")


@ApiResponses(value={@ApiResponse(responseCode =
"200",description="Reciverd data from server", content
= @Content(mediaType="application/json",
schema= @Schema(implementation=Blog_info.class))),

@ApiResponse(responseCode =
"500",description="there is internal server error"),

@ApiResponse(responseCode = "400",description = "no product found") })
	 public List<Blog_info> getAllBloginfo() {
	        return Bs.getAllBloginfo();
	    }
	 
	 
		
		  @PostMapping public ResponseEntity<Blog_info> addBlog(@RequestBody Blog_info
		  blog) { // Blog_info savedProduct = new Blog_info();
		  
		  Blog_info savedProduct = Bs.addBlog(blog); return
		  ResponseEntity.status(HttpStatus.CREATED).body(savedProduct); }
		 
	 
	   
	    @DeleteMapping("/{Topic}")
	    public ResponseEntity<Void> deleteBlog(@RequestParam("id") Long id) {
	        Bs.deleteBlog(id);
	        return ResponseEntity.noContent().build();
	    }

		
		 @GetMapping("/{id}") public Optional<Blog_info> getProductById(
		 
		  @PathVariable("id") Long Blogid) { return Bs.getBlogById(Blogid); }
		 
	    @GetMapping("/name")
	    public ResponseEntity<List<Blog_info>> filterByName(@RequestParam("name") String name) {
	        List<Blog_info> blogs = Bs.filterByName(name);
	        return ResponseEntity.ok(blogs);
	    }
}
