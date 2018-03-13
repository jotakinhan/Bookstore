package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Bookstore.model.Category;
import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.BookRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    public void findByAuthorShouldReturnBook() {
        List<Book> books = repository.findByAuthor("Johnson");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Otsikko1");
    }
    
    @Test
    public void createNewBook() {
    	Book book = new Book("Otsikko1", "Johnson", 1989, "951-98548-9-4", 20.00, new Category ("Scientific"));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    

}