package com.example.Bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.BookRepository;
import com.example.Bookstore.model.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping("/index")
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/booklist")
    public String bookList(Model model) {	
		List<Book> books = repository.findAll();
		model.addAttribute("books", books);
        return "booklist";
    }
  
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", crepository.findAll());
        return "add";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model) {
    	Book book = repository.findOne(bookId);
    	model.addAttribute("book", book);
    	model.addAttribute("categories", crepository.findAll());
    	
        return "edit";
    }

	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }   
	
	@RequestMapping(value = "/saveid/{id}", method = RequestMethod.POST)
    public String saveId(@PathVariable("id") Long bookId, Model model) {
		Book book = repository.findOne(bookId);
        repository.save(book);
        return "redirect:../booklist";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.delete(bookId);
        return "redirect:../booklist";
    }
	
}