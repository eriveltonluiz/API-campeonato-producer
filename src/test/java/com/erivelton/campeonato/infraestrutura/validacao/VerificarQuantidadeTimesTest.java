package com.erivelton.campeonato.infraestrutura.validacao;

import com.erivelton.campeonato.dominio.ViolacaoVerificacaoQuantidadeTimesException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class VerificarQuantidadeTimesTest {

    @Inject
    private VerificarQuantidadeTimes verificarQuantidadeTimes;

    @Test
    void deveValidarQuantidadeDeTimes() throws ViolacaoVerificacaoQuantidadeTimesException {
        verificarQuantidadeTimes.validar(32);
    }

    @Test
    void deveInvalidarQuandoQuantidadeDeTimeForImpar(){
        ViolacaoVerificacaoQuantidadeTimesException violacaoVerificacaoQuantidadeTimesException = assertThrows(
                ViolacaoVerificacaoQuantidadeTimesException.class, () -> verificarQuantidadeTimes.validar(31)
        );

        assertEquals("Quantidade de times cadastrados deve ser de um valor hexadecimal!", violacaoVerificacaoQuantidadeTimesException.getMessage());
    }

    @Test
    void deveInvalidarQuandoQuantidadeDeTimeForParPoremNaRecursaoObterValorImpar(){
        ViolacaoVerificacaoQuantidadeTimesException violacaoVerificacaoQuantidadeTimesException = assertThrows(
                ViolacaoVerificacaoQuantidadeTimesException.class, () -> verificarQuantidadeTimes.validar(24)
        );

        assertEquals("Quantidade de times cadastrados deve ser de um valor hexadecimal!", violacaoVerificacaoQuantidadeTimesException.getMessage());
    }
}