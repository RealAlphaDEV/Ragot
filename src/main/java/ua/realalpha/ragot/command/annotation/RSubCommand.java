package ua.realalpha.ragot.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RSubCommand {

    String command();
    String[] underCommand();
    String usage();
    String description();
    String[] args();

}
