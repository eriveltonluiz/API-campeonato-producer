package com.erivelton.campeonato.controle;

import com.erivelton.campeonato.dto.requisicao.DadosCampeonatoRequisicao;
import com.erivelton.campeonato.dto.requisicao.DadosEquipeRequisicao;
import com.erivelton.campeonato.dto.requisicao.DadosJogadorRequisicao;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class CriacaoCampeonatoControllerTest {

    @Inject
    @Client("/")
    private HttpClient httpClient;

    @Test
    public void deveRegistrarCriacaoCampeonato(){
        DadosCampeonatoRequisicao dadosCampeonatoRequisicao = dadosCampeonato(6);

        MutableHttpRequest<DadosCampeonatoRequisicao> requisicao = HttpRequest.POST("/api/campeonato", dadosCampeonatoRequisicao);
        HttpResponse<DadosCampeonatoRequisicao> resposta = httpClient.toBlocking().exchange(requisicao, DadosCampeonatoRequisicao.class);

        assertNull(resposta.body());
        assertEquals(HttpStatus.CREATED, resposta.getStatus());
    }

    @Test
    public void deveInvalidarQuandoQuantidadeMaximaDeJogadoresForMenorQueOMinimoEsperado(){
        DadosCampeonatoRequisicao dadosCampeonatoRequisicao = dadosCampeonato(5);

        ConstraintViolationException constraintViolationException = chamadaComExcecao(dadosCampeonatoRequisicao);


    }

    @Test
    public void deveInvalidarQuandoQuantidadeMaximaDeJogadoresForNulo(){
        DadosCampeonatoRequisicao dadosCampeonatoRequisicao = dadosCampeonato(null);

        ConstraintViolationException constraintViolationException = chamadaComExcecao(dadosCampeonatoRequisicao);

    }

    @Test
    public void deveInvalidarQuandoDadosDaEquipeForNulo(){
        DadosCampeonatoRequisicao dadosCampeonatoRequisicao = new DadosCampeonatoRequisicao(6);

        ConstraintViolationException constraintViolationException = chamadaComExcecao(dadosCampeonatoRequisicao);


    }

    @Test
    public void deveInvalidarQuandoQuantidadeDeTimesNaoSatisfazerOModeloDeProgressaoGeometricaDe2(){
        DadosCampeonatoRequisicao dadosCampeonatoRequisicao = dadosCampeonato(6);
        List<DadosJogadorRequisicao> jogadoresTime3 = new ArrayList<>(
                Arrays.asList(
                        new DadosJogadorRequisicao("teste1", "18947324"),
                        new DadosJogadorRequisicao("teste2", "68947324"),
                        new DadosJogadorRequisicao("teste3", "78947324"),
                        new DadosJogadorRequisicao("teste4", "38534543"),
                        new DadosJogadorRequisicao("teste5", "146327433"),
                        new DadosJogadorRequisicao("teste6", "146927433")
                )
        );

        dadosCampeonatoRequisicao.getDadosEquipe().add(new DadosEquipeRequisicao("prim3"));
        dadosCampeonatoRequisicao.getDadosEquipe().get(2).adiconarJoagadores(jogadoresTime3);

        ConstraintViolationException constraintViolationException = chamadaComExcecao(dadosCampeonatoRequisicao);

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

    private ConstraintViolationException chamadaComExcecao(DadosCampeonatoRequisicao dadosCampeonatoRequisicao) {
        MutableHttpRequest<DadosCampeonatoRequisicao> requisicao = HttpRequest.POST("/api/campeonato", dadosCampeonatoRequisicao);

        return assertThrows(ConstraintViolationException.class,
                () -> httpClient.toBlocking().exchange(requisicao, DadosCampeonatoRequisicao.class)
        );
    }

}