package com.erivelton.campeonato.controle;

import com.erivelton.campeonato.dto.requisicao.ConfrontoRequisicao;
import io.micronaut.core.type.Argument;
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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class ConfrontoControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    void deveRegistrarConfronto(){
        Map<String, Integer> golsJogadoresMandante = Map.of("jogador1", 2);
        Map<String, Integer> golsJogadoresVisitante = Map.of("jogador1", 1);

        ConfrontoRequisicao confrontoRequisicao = builderConfronto(1L, golsJogadoresMandante, golsJogadoresVisitante, 1, 1);

        HttpRequest<ConfrontoRequisicao> requisicao = HttpRequest.PUT("/api/confronto", confrontoRequisicao);
        HttpResponse<ConfrontoRequisicao> resposta = httpClient.toBlocking().exchange(requisicao, Argument.of(ConfrontoRequisicao.class));

        assertNull(resposta.body());
        assertEquals(HttpStatus.CREATED, resposta.getStatus());
    }

    @Test
    void deveInvalidarQuandoValoresForemNulos(){
        Map<String, Integer> golsJogadoresMandante = Map.of("jogador1", 2);
        Map<String, Integer> golsJogadoresVisitante = Map.of("jogador1", 1);

        ConfrontoRequisicao confrontoRequisicao = builderConfronto(null, golsJogadoresMandante, golsJogadoresVisitante, null, null);

        HttpRequest<ConfrontoRequisicao> requisicao = HttpRequest.PUT("/api/confronto", confrontoRequisicao);
        ConstraintViolationException constraintViolationException = assertThrows(
                ConstraintViolationException.class,
                () -> httpClient.toBlocking().exchange(requisicao, Argument.of(ConfrontoRequisicao.class))
        );
    }

    private ConfrontoRequisicao builderConfronto(Long identificacao, Map<String, Integer> golsJogadoresMandante, Map<String, Integer> golsJogadoresVisitante, Integer qtdGolsVisitante, Integer qtdGolsMandante){
        return ConfrontoRequisicao.builder()
                .identificacao(identificacao)
                .golsMandante(qtdGolsMandante)
                .golsVisitante(qtdGolsVisitante)
                .golsJogadoresMandante(golsJogadoresMandante)
                .golsJogadoresVisitante(golsJogadoresVisitante)
                .build();
    }
}