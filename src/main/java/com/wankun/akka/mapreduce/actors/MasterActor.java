package com.wankun.akka.mapreduce.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;
import com.wankun.akka.mapreduce.message.MapData;
import com.wankun.akka.mapreduce.message.ReduceData;
import com.wankun.akka.mapreduce.message.Result;

/**
 * Author : wankun
 * Date : 2015/10/9 10:59
 */
public class MasterActor extends UntypedActor {
    ActorRef mapActor = getContext().actorOf(
            new Props(MapActor.class).withRouter(new RoundRobinRouter(5)), "map");
    ActorRef reduceActor = getContext().actorOf(
            new Props(ReduceActor.class).withRouter(new RoundRobinRouter(5)), "reduce");
    ActorRef aggregateActor = getContext().actorOf(new Props(AggregateActor.class), "aggregate");

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            mapActor.tell(message, getSelf());
        } else if (message instanceof MapData) {
            reduceActor.tell(message, getSelf());
        } else if (message instanceof ReduceData) {
            aggregateActor.tell(message);
        } else if (message instanceof Result) {
            aggregateActor.forward(message, getContext());
        } else
            unhandled(message);
    }
}