package com.erivelton.campeonato.dto.requisicao;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Introspected
@NoArgsConstructor
@Getter
@ToString
public class DadosJogadorRequisicao {

    @NotBlank
    private String nome;

    @NotBlank
    private String rg;

    public DadosJogadorRequisicao(@NotBlank String nome, @NotBlank String rg) {
        this.nome = nome;
        this.rg = rg;
    }
}
