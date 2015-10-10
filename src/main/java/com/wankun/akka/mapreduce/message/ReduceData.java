package com.wankun.akka.mapreduce.message;

/**
 * Author : wankun
 * Date : 2015/10/9 10:47
 */
import java.util.HashMap;
public final class ReduceData {
    private final HashMap<String, Integer> reduceDataList;
    public HashMap<String, Integer> getReduceDataList() {
        return reduceDataList;
    }
    public ReduceData(HashMap<String, Integer> reduceDataList) {
        this.reduceDataList = reduceDataList;
    }
}