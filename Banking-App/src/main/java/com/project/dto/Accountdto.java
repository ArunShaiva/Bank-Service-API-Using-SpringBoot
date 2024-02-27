package com.project.dto;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Accountdto {
    private Long id;
    private String accountHolderName;
    private double balance;


}
