package com.erivelton.campeonato.aplicacao.dto.requisicao;

import com.erivelton.campeonato.infraestrutura.validacao.VerificarGols;
import io.micronaut.core.annotation.Introspected;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Introspected
@NoArgsConstructor
@Getter
@ToString
@VerificarGols
public class DadosEquipeRequisicao {

    @NotBlank
    private String equipe;

    @NotNull
    @Valid
    private List<DadosJogadorRequisicao> jogadores = new ArrayList<>();

    public DadosEquipeRequisicao(@NotBlank String equipe) {
        this.equipe = equipe;
    }
}
