package com.novohoteldb.dto;

public record EnderecoDTO (
        String Rua,
        String Numero,
        String Bairro,
        String Cep,
        String fk_Pessoa_CPF
){
}
