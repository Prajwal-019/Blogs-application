package com.Encora.Blogs_Project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;




public interface Blog_Repo extends JpaRepository<Blog_info,Long> {

	@Query("SELECT b FROM Blog_info b WHERE b.name = :name")
	List<Blog_info >filterByName(@Param("name") String name);


	

}
