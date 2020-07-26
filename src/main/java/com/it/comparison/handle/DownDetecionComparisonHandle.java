package com.it.comparison.handle;

import com.it.comparison.CompareResult;
import com.it.comparison.ComparisonUtil;

/**
 * 复杂对象深入机型基本类型判断
 */
public class DownDetecionComparisonHandle extends AbstractComparisonHandle {
    @Override
    protected <V> void childCompare(CompareResult compareResult, V v1, V v2, String errAttrPrefix) throws Exception {
        ComparisonUtil.compare(compareResult, v1, v2, errAttrPrefix);
    }
}
