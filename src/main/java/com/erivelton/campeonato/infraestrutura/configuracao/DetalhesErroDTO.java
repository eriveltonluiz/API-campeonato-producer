package com.erivelton.campeonato.infraestrutura.configuracao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class DetalhesErroDTO {

    private String field;

    private String message;
}
