package com.erivelton.campeonato.infraestrutura.validacao.constraint;

import com.erivelton.campeonato.dto.requisicao.DadosEquipeRequisicao;
import com.erivelton.campeonato.infraestrutura.validacao.anotacao.VerificarQuantidadeTimes;
import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.validation.validator.constraints.ConstraintValidator;
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Stream;

@Singleton
public class VerificarQuantidadeTimesValidacao implements ConstraintValidator<VerificarQuantidadeTimes, List<DadosEquipeRequisicao>> {

    @Override
    public boolean isValid(List<DadosEquipeRequisicao> value, AnnotationValue<VerificarQuantidadeTimes> annotationMetadata, ConstraintValidatorContext context) {
        return Stream
                .iterate(value.size(), valor -> valor > 2, valor -> valor/2)
                .allMatch(valor -> valor % 2 == 0);
    }
}
