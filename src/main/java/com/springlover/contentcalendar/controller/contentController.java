package com.springlover.contentcalendar.controller;

import com.springlover.contentcalendar.model.Content;
import com.springlover.contentcalendar.repository.ContentCollectionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class contentController {

    private final ContentCollectionRepository repository;

    public contentController(ContentCollectionRepository repository) {
        this.repository = repository;
    }



    // make a request and all the piece of content in the system.

    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }


    //CRUD :

    // get by id, use {} to get id dynamically

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Content Not Found"));
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create( @Valid @RequestBody Content content) { // @ RequestBody: annotation indicating a method parameter should bound the body of request
        repository.save(content);
    }


    //////UPDATE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content,@PathVariable Integer id) {
        if (!repository.existBtId(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content Not Found");
        }
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repository.delete(id);
    }
}
