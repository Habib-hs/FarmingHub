package com.project.farmingHub;

import com.project.farmingHub.entity.Book;
import com.project.farmingHub.repo.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/book")
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
