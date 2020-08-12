package com.it.comparison.handle;

import com.it.comparison.CompareResult;
import com.it.comparison.ComparisonUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 抽象的集合类属性判断工具
 *
 * @param <T> -- 集合中的对象类型
 * @param <R> -- 集合中对象的唯一id类型
 */
public abstract class AbstractListComparisonHandle<T, R> extends AbstractComparisonHandle {
    private static final MapComparisonHandle mapHandle = new MapComparisonHandle();

    @Override
    protected <V> void childCompare(CompareResult compareResult, V v1, V v2, String errAttrPrefix) throws Exception {
        List v11 = (List) v1;
        List v22 = (List) v2;
        if (CollectionUtils.isEmpty(v11) && CollectionUtils.isEmpty(v22)) {
            return;
        }
        if (CollectionUtils.isEmpty(v11) && CollectionUtils.isNotEmpty(v22)) {
            compareResult.addErrorAttr(errAttrPrefix + "v1 is empty ,v2 is " + v2.toString());
            return;
        }
        if (CollectionUtils.isNotEmpty(v11) && CollectionUtils.isEmpty(v22)) {
            compareResult.addErrorAttr(errAttrPrefix + "v2 is empty ,v1 is " + v1.toString());
            return;
        }
        if (v11.size() != v22.size()) {
            compareResult.addErrorAttr(errAttrPrefix + " v1.size:" + v11.size() + " v2.size:" + v22.size());
        }
        childCompareList(compareResult, v11, v22, errAttrPrefix, getId());
    }

    protected abstract Function<T, R> getId();
    protected boolean needCheckIdSort() {
        return true;
    }

    protected <T> void childCompareList(CompareResult compareResult, List<T> v11, List<T> v22, String errAttrPrefix, Function<T, R> f) throws Exception {
        if (needCheckIdSort()) {
            String v1IdStr = v11.stream().map(f).map(s -> s.toString()).collect(Collectors.joining(","));
            String v2IdStr = v22.stream().map(f).map(s -> s.toString()).collect(Collectors.joining(","));
            if (!v1IdStr.equals(v2IdStr)) {
                compareResult.addErrorAttr(errAttrPrefix + " 排序不同，v1:" + v1IdStr + " v2:" + v2IdStr);
            }
        }
        Map<R, T> map1 = v11.stream().collect(Collectors.toMap(f, (s) -> s));
        Map<R, T> map2 = v22.stream().collect(Collectors.toMap(f, (s) -> s));
        mapHandle.childCompareMap(compareResult, map1, map2, errAttrPrefix);
    }
}
