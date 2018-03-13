package com.example.Bookstore.controller;

import java.util.List;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.BookRepository;
import com.example.Bookstore.model.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// Show all students
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
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
	
	/*@RequestMapping(value = "/edit/{id}")
	public String addBook(@PathVariable("id") Long bookId, Model model){
		model.addAttribute("book", repository.findOne(bookId));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
}*/
	
	/*@RequestMapping(value = "/saveid/{id}", method = RequestMethod.POST)
	public ModelAndView showForm(@PathVariable("id") Long id) {
		Book book = repository.findOne(id);
		return new ModelAndView("updatebooking", "booking", book); }
	
	@RequestMapping(value = "/saveidd/{id}", method = RequestMethod.POST)
	public String saveId(@PathVariable("id") Long bookId, Model model){
		Book book = repository.findOne(bookId);
		repository.save(book);
		return "redirect:../booklist";
	}
	
	@RequestMapping(value = "/saveid/{id}", method = RequestMethod.POST)
    public String saveId(@RequestBody Model model,@PathVariable Long id) {
		Book book = repository.findOne(id);
        repository.save(book);
        return "redirect:../booklist";
    }
	
	@RequestMapping(value = "/saveid/{id}", method = RequestMethod.PUT)
    public String saveId(@PathVariable("id")Long bookId , Model model) {
		Book book = repository.findOne(bookId);
        repository.save(book);
        return "redirect:../booklist";
    }*/

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.delete(bookId);
        return "redirect:../booklist";
    }
    
    // RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
        
    }    

	// RESTful service to get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId) {
    	//ServletOutputStream stream = null;
    	//stream = response.getOutputStream();
    	return repository.findOne(bookId);
    	//stream.getOutputStream().flush();
    	//stream.getOutputStream().close();
    }
	
}