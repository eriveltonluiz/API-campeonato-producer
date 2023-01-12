package com.erivelton.campeonato.infraestrutura.servico.validacao;

import com.erivelton.campeonato.dominio.ViolacaoVerificacaoQuantidadeMaximaJogadoresException;
import com.erivelton.campeonato.dto.requisicao.DadosCampeonatoRequisicao;
import com.erivelton.campeonato.dto.requisicao.DadosEquipeRequisicao;
import com.erivelton.campeonato.infraestrutura.servico.ElaboracaoConfrontoServico;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class ValidacaoQuantidadeMaximaJogadores implements ValidacaoCustomizada<DadosCampeonatoRequisicao> {

    private static final Logger LOG = LoggerFactory.getLogger(ElaboracaoConfrontoServico.class);

    @Override
    public void limite(DadosCampeonatoRequisicao valor){
//        valor.getDadosEquipe().removeIf(equipe -> equipe.quantidadeJogadores() >= valor.getQuantidadeMaximaJogadores());
        Map<String, String> violacoes = new HashMap<>(
                valor.getDadosEquipe().stream()
                        .filter(equipe -> equipe.quantidadeJogadores() > valor.getQuantidadeMaximaJogadores())
                        .collect(Collectors.toMap(DadosEquipeRequisicao::getEquipe, violacao -> "ultrapassou a quantidade máxima de jogadores inscritos"))
        );

        if(violacoes.isEmpty()){
            LOG.debug("Dados do campeonato recebido: {} foram validados com sucesso", valor);
            return ;
        }

        LOG.error("Dados do campeonato não são válidos: {}", violacoes.entrySet().stream()
                .map(violacao -> violacao.getKey() + " " + violacao.getValue())
                .collect(Collectors.toList())
        );

        ViolacaoVerificacaoQuantidadeMaximaJogadoresException violacaoVerificacaoQuantidadeMaximaJogadoresException = new ViolacaoVerificacaoQuantidadeMaximaJogadoresException("Violação no limite de inscrição de jogadores");
        violacaoVerificacaoQuantidadeMaximaJogadoresException.adicionarViolacoes(violacoes);

        throw violacaoVerificacaoQuantidadeMaximaJogadoresException;
    }

}
