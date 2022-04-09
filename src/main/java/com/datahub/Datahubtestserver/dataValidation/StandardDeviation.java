package com.datahub.Datahubtestserver.dataValidation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StandardDeviation {


    public static List<Integer> getBadDataIndexes(List<Double> dataList){
        double mean = calculateMean(dataList);
        double standardDeviation = calculateStandardDeviation(dataList,mean);
        return Stream.iterate(0, n -> n+1).
                limit(dataList.size()).
                filter(x -> !(Math.abs(dataList.get(x) - mean) < standardDeviation)).
                collect(Collectors.toList());
    }


    private static float calculateStandardDeviation(List<Double> dataList,Double mean){
        return (float) Math.sqrt(
                dataList.stream().
                        map(x -> (x - mean)*(x - mean)).
                        reduce((double) 0, Double::sum) / dataList.size()
        );
    }


    private static double calculateMean(List<Double> dataList){
        return dataList.stream().
                reduce((double) 0, Double::sum) / dataList.size();
    }
}
