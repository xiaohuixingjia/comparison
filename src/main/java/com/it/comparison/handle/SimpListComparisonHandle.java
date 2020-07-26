package com.it.comparison.handle;

import com.it.comparison.CompareResult;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SimpListComparisonHandle<T, R> extends AbstractListComparisonHandle<T, R> {
    @Override
    protected Function getId() {
        return null;
    }

    protected <T> void childCompareList(CompareResult compareResult, List<T> v11, List<T> v22, String errAttrPrefix, Function<T, R> f) throws Exception {
        String s1 = v11.stream().map(o -> o.toString()).collect(Collectors.joining(","));
        String s2 = v22.stream().map(o -> o.toString()).collect(Collectors.joining(","));
        if (!s1.equals(s2)) {
            compareResult.addErrorAttr(errAttrPrefix + " 不相同 v11:" + s1 + " v22:" + s2);
        }
    }
}
