package com.erivelton.campeonato.infraestrutura.validacao;

import com.erivelton.campeonato.dominio.ViolacaoVerificacaoGolsException;
import com.erivelton.campeonato.dominio.ViolacaoVerificacaoQuantidadeTimesException;
import jakarta.inject.Singleton;

@Singleton
public class VerificarQuantidadeTimes {

    public void validar(Integer valor) throws ViolacaoVerificacaoQuantidadeTimesException {
        if(valor % 2 != 0){
            throw new ViolacaoVerificacaoQuantidadeTimesException("Quantidade de times cadastrados deve ser de um valor hexadecimal!");
        }

        if(valor > 2){
            validar(valor/2);
        }
    }
}
