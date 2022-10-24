package com.erivelton.campeonato.infraestrutura.validacao.anotacao;

import com.erivelton.campeonato.infraestrutura.validacao.constraint.VerificarQuantidadeMaximaJogadoresValidacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = {VerificarQuantidadeMaximaJogadoresValidacao.class})
public @interface VerificarQuantidadeMaximaJogadores {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
