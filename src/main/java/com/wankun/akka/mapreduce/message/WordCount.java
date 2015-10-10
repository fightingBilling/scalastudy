package com.wankun.akka.mapreduce.message;

/**
 * Author : wankun
 * Date : 2015/10/9 10:46
 */
public final class WordCount {
    private final String word;
    private final Integer count;

    public WordCount(String inWord, Integer inCount) {
        word = inWord;
        count = inCount;
    }

    public String getWord() {
        return word;
    }

    public Integer getCount() {
        return count;
    }
}