package com.erivelton.campeonato.infraestrutura.configuracao;

import com.erivelton.campeonato.dominio.ViolacaoVerificacaoGolsException;
import com.erivelton.campeonato.dominio.ViolacaoVerificacaoQuantidadeTimesException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;

import java.time.LocalDateTime;

@Controller
public class TratamentoExcecoesHandler {

    @Error(global = true, exception = ViolacaoVerificacaoGolsException.class)
    public HttpResponse<ErroDTO> handleViolacaoVerificacaoGols(ViolacaoVerificacaoGolsException violacaoVerificacaoGolsException){
        return HttpResponse.badRequest(
                new ErroDTO(
                        HttpStatus.BAD_REQUEST.getCode(), LocalDateTime.now(), violacaoVerificacaoGolsException.getMessage()
                )
        );
    }
    @Error(global = true, exception = ViolacaoVerificacaoQuantidadeTimesException.class)
    public HttpResponse<ErroDTO> handleViolacaoQuantidadeTimes(ViolacaoVerificacaoQuantidadeTimesException violacaoVerificacaoQuantidadeTimesException){
        return HttpResponse.badRequest(
                new ErroDTO(
                        HttpStatus.BAD_REQUEST.getCode(), LocalDateTime.now(), violacaoVerificacaoQuantidadeTimesException.getMessage()
                )
        );
    }
}
