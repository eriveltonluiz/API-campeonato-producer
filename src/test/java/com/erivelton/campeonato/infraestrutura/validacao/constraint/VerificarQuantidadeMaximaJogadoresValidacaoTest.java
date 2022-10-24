package com.erivelton.campeonato.infraestrutura.validacao.constraint;

import com.erivelton.campeonato.dominio.ViolacaoVerificacaoQuantidadeMaximaJogadoresException;
import com.erivelton.campeonato.dto.requisicao.DadosCampeonatoRequisicao;
import com.erivelton.campeonato.dto.requisicao.DadosEquipeRequisicao;
import com.erivelton.campeonato.dto.requisicao.DadosJogadorRequisicao;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class VerificarQuantidadeMaximaJogadoresValidacaoTest {

    @Inject
    private VerificarQuantidadeMaximaJogadoresValidacao verificarQuantidadeMaximaJogadoresValidacao;

    @Test
    void deveValidarQuandoQuantidadeDeJogadoresDosTimesEstiverDentroDoLimite(){
        DadosCampeonatoRequisicao dadosCampeonato = dadosCampeonato(3);
        assertTrue(verificarQuantidadeMaximaJogadoresValidacao.isValid(dadosCampeonato, null, null));
    }

    @Test
    void deveInvalidarQuandoQuantidadeDeJogadoresDosTimesUltrapassarOLimiteDeInscricoes(){
        DadosCampeonatoRequisicao dadosCampeonato = dadosCampeonato(5);

        ViolacaoVerificacaoQuantidadeMaximaJogadoresException violacaoVerificacaoQuantidadeMaximaJogadoresException =
                assertThrows(ViolacaoVerificacaoQuantidadeMaximaJogadoresException.class,
                        () -> verificarQuantidadeMaximaJogadoresValidacao.isValid(dadosCampeonato, null, null)
                );

        List<String> violacoesEsperadas = new ArrayList<>(
                dadosCampeonato.getDadosEquipe().stream()
                        .map(equipe -> String.format("%s ultrapassou a quantidade máxima de jogadores inscritos", equipe.getEquipe()))
                        .collect(Collectors.toList())
        );

        assertEquals(violacoesEsperadas, violacaoVerificacaoQuantidadeMaximaJogadoresException.getViolacoes());
    }

    private DadosCampeonatoRequisicao dadosCampeonato(Integer quantidadeMaximaJogadores) {
        DadosCampeonatoRequisicao dadosCampeonato = new DadosCampeonatoRequisicao(quantidadeMaximaJogadores);

        List<DadosJogadorRequisicao> jogadoresTime1 = new ArrayList<>(
                Arrays.asList(
                        new DadosJogadorRequisicao("tal1", "08947324"),
                        new DadosJogadorRequisicao("tal2", "78534543"),
                        new DadosJogadorRequisicao("tal3", "16327433")
                )
        );

        List<DadosJogadorRequisicao> jogadoresTime2 = new ArrayList<>(
                Arrays.asList(
                        new DadosJogadorRequisicao("tal4", "08947324"),
                        new DadosJogadorRequisicao("tal5", "78534543"),
                        new DadosJogadorRequisicao("tal6", "16327433")
                )
        );
        List<DadosEquipeRequisicao> dadosEquipe = new ArrayList<>(
                Arrays.asList(
                        new DadosEquipeRequisicao("prim1"),
                        new DadosEquipeRequisicao("prim2")
                )
        );

        dadosEquipe.get(0).adiconarJoagadores(jogadoresTime1);
        dadosEquipe.get(1).adiconarJoagadores(jogadoresTime2);

        dadosCampeonato.adicionarDadosEquipe(dadosEquipe);

        return dadosCampeonato;
    }

}