package com.project.farmingHub;

import com.project.farmingHub.domain.Book;
import com.project.farmingHub.repo.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@EnableCaching
@SpringBootApplication
@RestController
@RequestMapping("/book")
@EntityScan(basePackages = {"com.project.annotations.entities" , "com.project.farmingHub.domain"})
@EnableJpaRepositories(basePackages = {"com.project.annotations.repositories" , "com.project.farmingHub.repo"})
public class FarmingHubApplication {


	private final BookRepository repository;

	FarmingHubApplication(BookRepository bookRepository){
		this.repository = bookRepository;
	}

	@PostMapping
	public Book saveBook(@RequestBody Book book){
		return repository.save(book);
	}

	@GetMapping
	public List<Book> getBooks(){
		return repository.findAll();
	}

	@GetMapping("/message")
	public String message(){
		return "Hello from docker...";
	}

	public static void main(String[] args) {
		SpringApplication.run(FarmingHubApplication.class, args);
		System.out.println("hello ! lets build an amazing project!");
	}

}
