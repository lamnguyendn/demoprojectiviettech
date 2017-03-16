package com.ivt.demo.controller;

import com.ivt.demo.entity.BookEntity;
import com.ivt.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    @Autowired
    BookRepository bookRepository;

    @RequestMapping(method = GET)
    public String home(Model model) {
        model.addAttribute("bookList", bookRepository.getBooks(1));
        return "home";
    }

    @RequestMapping(value = "/book/{page}", method = POST)
    @ResponseBody
    public String getData(@PathVariable int page) {
        String result = "";
        List<BookEntity> data = bookRepository.getBooks(page);
        for (BookEntity book: data) {
            result += "<tr><td>" + book.getId() + "</td>" +
                    "<td>" + book.getName() + "</td>" +
                    "<td>" + book.getAuthor() + "</td>" +
                    "<td>" + book.getPrice() + "</td>" +
                    "<td>" + book.getIsbn() + "</td></tr>";
        }
        return result;
    }
}
