package org.example;

import java.util.List;
import java.util.Set;

public class ValueForKey {
    private final List<Pair<String, Object>> value;

    public ValueForKey(List<Pair<String, Object>> value){
        this.value = value;
    }
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Pair<String,Object> attribute : value) {
            stringBuilder.append(attribute.getFirst()).append(":").append(attribute.getSecond()).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public boolean search(Pair<String, String> attribute) {
        for(Pair<String,Object> a : value){
            Pair<String,String> p =new Pair<>(a.getFirst(), a.getSecond().toString());
            if(p.equals(attribute)){
                return true;
            }
        }
        return false;
    }
}
