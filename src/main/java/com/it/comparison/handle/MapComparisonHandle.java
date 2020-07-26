package com.it.comparison.handle;

import com.it.comparison.CompareResult;
import com.it.comparison.ComparisonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 对map属性的定制判断类
 *
 * @param <KEY>   map中Key的对象类型
 * @param <VALUE> map中value的对象类型
 */
public class MapComparisonHandle<KEY, VALUE> extends AbstractComparisonHandle {

    @Override
    protected <V> void childCompare(CompareResult compareResult, V v1, V v2, String errAttrPrefix) throws Exception {
        Map m1 = (Map) v1;
        Map m2 = (Map) v2;
        if (MapUtils.isEmpty(m1) && MapUtils.isEmpty(m2)) {
            return;
        }
        if (MapUtils.isEmpty(m1) && MapUtils.isNotEmpty(m2)) {
            compareResult.addErrorAttr(errAttrPrefix + "v1 is empty ,v2 is " + v2.toString());
            return;
        }
        if (MapUtils.isNotEmpty(m1) && MapUtils.isEmpty(m2)) {
            compareResult.addErrorAttr(errAttrPrefix + "v2 is empty ,v1 is " + v1.toString());
            return;
        }
        if (m1.size() != m2.size()) {
            compareResult.addErrorAttr(errAttrPrefix + " v1.size:" + m1.size() + " v2.size:" + m2.size());
        }
        childCompareMap(compareResult, m1, m2, errAttrPrefix);
    }

    protected void childCompareMap(CompareResult compareResult, Map<KEY, VALUE> m1, Map<KEY, VALUE> m2, String errAttrPrefix) throws Exception {
        Set<KEY> id1s = m1.keySet();
        Set<KEY> id2s = m2.keySet();
        Set<KEY> id1notIn2 = id1s.stream().filter(id -> !id2s.contains(id)).collect(Collectors.toSet());
        if (CollectionUtils.isNotEmpty(id1notIn2)) {
            compareResult.addErrorAttr(errAttrPrefix + " m1-m2:" + id1notIn2.stream().map(Object::toString).collect(Collectors.joining(",")));
        }
        Set<KEY> id2notIn1 = id2s.stream().filter(id -> !id1s.contains(id)).collect(Collectors.toSet());
        if (CollectionUtils.isNotEmpty(id2notIn1)) {
            compareResult.addErrorAttr(errAttrPrefix + " m2-m1:" + id2notIn1.stream().map(Object::toString).collect(Collectors.joining(",")));
        }
        Set<KEY> allInIds = id1s.stream().filter(id -> id2s.contains(id)).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(allInIds)) {
            return;
        }
        compareValue(compareResult, m1, m2, errAttrPrefix, allInIds);
    }

    protected void compareValue(CompareResult compareResult, Map<KEY, VALUE> m1, Map<KEY, VALUE> m2, String errAttrPrefix, Set<KEY> allInIds) throws Exception {
        for (Object id : allInIds) {
            ComparisonUtil.compare(compareResult, m1.get(id), m2.get(id), errAttrPrefix + "-id:" + id);
        }
    }
}
