package com.datahub.Datahubtestserver.dataValidation;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListCasterFactory {

    public List<Integer> floatToInt (List<Float> dataList){
        return dataList.stream().
                map(Math::round).
                collect(Collectors.toList());
    }

    public List<Float> intToFloat (List<Integer> dataList){
        return dataList.stream().
                map(x -> (float) x).
                collect(Collectors.toList());
    }

    public List<Double> intToDouble (List<Integer> dataList){
        return dataList.stream().
                map(x -> (double) x).
                collect(Collectors.toList());
    }

    public List<Integer> doubleToInt (List<Double> dataList){
        return dataList.stream().
                map(x -> (int) Math.round(x)).
                collect(Collectors.toList());
    }

    public List<Float> fromJSONFloat(JSONArray jsonList, String columnName) throws JSONException {
        List<Float> result = new ArrayList<>();
        for(int i = 0 ; i < jsonList.length() ; i++){
            result.add(Float.parseFloat(jsonList.getJSONObject(i).
                    getString(columnName)));
        }
        return result;
    }

    public List<Integer> fromJSONInt(JSONArray jsonList, String columnName) throws JSONException {
        List<Integer> result = new ArrayList<>();
        for(int i = 0 ; i < jsonList.length() ; i++){
            result.add(Integer.parseInt(jsonList.getJSONObject(i).
                    getString(columnName)));
        }
        return result;
    }

    public List<String> fromJSONString(JSONArray jsonList, String columnName) throws JSONException {
        List<String> result = new ArrayList<>();
        for(int i = 0 ; i < jsonList.length() ; i++){
            result.add(jsonList.getJSONObject(i).
                    getString(columnName));
        }
        return result;
    }



}
