package com.erivelton.campeonato.dto.requisicao;

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
    @Min(5)
    private Integer quantidadeMaximaJogadores;

    @NotNull
    @Valid
    @VerificarQuantidadeTimes
    private List<DadosEquipeRequisicao> dadosEquipe = new ArrayList<>();

    public DadosCampeonatoRequisicao(Integer quantidadeMaximaJogadores) {
        this.quantidadeMaximaJogadores = quantidadeMaximaJogadores;
    }
}
