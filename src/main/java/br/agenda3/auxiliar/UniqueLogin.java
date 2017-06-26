package br.agenda3.auxiliar;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UniqueLoginImpl.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueLogin {

	String message() default "Já cadastrado.";
	  Class<?>[] groups() default { };
	  Class<? extends Payload>[] payload() default { };
}
