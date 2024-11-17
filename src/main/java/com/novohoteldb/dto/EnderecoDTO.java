package com.novohoteldb.dto;

public record EnderecoDTO (
        Integer Endereco_PK,
        String Rua,
        String Numero,
        String Bairro,
        String Cep,
        String fk_Pessoa_CPF
){
}
