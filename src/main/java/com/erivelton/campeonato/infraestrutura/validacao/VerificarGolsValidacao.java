package com.erivelton.campeonato.infraestrutura.validacao;

import com.erivelton.campeonato.dto.requisicao.ConfrontoRequisicao;
import com.erivelton.campeonato.dominio.ViolacaoVerificacaoGolsException;
import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.validation.validator.constraints.ConstraintValidator;
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext;
import jakarta.inject.Singleton;

@Singleton
public class VerificarGolsValidacao implements ConstraintValidator<VerificarGols, ConfrontoRequisicao> {

    @Override
    public boolean isValid(ConfrontoRequisicao value, AnnotationValue<VerificarGols> annotationMetadata, ConstraintValidatorContext context) {
        if(value.getGolsJogadoresMandante().isEmpty() && !value.getGolsMandante().equals(0))
            throw new ViolacaoVerificacaoGolsException("Gols de jogadores do time mandante devem ser inseridos!");

        if(value.getGolsJogadoresVisitante().isEmpty() && !value.getGolsVisitante().equals(0))
            throw new ViolacaoVerificacaoGolsException("Gols de jogadores do time visitante devem ser inseridos!");

        if(!value.getGolsMandante().equals(value.getGolsJogadoresMandante().values().stream().reduce(0, Integer::sum)))
            throw new ViolacaoVerificacaoGolsException("Gols de jogadores do time mandante devem ser condizentes com o número de gols marcados pela equipe!");

        if(!value.getGolsVisitante().equals(value.getGolsJogadoresVisitante().values().stream().reduce(0, Integer::sum)))
            throw new ViolacaoVerificacaoGolsException("Gols de jogadores do time visitante devem ser condizentes com o número de gols marcados pela equipe!");

        return true;
    }
}
