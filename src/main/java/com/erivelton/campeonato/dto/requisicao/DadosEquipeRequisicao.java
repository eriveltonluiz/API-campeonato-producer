package com.erivelton.campeonato.dto.requisicao;

import com.erivelton.campeonato.infraestrutura.validacao.anotacao.VerificarGols;
import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Introspected
@NoArgsConstructor
@Getter
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

    public int quantidadeJogadores(){
        return jogadores.size();
    }

    public void adiconarJoagadores(List<DadosJogadorRequisicao> dadosJogador){
        this.jogadores.addAll(dadosJogador);
    }
}
