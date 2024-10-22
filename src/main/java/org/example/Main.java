package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        KeyValueStore keyValueStore = new KeyValueStore();
        try{
            keyValueStore.put("1", List.of(
                    new Pair<>("1", "true"),
                    new Pair<>("2", "2"),
                    new Pair<>("3", "3.00")
            ));
            System.out.println(keyValueStore.get("1"));
            keyValueStore.put("2", List.of(
                    new Pair<>("1", "false")
            ));
            System.out.println(keyValueStore.get("1"));
            keyValueStore.put("4", List.of(
                    new Pair<>("1","true")
            ));
            System.out.println(keyValueStore.search(new Pair<>("1","false")));

        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }

    }
}