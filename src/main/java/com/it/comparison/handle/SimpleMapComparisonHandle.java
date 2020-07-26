package com.it.comparison.handle;

import com.it.comparison.CompareResult;
import com.it.comparison.ComparisonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 对map中的value值只做简单的 equails判断
 *
 * @param <KEY>   map中Key的对象类型
 * @param <VALUE> map中value的对象类型
 */
public class SimpleMapComparisonHandle<KEY, VALUE> extends MapComparisonHandle {
    @Override
    protected void compareValue(CompareResult compareResult, Map m1, Map m2, String errAttrPrefix, Set allInIds) throws Exception {
        for (Object id : allInIds) {
            if (!m1.get(id).equals(m2.get(id))) {
                compareResult.addErrorAttr(errAttrPrefix + " m1." + id + ":" + m1.get(id) + " v2." + id + ":" + m2.get(id));
            }
        }
    }
}
