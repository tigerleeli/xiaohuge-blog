package lesson12.lesson1201;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 注解 类注释
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ClassPreamble {
    String name();

    String author();

    String date();

    int currentRevision() default 1;
}
