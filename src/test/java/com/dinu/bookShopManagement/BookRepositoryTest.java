package com.dinu.bookShopManagement;

import static org.assertj.core.api.Assertions.assertThat;

import com.dinu.bookShopManagement.entity.Author;
import com.dinu.bookShopManagement.entity.Book;
import com.dinu.bookShopManagement.entity.Category;
import com.dinu.bookShopManagement.entity.Publisher;
import com.dinu.bookShopManagement.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

    @DataJpaTest
    @AutoConfigureTestDatabase(replace = Replace.NONE)
    @Rollback(false)
    public class BookRepositoryTest {

        @Autowired
        private TestEntityManager entityManager;

        @Autowired
        private BookRepository bookRepository;


        @Test
        public void testCreateBook() {
          /*  Book book = new Book();
            book.setIsbn("T123");
            book.setName("TestJunit");
            book.setSerialName("TS123");
            book.setDescription("Test Book saving using JUnit");
            Book saveBook=bookRepository.save(book);
            Book exitBook=entityManager.find(Book.class,saveBook.getName());
            assertThat(book.getName()).isEqualTo(exitBook.getName());*/


            Book book = new Book("Test D123emo", "Test Demo book", "Test serial 2234name", "Test description");
            Author author = new Author("Test author demo", "Test description");
            Category category = new Category("Test category demo");
            Publisher publisher = new Publisher("Test publisher demo");

            book.addAuthors(author);
            book.addCategories(category);
            book.addPublishers(publisher);

            bookRepository.save(book);

        }

    }

