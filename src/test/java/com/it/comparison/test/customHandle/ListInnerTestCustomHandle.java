package com.it.comparison.test.customHandle;

import com.it.comparison.handle.AbstractListComparisonHandle;
import com.it.comparison.test.model.InnerTestModel;

import java.util.function.Function;

public class ListInnerTestCustomHandle extends AbstractListComparisonHandle<InnerTestModel, Integer> {
    @Override
    protected Function<InnerTestModel, Integer> getId() {
        return InnerTestModel::getId;
    }
}
