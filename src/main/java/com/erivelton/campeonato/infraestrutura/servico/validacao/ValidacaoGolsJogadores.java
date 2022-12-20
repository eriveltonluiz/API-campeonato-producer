package com.erivelton.campeonato.infraestrutura.servico.validacao;

import com.erivelton.campeonato.dominio.ViolacaoVerificacaoGolsException;
import com.erivelton.campeonato.dominio.ViolacaoVerificacaoQuantidadeMaximaJogadoresException;
import com.erivelton.campeonato.dto.requisicao.ConfrontoRequisicao;
import com.erivelton.campeonato.dto.requisicao.DadosCampeonatoRequisicao;
import com.erivelton.campeonato.dto.requisicao.DadosEquipeRequisicao;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class ValidacaoGolsJogadores implements ValidacaoCustomizada<ConfrontoRequisicao> {

    @Override
    public void limite(ConfrontoRequisicao valor){
        if(valor.getGolsJogadoresMandante().isEmpty() && !valor.getGolsMandante().equals(0))
            throw new ViolacaoVerificacaoGolsException("Gols de jogadores do time mandante devem ser inseridos!");

        if(valor.getGolsJogadoresVisitante().isEmpty() && !valor.getGolsVisitante().equals(0))
            throw new ViolacaoVerificacaoGolsException("Gols de jogadores do time visitante devem ser inseridos!");

        if(!valor.getGolsMandante().equals(valor.getGolsJogadoresMandante().values().stream().reduce(0, Integer::sum)))
            throw new ViolacaoVerificacaoGolsException("Gols de jogadores do time mandante devem ser condizentes com o número de gols marcados pela equipe!");

        if(!valor.getGolsVisitante().equals(valor.getGolsJogadoresVisitante().values().stream().reduce(0, Integer::sum)))
            throw new ViolacaoVerificacaoGolsException("Gols de jogadores do time visitante devem ser condizentes com o número de gols marcados pela equipe!");

    }

}
