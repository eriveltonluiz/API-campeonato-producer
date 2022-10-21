package com.erivelton.campeonato.dto.requisicao;

import com.erivelton.campeonato.infraestrutura.validacao.anotacao.VerificarGols;
import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
@SuperBuilder
@Introspected
@VerificarGols
public class ConfrontoRequisicao {

    @NotNull
    private Long identificacao;

    @NotNull
    private Integer golsMandante;

    @NotNull
    private Integer golsVisitante;

    private Map<String, Integer> golsJogadoresMandante = new HashMap<>();

    private Map<String, Integer> golsJogadoresVisitante = new HashMap<>();

    public ConfrontoRequisicao(Long identificacao, Integer golsMandante, Integer golsVisitante) {
        this.identificacao = identificacao;
        this.golsMandante = golsMandante;
        this.golsVisitante = golsVisitante;
    }

}