package com.Encora.Blogs_Project;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.h2.command.dml.MergeUsing.When;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class BlogTest {

	
	@Mock
	Blog_Repo BR;
	
	@InjectMocks
	Blog_service BS;
	
	Blog_info B1;
	Blog_info B2;
	
	@BeforeEach
	public void setup() {
		B1=new Blog_info();
		B1.setId((long)1);
		B1.setname("kiran");
		B1.setcontent("playing Cricket");
		B1.setdate("20/7/2025");
		B1.settopic("Sports");
		
		
		B2=new Blog_info();
		B2.setId((long) 2);
		B2.setname("chintu");
		B2.setcontent("playing Cricket");
		B2.setdate("21/7/2025");
		B2.settopic("Sports");
	}
	
	
	@Test
	@DisplayName("test of get blog")
	public void getblog() {
		 when(BR.findAll()).thenReturn(new ArrayList<>(List.of(B1,B2)));
		
		 List<Blog_info>Blist=BS.getAllBloginfo();
		 
		 assertThat(Blist).isNotNull();
			assertThat(Blist.size()).isEqualTo(2);
			assertThat(Blist.get(0).getname()).isEqualTo("kiran");
			assertThat(Blist.get(0).getcontent()).isEqualTo("playing Cricket");
	}
	
	/*
	 * @Test
	 * 
	 * @DisplayName("test of get blog") public void getblogId() {
	 * when(BR.findById(2)).thenReturn(Optional.of(B2));
	 * 
	 * List<Blog_info>Blist=BS.getAllBloginfo();
	 * 
	 * assertThat(Blist).isNotNull(); assertThat(Blist.size()).isEqualTo(2);
	 * assertThat(Blist.get(0).getname()).isEqualTo("kiran");
	 * assertThat(Blist.get(0).getContent()).isEqualTo("playing Cricket"); }
	 */
	
	@Test
	@DisplayName("test add blog")
	public void addblog()
	{
		Blog_info myBl=new Blog_info();
		
		myBl.setname("Abhi");
		myBl.setcontent("Playing");
		myBl.setdate("20/09/2025");
		myBl.settopic("Game");
		
		when(BR.save(myBl)).thenReturn(myBl);
		var SaveBlog=BS.addBlog(myBl);
		
		 assertThat(SaveBlog).isNotNull();
			
			assertThat(SaveBlog.getname()).isEqualTo("Abhi");
			assertThat(SaveBlog.getcontent()).isEqualTo("Playing");
			assertThat(SaveBlog.getTopic()).isEqualTo("Game");
			assertThat(SaveBlog.getdate()).isEqualTo("20/09/2025");
			verify(BR,times(1)).save(myBl);
	}
	
	

}
