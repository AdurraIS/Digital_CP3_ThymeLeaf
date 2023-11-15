package com.cp3.apicp3.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String marca;

    private String modelo;

    private String ano;

    private String cor;


}
