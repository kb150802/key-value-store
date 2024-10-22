package org.example;

public class Pair<F, S> {
    private final F first;
    private final S second;
    Pair(F first, S second){
        this.first = first;
        this.second = second;
    }
    F getFirst(){
        return first;
    }
    S getSecond(){
        return second;
    }
    public boolean equals(Pair<F, S> p){
        return p.first.equals(first) && p.second.equals(second);
    }
}
