package com.datahub.Datahubtestserver.dataValidation;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StandardDeviationTest {

    @Test
    public void getBadIndexesTest(){
        List<Float> dataList = Arrays.asList(1f,2f,43f);
        Float mean = (float) ((1 + 2 + 43) / 3.0);
        List<Integer> result = new ArrayList<>();
        Float standardDeviation = (float) Math.sqrt(((1 - mean) * (1 - mean) + (2 - mean) * (2 - mean) + (43 - mean) * (43 - mean)) / 3.0);
        for(int i=0;i<dataList.size();i+=1){
            if(Math.abs(dataList.get(i)-mean) > standardDeviation){
                result.add(i);
            }
        }
        System.out.println(result);
        List<Integer> functionResult = StandardDeviation.getBadDataIndexes(dataList);
        System.out.println(functionResult);
        Assert.isTrue(result.size() == functionResult.size(),"Error different sizes");
        for(int i=0;i<result.size();i+=1){
            Assert.isTrue(result.get(i) == functionResult.get(i),"Error different indexes");
        }
    }
}
