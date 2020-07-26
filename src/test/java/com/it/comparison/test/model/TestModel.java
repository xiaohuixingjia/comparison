package com.it.comparison.test.model;

import com.it.comparison.anno.ComparisonAnno;
import com.it.comparison.anno.ComparisonIgnore;
import com.it.comparison.handle.DownDetecionComparisonHandle;
import com.it.comparison.handle.MapComparisonHandle;
import com.it.comparison.handle.SimpListComparisonHandle;
import com.it.comparison.handle.SimpleMapComparisonHandle;
import com.it.comparison.test.customHandle.ListInnerTestCustomHandle;

import java.util.List;
import java.util.Map;

public class TestModel {
    private int a;
    private short b;
    private String c;
    private boolean d;
    private double e;
    private char f;
    private long g;
    private float h;
    private byte i;
    @ComparisonAnno(checkHandlerClass = DownDetecionComparisonHandle.class)
    private InnerTestModel innerTestModel;
    @ComparisonAnno(checkHandlerClass = ListInnerTestCustomHandle.class)
    private List<InnerTestModel> list;
    @ComparisonAnno(checkHandlerClass = SimpListComparisonHandle.class)
    private List<String> simpleComparelist;
    @ComparisonIgnore
    private String ignoreString;
    @ComparisonAnno(checkHandlerClass = SimpleMapComparisonHandle.class)
    private Map<Integer, String> simpleMap;
    @ComparisonAnno(checkHandlerClass = MapComparisonHandle.class)
    private Map<Integer, InnerTestModel> map;

    public List<String> getSimpleComparelist() {
        return simpleComparelist;
    }

    public void setSimpleComparelist(List<String> simpleComparelist) {
        this.simpleComparelist = simpleComparelist;
    }

    public String getIgnoreString() {
        return ignoreString;
    }

    public void setIgnoreString(String ignoreString) {
        this.ignoreString = ignoreString;
    }

    public Map<Integer, String> getSimpleMap() {
        return simpleMap;
    }

    public void setSimpleMap(Map<Integer, String> simpleMap) {
        this.simpleMap = simpleMap;
    }

    public Map<Integer, InnerTestModel> getMap() {
        return map;
    }

    public void setMap(Map<Integer, InnerTestModel> map) {
        this.map = map;
    }

    public List<InnerTestModel> getList() {
        return list;
    }

    public void setList(List<InnerTestModel> list) {
        this.list = list;
    }

    public TestModel() {

    }

    public TestModel(int a, short b, String c, boolean d, double e, char f, long g, float h, byte i, InnerTestModel innerTestModel, List<InnerTestModel> list) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.innerTestModel = innerTestModel;
        this.list = list;
    }

    public InnerTestModel getInnerTestModel() {
        return innerTestModel;
    }

    public void setInnerTestModel(InnerTestModel innerTestModel) {
        this.innerTestModel = innerTestModel;
    }

    public byte getI() {
        return i;
    }

    public void setI(byte i) {
        this.i = i;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public short getB() {
        return b;
    }

    public void setB(short b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public boolean isD() {
        return d;
    }

    public void setD(boolean d) {
        this.d = d;
    }

    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }

    public char getF() {
        return f;
    }

    public void setF(char f) {
        this.f = f;
    }

    public long getG() {
        return g;
    }

    public void setG(long g) {
        this.g = g;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

}
