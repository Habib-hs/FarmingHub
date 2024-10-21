package com.project.farmingHub.repo;

import com.project.farmingHub.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}