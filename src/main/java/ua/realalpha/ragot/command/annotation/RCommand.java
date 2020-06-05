package ua.realalpha.ragot.command.annotation;

import ua.realalpha.ragot.command.RExecutorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RCommand {

    String command();
    String[] aliases() default {};
    String description();
    RExecutorType type();

}
