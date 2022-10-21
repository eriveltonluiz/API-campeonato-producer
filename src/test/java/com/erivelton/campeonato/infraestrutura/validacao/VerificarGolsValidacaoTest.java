package com.erivelton.campeonato.infraestrutura.validacao;

import com.erivelton.campeonato.dto.requisicao.ConfrontoRequisicao;
import com.erivelton.campeonato.dominio.ViolacaoVerificacaoGolsException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

@MicronautTest
class VerificarGolsValidacaoTest {

    @Inject
    private VerificarGolsValidacao verificarGolsValidacao;

    @Test
    void devePassarAValidacaoQuandoDadosForemCoerentes(){
        Map<String, Integer> golsJogadoresVisitante = Map.of("jogador1", 1);
        Map<String, Integer> golsJogadoresMandante = Map.of("jogador2", 1);

        ConfrontoRequisicao confrontoRequisicao = builderConfronto(golsJogadoresMandante, golsJogadoresVisitante, 1, 1);

        boolean resultado = verificarGolsValidacao.isValid(confrontoRequisicao, null, null);

        assertTrue(resultado);
    }

    @Test
    void deveInvalidarQuandoTimeMandanteFezGolsENaoFoiAdiconadoOsJogadoresQueFizeram(){
        Map<String, Integer> golsJogadoresVisitante = Map.of("jogador1", 1);
        Map<String, Integer> golsJogadoresMandante = new HashMap<>();

        resultadosExcecoes(golsJogadoresMandante, golsJogadoresVisitante, "Gols de jogadores do time mandante devem ser inseridos!");
    }

    @Test
    void deveInvalidarQuandoTimeVisitanteFezGolsENaoFoiAdiconadoOsJogadoresQueFizeram(){
        Map<String, Integer> golsJogadoresMandante = Map.of("jogador1", 1);
        Map<String, Integer> golsJogadoresVisitante = new HashMap<>();

        resultadosExcecoes(golsJogadoresMandante, golsJogadoresVisitante, "Gols de jogadores do time visitante devem ser inseridos!");
    }

    @Test
    void deveInvalidarQuandoGolsDoTimeVisitanteNaoCondizerComOsGolsAdicionadosNosJogadores(){
        Map<String, Integer> golsJogadoresMandante = Map.of("jogador1", 1);
        Map<String, Integer> golsJogadoresVisitante = Map.of("jogador1", 2);

        resultadosExcecoes(golsJogadoresMandante, golsJogadoresVisitante, "Gols de jogadores do time visitante devem ser condizentes com o número de gols marcados pela equipe!");
    }

    @Test
    void deveInvalidarQuandoGolsDoTimeMandanteNaoCondizerComOsGolsAdicionadosNosJogadores(){
        Map<String, Integer> golsJogadoresMandante = Map.of("jogador1", 2);
        Map<String, Integer> golsJogadoresVisitante = Map.of("jogador1", 1);

        resultadosExcecoes(golsJogadoresMandante, golsJogadoresVisitante, "Gols de jogadores do time mandante devem ser condizentes com o número de gols marcados pela equipe!");
    }

    private void resultadosExcecoes(Map<String, Integer> golsJogadoresMandante, Map<String, Integer> golsJogadoresVisitante, String mensagemEsperada){
        ConfrontoRequisicao confrontoRequisicao = builderConfronto(golsJogadoresMandante, golsJogadoresVisitante, 1, 1);

        ViolacaoVerificacaoGolsException violacaoVerificacaoGolsException = assertThrows(ViolacaoVerificacaoGolsException.class, () -> {
            verificarGolsValidacao.isValid(confrontoRequisicao, null, null);
        });

        assertEquals(mensagemEsperada, violacaoVerificacaoGolsException.getMessage());
    }

    private ConfrontoRequisicao builderConfronto(Map<String, Integer> golsJogadoresMandante, Map<String, Integer> golsJogadoresVisitante, Integer qtdGolsVisitante, Integer qtdGolsMandante){
        return ConfrontoRequisicao.builder()
                    .identificacao(Long.parseLong("1"))
                    .golsMandante(qtdGolsMandante)
                    .golsVisitante(qtdGolsVisitante)
                    .golsJogadoresMandante(golsJogadoresMandante)
                    .golsJogadoresVisitante(golsJogadoresVisitante)
                    .build();
    }
}