package com.novohoteldb.dto;

public record CadastroDTO(
        String cpf,
        String nome,
        String email,
        String telefone1,
        String telefone2,
        Integer fk_Endereco_PK
) {
}
