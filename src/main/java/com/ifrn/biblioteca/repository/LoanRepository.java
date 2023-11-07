package com.ifrn.biblioteca.repository;

import com.ifrn.biblioteca.models.Loan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
	
	List<Loan> findByStudentCpf(Long cpf);
	List<Loan> findByBookId(Long id);
	
}
