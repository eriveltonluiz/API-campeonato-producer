package com.erivelton.campeonato.infraestrutura.validacao.anotacao;

import com.erivelton.campeonato.infraestrutura.validacao.constraint.VerificarQuantidadeMaximaJogadoresValidacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.Valid;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Constraint(validatedBy = {VerificarQuantidadeMaximaJogadoresValidacao.class})
public @interface VerificarQuantidadeMaximaJogadores {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
