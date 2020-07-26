package com.it.comparison.anno;

import com.it.comparison.handle.AbstractComparisonHandle;
import com.it.comparison.handle.BasicTypeComparisonHandle;

import java.lang.annotation.*;

@Target(value = {ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ComparisonAnno {
    Class<? extends AbstractComparisonHandle> checkHandlerClass() default BasicTypeComparisonHandle.class;
}
