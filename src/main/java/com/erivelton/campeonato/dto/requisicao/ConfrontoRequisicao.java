package com.erivelton.campeonato.dto.requisicao;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Introspected
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