package com.erivelton.campeonato.infraestrutura.validacao.anotacao;

import com.erivelton.campeonato.infraestrutura.validacao.constraint.VerificarQuantidadeTimesValidacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {VerificarQuantidadeTimesValidacao.class})
public @interface VerificarQuantidadeTimes {
    String message() default "Quantidade de times não condoizente com modelo exponencial a base 2";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
