package com.datahub.Datahubtestserver.dataValidation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StandardDeviation {


    public static List<Integer> getBadDataIndexes(List<Float> dataList){
        float mean = calculateMean(dataList);
        float standardDeviation = calculateStandardDeviation(dataList,mean);
        return Stream.iterate(0, n -> n+1).
                limit(dataList.size()).
                filter(x -> !(Math.abs(dataList.get(x) - mean) < standardDeviation)).
                collect(Collectors.toList());
    }


    private static float calculateStandardDeviation(List<Float> dataList,Float mean){
        return (float) Math.sqrt(
                dataList.stream().
                        map(x -> (x - mean)*(x - mean)).
                        reduce(0f, Float::sum) / dataList.size()
        );
    }


    private static float calculateMean(List<Float> dataList){
        return dataList.stream().
                reduce(0f, Float::sum) / dataList.size();
    }
}
