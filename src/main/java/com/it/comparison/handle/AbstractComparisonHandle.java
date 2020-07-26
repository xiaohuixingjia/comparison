package com.it.comparison.handle;

import com.it.comparison.CompareResult;

public abstract class AbstractComparisonHandle {
    public <V> void compare(CompareResult compareResult, V v1, V v2, String errAttrPrefix) throws Exception {
        if (v1 == null && v2 == null) {
            return;
        }
        if (v1 == null && v2 != null) {
            compareResult.addErrorAttr(errAttrPrefix + " 不同" + " v1 is null , v2:" + v2);
            return;
        }
        if (v1 != null && v2 == null) {
            compareResult.addErrorAttr(errAttrPrefix + " 不同" + " v1 :" + v1 + " , v2 is null");
            return;
        }
        childCompare(compareResult, v1, v2, errAttrPrefix);
    }

    protected abstract <V> void childCompare(CompareResult compareResult, V v1, V v2, String errAttrPrefix) throws Exception;

}
