package com.it.comparison;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CompareResult {
    private boolean compareResu=true;
    private String msg;
    private List<String> list = new ArrayList<>();

    public CompareResult() {
    }

    public CompareResult(boolean compareResu, String msg) {
        this.compareResu = compareResu;
        this.msg = msg;
    }

    public boolean isCompareResu() {
        return compareResu;
    }

    public void setCompareResu(boolean compareResu) {
        this.compareResu = compareResu;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String formatResu() {
        if (CollectionUtils.isEmpty(list)) {
            return "全部一致";
        } else {
            StringBuffer builder = new StringBuffer("不一致\n");
            for (String s : list) {
                builder.append(s).append("\n");
            }
            return builder.toString();
        }
    }

    public void addErrorAttr(String s) {
        this.compareResu = false;
        list.add(s);
    }
}
