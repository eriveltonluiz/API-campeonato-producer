package com.erivelton.campeonato.infraestrutura.validacao.constraint;

import com.erivelton.campeonato.dominio.ViolacaoVerificacaoQuantidadeMaximaJogadoresException;
import com.erivelton.campeonato.dto.requisicao.DadosCampeonatoRequisicao;
import com.erivelton.campeonato.dto.requisicao.DadosEquipeRequisicao;
import com.erivelton.campeonato.infraestrutura.validacao.anotacao.VerificarQuantidadeMaximaJogadores;
import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.validation.validator.constraints.ConstraintValidator;
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext;
import jakarta.inject.Singleton;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class VerificarQuantidadeMaximaJogadoresValidacao implements ConstraintValidator<VerificarQuantidadeMaximaJogadores, DadosCampeonatoRequisicao> {

    @Override
    public boolean isValid(DadosCampeonatoRequisicao value, AnnotationValue<VerificarQuantidadeMaximaJogadores> annotationMetadata, ConstraintValidatorContext context) {
        value.getDadosEquipe().removeIf(equipe -> equipe.quantidadeJogadores() >= value.getQuantidadeMaximaJogadores());
        Map<String, String> violacoes = new HashMap<>(
                value.getDadosEquipe().stream()
                        .collect(Collectors.toMap(DadosEquipeRequisicao::getEquipe, valor -> "ultrapassou a quantidade máxima de jogadores inscritos"))
        );

        if(violacoes.isEmpty()){
            return true;
        }

        ViolacaoVerificacaoQuantidadeMaximaJogadoresException violacaoVerificacaoQuantidadeMaximaJogadoresException = new ViolacaoVerificacaoQuantidadeMaximaJogadoresException("Violação no limite de inscrição de jogadores");
        violacaoVerificacaoQuantidadeMaximaJogadoresException.adicionarViolacoes(violacoes);

        throw violacaoVerificacaoQuantidadeMaximaJogadoresException;
    }
}
