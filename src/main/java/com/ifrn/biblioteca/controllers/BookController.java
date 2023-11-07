package com.ifrn.biblioteca.controllers;

import com.ifrn.biblioteca.models.Book;
import com.ifrn.biblioteca.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/livro")
public class BookController {

    @Autowired
    private BookRepository br;

    @GetMapping("")
    public ModelAndView listAll() {
        List<Book> books = br.findAll();
        ModelAndView mv = new ModelAndView("books/listAll");
        mv.addObject("books", books);
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView addPage(Book book) {
        return new ModelAndView("books/add").addObject("book", book);
    }

    @PostMapping("/adicionar")
    public ModelAndView add(@Valid @ModelAttribute("book") Book book, BindingResult br, RedirectAttributes ra) {

        if (br.hasErrors()) {
            return addPage(book);
        }

        this.br.save(book);
        ra.addFlashAttribute("mensagem", "Livro salvo com sucesso");
        return new ModelAndView("redirect:/livro");

    }

    @GetMapping("/{id}")
    public ModelAndView editBook(@PathVariable Long id) {

        Optional<Book> opt = br.findById(id);
        ModelAndView md = new ModelAndView();

        if (opt.isEmpty()) {
            md.setViewName("redirect:livro/");
            return md;
        }

        md.addObject("book", opt.get());
        md.setViewName("books/add");
        return md;
    }

    @GetMapping("/deletar/{id}")
    public ModelAndView deleteBook(@PathVariable Long id, RedirectAttributes ra) {

        br.deleteById(id);
        ra.addFlashAttribute("mensagem", "Livro Excluído com Sucesso");
        return new ModelAndView("redirect:/livro");
    }


}
