package com.erivelton.campeonato.infraestrutura.validacao.anotacao;

import com.erivelton.campeonato.infraestrutura.validacao.constraint.VerificarGolsValidacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {VerificarGolsValidacao.class})
public @interface VerificarGols {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
