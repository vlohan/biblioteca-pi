package com.ifrn.biblioteca.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank
    private Long cpf;
    @NotBlank
    private String registration;
    @NotBlank
    private String name;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    @NotBlank
    private String address;

    public Student() {
    }

    public Student(Long cpf, String registration, String name, LocalDate birthdate, String address) {
        this.cpf = cpf;
        this.registration = registration;
        this.name = name;
        this.birthdate = birthdate;
        this.address = address;

    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "cpf=" + cpf +
                ", registration='" + registration + '\'' +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", address='" + address + '\'' +
                '}';
    }

    /*public static boolean validateCPF(String cpf) {
        // Verificar se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Calcular os dígitos verificadores
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }
        int firstValidationDigit = 11 - (sum % 11);

        if (firstValidationDigit == 10 || firstValidationDigit == 11) {
            firstValidationDigit = 0;
        }

        if (firstValidationDigit != cpf.charAt(9) - '0') {
            return false;
        }

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }
        int secondValidationDigit = 11 - (sum % 11);

        if (secondValidationDigit == 10 || secondValidationDigit == 11) {
            secondValidationDigit = 0;
        }

        return secondValidationDigit == cpf.charAt(10) - '0';
    }*/

}
