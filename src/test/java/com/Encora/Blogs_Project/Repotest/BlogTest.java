package com.Encora.Blogs_Project.Repotest;


import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.Encora.Blogs_Project.Blog_Repo;
import com.Encora.Blogs_Project.Blog_info;
 

 
@DataJpaTest
public class BlogTest {
    @Autowired
    private Blog_Repo blogRepository;
 
    @Autowired
    private TestEntityManager entityManager;
 
    
 
    private Blog_info blog;
 
    @BeforeEach
    public void setup() throws Exception {
        blog = new Blog_info();
        blog.setname("Test Blog");
        blog.settopic("Testing Topic");
        blog.setcontent("This is a test content.");
        blog.setdate("2024-01-14");
    }
 
    @Test
    @DisplayName("Integration Test - Save Blog")
    public void testSaveBlog() {
        Blog_info saved = blogRepository.save(blog);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }
 
    @Test
    @DisplayName("Integration Test - Find by ID")
    public void testFindById() {
        Blog_info saved = entityManager.persistFlushFind(blog);
        Optional<Blog_info> found = blogRepository.findById(saved.getId());
 
        assertThat(found).isPresent();
        assertThat(found.get().getname()).isEqualTo("Test Blog");
    }
 
    @Test
    @DisplayName("Integration Test - Find by Name (Custom Query)")
    public void testFilterByName() {
        blog.setname("Unique Blog Name");
        entityManager.persistAndFlush(blog);
 
        List<Blog_info> filtered = blogRepository.filterByName("Unique Blog Name");
 
        assertThat(filtered).hasSize(1);
        assertThat(filtered.get(0).getname()).isEqualTo("Unique Blog Name");
    }
 
    @Test
    @DisplayName("Integration Test - Delete by ID")
    public void testDeleteById() {
        Blog_info saved = entityManager.persistAndFlush(blog);
        blogRepository.deleteById(saved.getId());
 
        Optional<Blog_info> found = blogRepository.findById(saved.getId());
        assertThat(found).isEmpty();
    }
}
