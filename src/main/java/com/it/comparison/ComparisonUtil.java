package com.it.comparison;

import com.it.comparison.anno.ComparisonAnno;
import com.it.comparison.anno.ComparisonIgnore;
import com.it.comparison.handle.AbstractComparisonHandle;
import com.it.comparison.handle.BasicTypeComparisonHandle;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ComparisonUtil {
    private static final Map<Class<? extends AbstractComparisonHandle>, AbstractComparisonHandle> compareHandleMap = new ConcurrentHashMap<>();
    private static AbstractComparisonHandle defaultHandle = new BasicTypeComparisonHandle();

    static {
        compareHandleMap.put(defaultHandle.getClass(), defaultHandle);
    }

    public static void initDefaultHandle(AbstractComparisonHandle d) {
        defaultHandle = d;
        compareHandleMap.put(defaultHandle.getClass(), defaultHandle);
    }

    public static <T> CompareResult compare(T s1, T s2) throws Exception {
        CompareResult compareResult = new CompareResult();
        if (s1 == null && s2 == null) {
            return compareResult;
        }
        if (s1 == null && s2 != null) {
            compareResult.addErrorAttr("不相同 s1 is null s2 不为空");
            return compareResult;
        }
        if (s1 != null && s2 == null) {
            compareResult.addErrorAttr("不相同 s2 is null s1 不为空");
            return compareResult;
        }
        compare(compareResult, s1, s2, s1.getClass().getSimpleName());
        return compareResult;

    }

    public static <T> void compare(CompareResult compareResult, T s1, T s2, String errAttrPrefix) throws Exception {
        Class<? extends AbstractComparisonHandle> defaultHandleClass = defaultHandle.getClass();
        ComparisonAnno classDefaultComparisonAnno = s1.getClass().getAnnotation(ComparisonAnno.class);
        if (classDefaultComparisonAnno != null) {
            defaultHandleClass = classDefaultComparisonAnno.checkHandlerClass();
        }
        //得到所有属性
        Field[] fields = s1.getClass().getDeclaredFields();
        for (Field field : fields) {
            ComparisonIgnore comparisonIgnore = field.getAnnotation(ComparisonIgnore.class);
            if (comparisonIgnore != null) {
                continue;
            }
            ComparisonAnno annotation = field.getAnnotation(ComparisonAnno.class);
            Class<? extends AbstractComparisonHandle> fieldHandleClass = null;
            if (annotation != null) {
                fieldHandleClass = annotation.checkHandlerClass();
            } else {
                fieldHandleClass = defaultHandleClass;
            }
            if (!compareHandleMap.containsKey(fieldHandleClass)) {
                compareHandleMap.put(fieldHandleClass, fieldHandleClass.newInstance());
            }
            field.setAccessible(true);
            String filedErrAttr = errAttrPrefix + "." + field.getName();
            compareHandleMap.get(fieldHandleClass).compare(compareResult, field.get(s1), field.get(s2), filedErrAttr);
        }
    }
}

