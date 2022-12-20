package com.erivelton.campeonato.dto.requisicao;

import com.erivelton.campeonato.infraestrutura.validacao.anotacao.VerificarQuantidadeMaximaJogadores;
import com.erivelton.campeonato.infraestrutura.validacao.anotacao.VerificarQuantidadeTimes;
import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Introspected
public class DadosCampeonatoRequisicao {

    @NotNull
    @Min(6)
    private Integer quantidadeMaximaJogadores;

    @NotNull
    @VerificarQuantidadeTimes
    @Valid
    private List<DadosEquipeRequisicao> dadosEquipe = new ArrayList<>();

    public DadosCampeonatoRequisicao(Integer quantidadeMaximaJogadores) {
        this.quantidadeMaximaJogadores = quantidadeMaximaJogadores;
    }

    public void adicionarDadosEquipe(List<DadosEquipeRequisicao> dadosCampeonato){
        this.dadosEquipe.addAll(dadosCampeonato);
    }
}
