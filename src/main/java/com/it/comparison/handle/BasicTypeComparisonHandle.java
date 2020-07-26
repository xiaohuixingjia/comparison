package com.it.comparison.handle;

import com.it.comparison.CompareResult;

/**
 * 基本类型的equails判断
 */
public class BasicTypeComparisonHandle extends AbstractComparisonHandle {
    @Override
    protected <V> void childCompare(CompareResult compareResult, V v1, V v2, String errAttrPrefix) {
        if (!v1.equals(v2)) {
            compareResult.addErrorAttr(errAttrPrefix + " 不同 v1:" + v1.toString() + " v2:" + v2.toString());
        }
    }
}
