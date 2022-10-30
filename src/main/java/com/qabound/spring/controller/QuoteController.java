package com.qabound.spring.controller;

import com.qabound.spring.exception.QuoteNotFoundException;
import com.qabound.spring.model.Quote;
import com.qabound.spring.service.QuoteService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("api/v1/quotes")
@Log4j2
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping
    public Iterable<Quote> getAllQuotes() {
        log.info("/request recieved GET-ALL");
        Iterable<Quote> all = quoteService.getAll();
        log.info("/response sent GET-ALL");
        return all;
    }

    @GetMapping("/{quoteId}")
    public Quote getQuoteById(@PathVariable String quoteId) throws QuoteNotFoundException {
        Optional<Quote> responseQuote = quoteService.getById(quoteId);
        if (responseQuote.isPresent()) {
            return responseQuote.get();
        } else {
            throw new QuoteNotFoundException("Quote not found");
        }
    }

    @PostMapping
    public Quote saveQuote(@RequestBody Quote quote) {
        return quoteService.save(quote);
    }

    @PutMapping("/{quoteId}")
    public Quote updateQuote(@PathVariable String quoteId, @RequestBody Quote quote) throws QuoteNotFoundException {
        return quoteService.update(quoteId, quote);
    }

    @DeleteMapping("/{quoteId}")
    public void deleteQuote(@PathVariable String quoteId) {
        quoteService.delete(quoteId);
    }
}
