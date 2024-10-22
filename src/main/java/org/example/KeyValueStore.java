package org.example;

import javax.swing.text.StyledEditorKit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class KeyValueStore {
    private final Map<String, ValueForKey> store;
    private final Map<String, Class<?>> dataType;

    public KeyValueStore(){
        store = new ConcurrentHashMap<>();
        dataType = new ConcurrentHashMap<>();
    }
    public ValueForKey get(String key) {
        return store.get(key);
    }
    public void put(String key, List<Pair<String, String>> attributes) throws Exception {
        List<Pair<String, Object>> parsedAttributes = new ArrayList<>();
        for(Pair<String, String> attribute : attributes) {
            Object attributeValue = parseAttribute(attribute);
            parsedAttributes.add(new Pair<String, Object>(attribute.getFirst(), attributeValue));
        }
        ValueForKey value = new ValueForKey(parsedAttributes);
        store.put(key, value);
    }

    public void delete(String key){
        store.remove(key);
    }

    public Set<String> keys(){
        return store.keySet();
    }
    public Set<String> search(Pair<String, String> attributeToSearch) throws Exception {
        Set<String> keys = new TreeSet<>();
        store.forEach((key, valueList)->{
            if(valueList.search(attributeToSearch)) {
                keys.add(key);
            }
        });
        return keys;
    }
    private Object parseAttribute(Pair<String, String> attribute) throws Exception {
        Class<?> alreadyFoundDataType = dataType.get(attribute.getFirst());

        if(alreadyFoundDataType != null) {

            try {
                if (isInteger(attribute.getSecond())) {
                    if (alreadyFoundDataType != Integer.class)
                        throw new Exception("Data type Error");
                    return Integer.parseInt(attribute.getSecond());
                }
                if (isDouble(attribute.getSecond())) {
                    if (alreadyFoundDataType != Double.class)
                        throw new Exception("Data type Error");
                    return Double.parseDouble(attribute.getSecond());
                }
                if (isBoolean(attribute.getSecond())) {
                    if (alreadyFoundDataType != Boolean.class)
                        throw new Exception("Data type Error");
                    return Boolean.parseBoolean(attribute.getSecond());
                }
                if (alreadyFoundDataType != String.class) {
                    throw new Exception("Data type Error");
                }
                return attribute.getSecond();
            } catch (Exception exception) {
                throw new Exception("Data type Error");
            }
        }else {
            if (isInteger(attribute.getSecond())) {
                dataType.put(attribute.getFirst(), Integer.class);
                return Integer.parseInt(attribute.getSecond());
            }
            if (isDouble(attribute.getSecond())) {
                dataType.put(attribute.getFirst(), Double.class);
                return Double.parseDouble(attribute.getSecond());
            }
            if (isBoolean(attribute.getSecond())) {
                dataType.put(attribute.getFirst(), Boolean.class);
                return Boolean.parseBoolean(attribute.getSecond());
            }
            dataType.put(attribute.getFirst(), String.class);
            return attribute.getSecond();
        }
    }
    private boolean isInteger(String s){
        try{
            Integer.parseInt(s);
            return true;
        }
        catch (Exception ignore){
            return false;
        }
    }
    private boolean isBoolean(String s){
        return s.equals("true") || s.equals("false");
    }
    private boolean isDouble(String s){
        try{
            Double.parseDouble(s);
            return true;
        }
        catch (Exception ignore){
            return false;
        }
    }
}
