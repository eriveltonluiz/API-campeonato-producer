package com.erivelton.campeonato.infraestrutura.configuracao;

import com.erivelton.campeonato.dominio.ViolacaoVerificacaoGolsException;
import com.erivelton.campeonato.dominio.ViolacaoVerificacaoQuantidadeMaximaJogadoresException;
import com.erivelton.campeonato.dominio.ViolacaoVerificacaoQuantidadeTimesException;
import io.micronaut.context.MessageSource;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class TratamentoExcecoesHandler {

    @Error(global = true, exception = ConstraintViolationException.class)
    public HttpResponse<ErroDTO> handleConstraintViolation(ConstraintViolationException constraintViolationException){
        List<DetalhesErroDTO> detalhesErros = constraintViolationException.getConstraintViolations().stream()
                .map(violacao -> {
                    Stack<Path.Node> stack = new Stack<>();
                    stack.addAll(
                            StreamSupport
                                .stream(violacao.getPropertyPath().spliterator(), false)
                                .collect(Collectors.toList())
                    );

                    return new DetalhesErroDTO(stack.pop().getName(), violacao.getMessage());
                })
                .collect(Collectors.toList());

        return HttpResponse.badRequest(
                new ErroDTO(HttpStatus.BAD_REQUEST.getCode(), LocalDateTime.now(), "Bad Request", detalhesErros)
        );
    }

    @Error(global = true, exception = ViolacaoVerificacaoGolsException.class)
    public HttpResponse<ErroDTO> handleViolacaoVerificacaoGols(ViolacaoVerificacaoGolsException violacaoVerificacaoGolsException) {
        return HttpResponse.badRequest(
                new ErroDTO(
                        HttpStatus.BAD_REQUEST.getCode(), LocalDateTime.now(), violacaoVerificacaoGolsException.getMessage()
                )
        );
    }

    @Error(global = true, exception = ViolacaoVerificacaoQuantidadeTimesException.class)
    public HttpResponse<ErroDTO> handleViolacaoQuantidadeTimes(ViolacaoVerificacaoQuantidadeTimesException violacaoVerificacaoQuantidadeTimesException) {
        return HttpResponse.badRequest(
                new ErroDTO(
                        HttpStatus.BAD_REQUEST.getCode(), LocalDateTime.now(), violacaoVerificacaoQuantidadeTimesException.getMessage()
                )
        );
    }

    @Error(global = true, exception = ViolacaoVerificacaoQuantidadeMaximaJogadoresException.class)
    public HttpResponse<ErroDTO> handleViolacaoVerificacaoQuantidadeMaximaJogadores(ViolacaoVerificacaoQuantidadeMaximaJogadoresException violacaoVerificacaoQuantidadeMaximaJogadoresException) {
        return HttpResponse.badRequest(
                new ErroDTO(
                        HttpStatus.BAD_REQUEST.getCode(), LocalDateTime.now(),
                        violacaoVerificacaoQuantidadeMaximaJogadoresException.getMessage(),
                        violacaoVerificacaoQuantidadeMaximaJogadoresException.getViolacoes().entrySet().stream()
                                .map(violacao -> new DetalhesErroDTO(violacao.getKey(), violacao.getValue()))
                                .collect(Collectors.toList())
                )
        );
    }

}
