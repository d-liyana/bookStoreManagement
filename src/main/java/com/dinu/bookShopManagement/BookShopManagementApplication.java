package com.dinu.bookShopManagement;

import com.dinu.bookShopManagement.entity.Book;
import com.dinu.bookShopManagement.repository.BookRepository;
import com.dinu.bookShopManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookShopManagementApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BookShopManagementApplication.class, args);
	}


	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

	}
}
