package com.novohoteldb.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Pessoa {
    private String cpf;
    private String nome;
    private String email;
    private String telefone1;
    private String telefone2;
}