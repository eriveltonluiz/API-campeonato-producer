package com.erivelton.campeonato.infraestrutura.validacao.constraint;

import com.erivelton.campeonato.dominio.ViolacaoVerificacaoQuantidadeTimesException;
import com.erivelton.campeonato.dto.requisicao.DadosEquipeRequisicao;
import com.erivelton.campeonato.infraestrutura.validacao.constraint.VerificarQuantidadeTimesValidacao;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class VerificarQuantidadeTimesValidacaoTest {

    @Inject
    private VerificarQuantidadeTimesValidacao verificarQuantidadeTimesValidacao;

    @Test
    void deveValidarQuantidadeDeTimes() throws ViolacaoVerificacaoQuantidadeTimesException {
        List<DadosEquipeRequisicao> dadosEquipe = listaDadosEquipeAuxiliar();
        dadosEquipe.add(new DadosEquipeRequisicao("prim8"));

        assertTrue(verificarQuantidadeTimesValidacao.isValid(dadosEquipe, null, null));
    }

    @Test
    void deveInvalidarQuandoQuantidadeDeTimeForImpar(){
        List<DadosEquipeRequisicao> dadosEquipe = listaDadosEquipeAuxiliar();

        assertFalse(verificarQuantidadeTimesValidacao.isValid(dadosEquipe, null, null));
    }

    @Test
    void deveInvalidarQuandoQuantidadeDeTimeForParPoremNaRecursaoObterValorImpar(){
        List<DadosEquipeRequisicao> dadosEquipe = listaDadosEquipeAuxiliar();

        dadosEquipe.addAll(
                Arrays.asList(
                        new DadosEquipeRequisicao("prim8"),
                        new DadosEquipeRequisicao("prim9"),
                        new DadosEquipeRequisicao("prim10")
                )
        );

        assertFalse(verificarQuantidadeTimesValidacao.isValid(dadosEquipe, null, null));
    }

    private List<DadosEquipeRequisicao> listaDadosEquipeAuxiliar(){
        return new ArrayList<>(
                Arrays.asList(
                    new DadosEquipeRequisicao("prim1"),
                    new DadosEquipeRequisicao("prim2"),
                    new DadosEquipeRequisicao("prim3"),
                    new DadosEquipeRequisicao("prim4"),
                    new DadosEquipeRequisicao("prim5"),
                    new DadosEquipeRequisicao("prim6"),
                    new DadosEquipeRequisicao("prim7")
                )
        );
    }
}