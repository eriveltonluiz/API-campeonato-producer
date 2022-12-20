package com.erivelton.campeonato.dto.requisicao;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

@Introspected
@NoArgsConstructor
@Getter
public class DadosJogadorRequisicao {

    @NotBlank(groups = Default.class)
    private String nome;

    @NotBlank(groups = Default.class)
    private String rg;

    public DadosJogadorRequisicao(@NotBlank String nome, @NotBlank String rg) {
        this.nome = nome;
        this.rg = rg;
    }
}
