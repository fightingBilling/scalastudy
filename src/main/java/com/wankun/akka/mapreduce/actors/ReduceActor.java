package com.wankun.akka.mapreduce.actors;

/**
 * Author : wankun
 * Date : 2015/10/9 10:56
 */

import akka.actor.UntypedActor;
import com.wankun.akka.mapreduce.message.MapData;
import com.wankun.akka.mapreduce.message.ReduceData;
import com.wankun.akka.mapreduce.message.WordCount;

import java.util.HashMap;
import java.util.List;

public class ReduceActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof MapData) {
            MapData mapData = (MapData) message;
// reduce the incoming data and forward the result to  Master actor
            getSender().tell(reduce(mapData.getDataList()));
        } else
            unhandled(message);
    }

    private ReduceData reduce(List<WordCount> dataList) {
        HashMap<String, Integer> reducedMap = new HashMap<String,
                Integer>();
        for (WordCount wordCount : dataList) {
            if (reducedMap.containsKey(wordCount.getWord())) {
                Integer value = (Integer)
                        reducedMap.get(wordCount.getWord());
                value++;
                reducedMap.put(wordCount.getWord(), value);
            } else {
                reducedMap.put(wordCount.getWord(),
                        Integer.valueOf(1));
            }
        }
        return new ReduceData(reducedMap);
    }
}