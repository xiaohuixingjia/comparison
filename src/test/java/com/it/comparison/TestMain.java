package com.it.comparison;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.it.comparison.test.model.InnerTestModel;
import com.it.comparison.test.model.TestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestMain {
    public static String generJson() {
        TestModel model = new TestModel();
        model.setA(1);
        model.setB(new Short("2").shortValue());
        model.setC("cccc");
        model.setD(false);
        model.setE(22334.67D);
        model.setF('f');
        model.setG(123l);
        model.setH(123.4f);
        model.setI(new Byte("3"));
        List<InnerTestModel> l1 = new ArrayList<>();
        l1.add(new InnerTestModel(1, "id1"));
        l1.add(new InnerTestModel(2, "id2"));
        l1.add(new InnerTestModel(3, "id3"));
        l1.add(new InnerTestModel(4, "id4"));
        l1.add(new InnerTestModel(5, "id5"));
        model.setList(l1);
        model.setInnerTestModel(l1.get(0));
        model.setSimpleComparelist(l1.stream().map(InnerTestModel::getNn).collect(Collectors.toList()));
        model.setIgnoreString("ignore1");
        model.setSimpleMap(l1.stream().collect(Collectors.toMap(InnerTestModel::getId, InnerTestModel::getNn)));
        model.setMap(l1.stream().collect(Collectors.toMap(InnerTestModel::getId, v -> v)));
        return JSONObject.toJSONString(model, SerializerFeature.DisableCircularReferenceDetect);
    }

    public static void main(String[] args) throws Exception {
        String json = generJson();
        System.out.println(json);
        System.out.println("--------------------所有属性都相同时的对比---------------------------");
        testAllSame(json);
        System.out.println("--------------------可以忽略不管的属性在不同时的对比---------------------------");
        testIgnoreAnno(json);
        System.out.println("--------------------一些基本属性不同的对比---------------------------");
        testBasicType(json);
        System.out.println("--------------------复杂对象属性不同的对比---------------------------");
        testDownDetecion(json);
        System.out.println("--------------------对list集合进行定制化的对比---------------------------");
        testConsumList(json);
        System.out.println("--------------------对集合进行简单的toString后的对比---------------------------");
        testSimpleList(json);
        System.out.println("---------------------对map中的value进行简单的equails对比--------------------------");
        testSimpleMap(json);
        System.out.println("---------------------对map中的value进行详细属性的对比--------------------------");
        testMap(json);
    }

    /**
     * 测试属性map中的value为复杂对象的对比
     *
     * @param json
     * @throws Exception
     */
    private static void testMap(String json) throws Exception {
        TestModel testModel1 = JSONObject.parseObject(json, TestModel.class);
        TestModel testModel2 = JSONObject.parseObject(json, TestModel.class);
        testModel2.getMap().get(1).setNn("idddddd");
        testModel2.getMap().get(1).setId(11111);
        CompareResult compare = ComparisonUtil.compare(testModel1, testModel2);
        System.out.println(compare.formatResu());
    }

    /**
     * 测试map的value不是复杂对象的对比
     *
     * @param json
     * @throws Exception
     */
    private static void testSimpleMap(String json) throws Exception {
        TestModel testModel1 = JSONObject.parseObject(json, TestModel.class);
        TestModel testModel2 = JSONObject.parseObject(json, TestModel.class);
        testModel2.getSimpleMap().put(1, "没有id");
        CompareResult compare = ComparisonUtil.compare(testModel1, testModel2);
        System.out.println(compare.formatResu());
    }

    /**
     * 测试集合的简单对比效果
     *
     * @param json
     * @throws Exception
     */
    private static void testSimpleList(String json) throws Exception {
        TestModel testModel1 = JSONObject.parseObject(json, TestModel.class);
        TestModel testModel2 = JSONObject.parseObject(json, TestModel.class);
        testModel2.getSimpleComparelist().add("1234");
        testModel2.getSimpleComparelist().set(0, "aabbccd");
        CompareResult compare = ComparisonUtil.compare(testModel1, testModel2);
        System.out.println(compare.formatResu());
    }

    /**
     * 测试定制的list集合校验
     *
     * @param json
     * @throws Exception
     */
    private static void testConsumList(String json) throws Exception {
        TestModel testModel1 = JSONObject.parseObject(json, TestModel.class);
        TestModel testModel2 = JSONObject.parseObject(json, TestModel.class);
        testModel2.getList().add(new InnerTestModel(9, "id9"));
        testModel2.getList().get(0).setId(1);
        testModel2.getList().get(0).setNn("id-1");
        CompareResult compare = ComparisonUtil.compare(testModel1, testModel2);
        System.out.println(compare.formatResu());
    }

    /**
     * 测试属性未复杂对象不同时的对比验证结果
     *
     * @param json
     * @throws Exception
     */
    private static void testDownDetecion(String json) throws Exception {
        TestModel testModel1 = JSONObject.parseObject(json, TestModel.class);
        TestModel testModel2 = JSONObject.parseObject(json, TestModel.class);
        testModel2.getInnerTestModel().setId(8);
        testModel2.getInnerTestModel().setNn("id8");
        CompareResult compare = ComparisonUtil.compare(testModel1, testModel2);
        System.out.println(compare.formatResu());
    }

    /**
     * 测试普通属性不同 验证结果
     *
     * @param json
     * @throws Exception
     */
    private static void testBasicType(String json) throws Exception {
        TestModel testModel1 = JSONObject.parseObject(json, TestModel.class);
        TestModel testModel2 = JSONObject.parseObject(json, TestModel.class);
        testModel2.setG(123987L);
        testModel2.setC("33jjkk");
        CompareResult compare = ComparisonUtil.compare(testModel1, testModel2);
        System.out.println(compare.formatResu());
    }

    /**
     * 在属性上注有 ComparisonIgnore 注解的属性不同时 验证是否会略过
     *
     * @param json
     * @throws Exception
     */
    private static void testIgnoreAnno(String json) throws Exception {
        TestModel testModel1 = JSONObject.parseObject(json, TestModel.class);
        TestModel testModel2 = JSONObject.parseObject(json, TestModel.class);
        testModel2.setIgnoreString("32425");
        CompareResult compare = ComparisonUtil.compare(testModel1, testModel2);
        System.out.println(compare.formatResu());
    }

    /**
     * 属性全部相同时 是否对比完全一致
     *
     * @param json
     * @throws Exception
     */
    private static void testAllSame(String json) throws Exception {
        TestModel testModel1 = JSONObject.parseObject(json, TestModel.class);
        TestModel testModel2 = JSONObject.parseObject(json, TestModel.class);
        CompareResult compare = ComparisonUtil.compare(testModel1, testModel2);
        System.out.println(compare.formatResu());
    }
}
