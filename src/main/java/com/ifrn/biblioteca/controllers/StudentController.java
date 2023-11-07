package com.ifrn.biblioteca.controllers;

import com.ifrn.biblioteca.models.Student;
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
@RequestMapping("/aluno")
public class StudentController {

    @Autowired
    private StudentRepository sr;

    @GetMapping("")
    public ModelAndView listAll() {
        List<Student> students = sr.findAll();
        ModelAndView mv = new ModelAndView("students/listAll");
        mv.addObject("students", students);
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView addPage(Student student) {
        return new ModelAndView("students/add").addObject("student", student);
    }

    @PostMapping("/adicionar")
    public ModelAndView add(@Valid @ModelAttribute("student") Student student, BindingResult br, RedirectAttributes ra) {

        if (br.hasErrors()) {
            return addPage(student);
        }

        sr.save(student);
        ra.addFlashAttribute("mensagem", "Estudante salvo com sucesso");
        return new ModelAndView("redirect:/aluno");

    }

    @GetMapping("/{cpf}")
    public ModelAndView editStudent(@PathVariable Long cpf) {

        Optional<Student> opt = sr.findById(cpf);
        ModelAndView md = new ModelAndView();

        if (opt.isEmpty()) {
            md.setViewName("redirect:aluno/");
            return md;
        }

        md.addObject("student", opt.get());
        md.setViewName("students/add");
        return md;
    }

    @GetMapping("/deletar/{cpf}")
    public ModelAndView deleteStudent(@PathVariable Long cpf, RedirectAttributes ra) {

        sr.deleteById(cpf);
        ra.addFlashAttribute("mensagem", "Aluno Excluído com Sucesso");
        return new ModelAndView("redirect:/aluno");
    }

}
