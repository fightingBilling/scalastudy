package com.wankun.akka.mapreduce.message;

/**
 * Author : wankun
 * Date : 2015/10/9 10:37
 */

import java.util.List;

public final class MapData {
    private final List<WordCount> dataList;

    public List<WordCount> getDataList() {
        return dataList;
    }

    public MapData(List<WordCount> dataList) {
        this.dataList = dataList;
    }
}