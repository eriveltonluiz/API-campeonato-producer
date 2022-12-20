package com.erivelton.campeonato.infraestrutura.servico.validacao;

import com.erivelton.campeonato.dominio.ViolacaoVerificacaoQuantidadeMaximaJogadoresException;
import com.erivelton.campeonato.dto.requisicao.DadosCampeonatoRequisicao;
import com.erivelton.campeonato.dto.requisicao.DadosEquipeRequisicao;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class ValidacaoQuantidadeMaximaJogadores implements ValidacaoCustomizada<DadosCampeonatoRequisicao> {

    @Override
    public void limite(DadosCampeonatoRequisicao valor){
        valor.getDadosEquipe().removeIf(equipe -> equipe.quantidadeJogadores() >= valor.getQuantidadeMaximaJogadores());
        Map<String, String> violacoes = new HashMap<>(
                valor.getDadosEquipe().stream()
                        .collect(Collectors.toMap(DadosEquipeRequisicao::getEquipe, violacao -> "ultrapassou a quantidade máxima de jogadores inscritos"))
        );

        if(violacoes.isEmpty()){
            return ;
        }

        ViolacaoVerificacaoQuantidadeMaximaJogadoresException violacaoVerificacaoQuantidadeMaximaJogadoresException = new ViolacaoVerificacaoQuantidadeMaximaJogadoresException("Violação no limite de inscrição de jogadores");
        violacaoVerificacaoQuantidadeMaximaJogadoresException.adicionarViolacoes(violacoes);

        throw violacaoVerificacaoQuantidadeMaximaJogadoresException;
    }

}
