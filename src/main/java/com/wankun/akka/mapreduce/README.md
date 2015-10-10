# 使用akka实现mapreduce程序处理


```sequence
Title: Data Flow
Master->MapActor: String
MapActor-->Master: MapData
Master->ReduceActor: MapData
ReduceActor-->Master: ReduceData
Master->AggregateActor: ReduceData
AggregateActor-->Master: String
```