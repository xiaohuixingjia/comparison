package com.it.comparison.anno;

import java.lang.annotation.*;

/**
 * 忽略检查字段
 */
@Target(value={ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ComparisonIgnore {
}
