package com.ifrn.biblioteca.controllers;

import com.ifrn.biblioteca.models.Book;
import com.ifrn.biblioteca.models.Loan;
import com.ifrn.biblioteca.models.Student;
import com.ifrn.biblioteca.repository.BookRepository;
import com.ifrn.biblioteca.repository.LoanRepository;
import com.ifrn.biblioteca.repository.StudentRepository;
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
@RequestMapping("/emprestimo")
public class LoanController {

    @Autowired
    private LoanRepository lr;
    @Autowired
    private StudentRepository sr;
    @Autowired
    private BookRepository br;

    @GetMapping("")
    public ModelAndView listAll() {
        List<Loan> loans = lr.findAll();
        ModelAndView mv = new ModelAndView("loans/listAll");
        mv.addObject("loans", loans);
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView addPage(Loan loan) {

        List<Student> students = sr.findAll();
        List<Book> books = br.findAll();

        return new ModelAndView("loans/add")
                .addObject("students", students)
                .addObject("books", books)
                .addObject("loan", loan);
    }

    @PostMapping("/adicionar")
    public ModelAndView add(@Valid @ModelAttribute("loan") Loan loan, BindingResult br, RedirectAttributes ra) {

        if (br.hasErrors()) {
            return addPage(loan);
        }
        
        if (lr.findByStudentCpf(loan.getStudent().getCpf()).size() >= 3) {
        	ra.addFlashAttribute("mensagem", "Aluno com 3 empréstimos ativos");
        	return new ModelAndView("redirect:/emprestimo");
        }
        
        if (!lr.findByBookId(loan.getBook().getId()).isEmpty()) {
        	ra.addFlashAttribute("mensagem", "Livro já com empréstimo ativo");
        	return new ModelAndView("redirect:/emprestimo");
        }

        lr.save(loan);
        ra.addFlashAttribute("mensagem", "Empréstimo salvo com sucesso");
        return new ModelAndView("redirect:/emprestimo");

    }

    @GetMapping("/{id}")
    public ModelAndView editLoan(@PathVariable Long id) {

        Optional<Loan> opt = lr.findById(id);
        ModelAndView md = new ModelAndView();

        if (opt.isEmpty()) {
            md.setViewName("redirect:emprestimo/");
            return md;
        }

        md.addObject("books", br.findAll());
        md.addObject("students", sr.findAll());
        md.addObject("loan", opt.get());
        md.setViewName("loans/add");
        return md;
    }

    @GetMapping("/deletar/{id}")
    public ModelAndView deleteLoan(@PathVariable Long id, RedirectAttributes ra) {

        lr.deleteById(id);
        ra.addFlashAttribute("mensagem", "Empréstimo Excluído com Sucesso");
        return new ModelAndView("redirect:/emprestimo");
    }


}
