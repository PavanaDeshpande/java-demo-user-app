package com.eazybytes.DemoUsersApplication.controller;

import com.eazybytes.DemoUsersApplication.model.Book;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Book getBook(@PathVariable Long id) {
        return new Book(id, "Spring Boot in Action", "Craig Walls");
    }
}
