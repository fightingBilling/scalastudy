package com.wankun.akka.mapreduce.actors;

/**
 * Author : wankun
 * Date : 2015/10/9 10:58
 */

import akka.actor.UntypedActor;
import com.wankun.akka.mapreduce.message.ReduceData;
import com.wankun.akka.mapreduce.message.Result;

import java.util.HashMap;
import java.util.Map;

public class AggregateActor extends UntypedActor {
    private Map<String, Integer> finalReducedMap =
            new HashMap<String, Integer>();

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof ReduceData) {
            ReduceData reduceData = (ReduceData) message;
            aggregateInMemoryReduce(reduceData.
                    getReduceDataList());
        } else if (message instanceof Result) {
            getSender().tell(finalReducedMap.toString());
        } else
            unhandled(message);
    }

    private void aggregateInMemoryReduce(Map<String,
            Integer> reducedList) {
        Integer count = null;
        for (String key : reducedList.keySet()) {
            if (finalReducedMap.containsKey(key)) {
                count = reducedList.get(key) +
                        finalReducedMap.get(key);
                finalReducedMap.put(key, count);
            } else {
                finalReducedMap.put(key, reducedList.get(key));
            }
        }
    }

}