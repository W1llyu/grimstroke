package com.ouresports.grimstroke.base.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by will on 2018/12/7.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RestCrudController {
    @AliasFor(
            annotation = Component.class
    )
    String value() default "";
}
