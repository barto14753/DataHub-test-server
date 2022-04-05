package com.datahub.Datahubtestserver.dataValidation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class LIstCasterFactoryTest {

    private final ListCasterFactory listCasterFactory = new ListCasterFactory();

    @Test
    public void intToFloatTest(){
        List<Integer> testList = Arrays.asList(1,2,5,7,8,43);
        List<Float> endList = listCasterFactory.intToFloat(testList);
        for (Float i: endList) {
            Assert.isTrue(i.getClass().getSimpleName().equals("Float"),"Error not casted!");
        }
    }

    @Test
    public void floatToIntTest(){
        List<Float> testList = Arrays.asList(1.0f,2.2f,5.1f,7.5f,8.7f,43.9f);
        List<Integer> endList = listCasterFactory.floatToInt(testList);
        for (Integer i: endList) {
            Assert.isTrue(i.getClass().getSimpleName().equals("Integer"),"Error not casted!");
        }
    }

    @Test
    public void fromJSONToFloatTest() throws JSONException {
        JSONObject obj = new JSONObject("{data: " +
                "[" +
                "{number:6.0}, " +
                "{number:5.8}, " +
                "{number:5.3} " +
                "]" +
                "}");
        List<Float> endList = listCasterFactory.fromJSONFloat(obj.getJSONArray("data"),"number");
        Assert.isTrue((6f) == endList.get(0),"Error bad cast!");
        Assert.isTrue((5.8f) == endList.get(1),"Error bad cast!");
        Assert.isTrue((5.3f) == endList.get(2),"Error bad cast!");
    }
    @Test
    public void fromJSONToIntTest() throws JSONException {
        JSONObject obj = new JSONObject("{data: " +
                "[" +
                "{number:6 , name:Alan}, " +
                "{number:5, name:Alan}, " +
                "{number:7, name:Alan} " +
                "]" +
                "}");
        List<Integer> endList = listCasterFactory.fromJSONInt(obj.getJSONArray("data"),"number");
        Assert.isTrue((6) == endList.get(0),"Error bad cast!");
        Assert.isTrue((5) == endList.get(1),"Error bad cast!");
        Assert.isTrue((7) == endList.get(2),"Error bad cast!");
    }


}
