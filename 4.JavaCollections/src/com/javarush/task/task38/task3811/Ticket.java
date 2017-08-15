package com.javarush.task.task38.task3811;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sikonder on 13.08.17.
 */
@Target(value= ElementType.TYPE)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface Ticket {
public enum Priority{LOW, MEDIUM, HIGH}
    Priority priority() default Priority.MEDIUM;

    //5) Свойство tags будет массивом строк и будет хранить теги связанные с тикетом.
    String[] tags() default {};

    //6) Свойство createdBy будет строкой — по умолчанию Amigo.
    String createdBy() default "Amigo";
}

