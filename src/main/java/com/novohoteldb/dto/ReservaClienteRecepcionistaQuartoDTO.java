package com.novohoteldb.dto;

public record ReservaClienteRecepcionistaQuartoDTO(
        Integer id_reserva,
        Integer fk_Quarto_Numero,
        Integer fk_Recepcionista_fk_Funcionario_Id_Funcionario,
        String fk_Cliente_fk_Pessoa_CPF,
        String Check_in,
        String Check_out
) {
}
