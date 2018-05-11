package com.dc.frame2.util;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Defferson.Cheng
 * @date 2018/4/17.
 */
public class MapBuilder<K,V> {
    private Map<K,V> map;
    private MapBuilder(){}
    
    public static MapBuilder<String, Object> dataMap() {
        MapBuilder<String, Object> builder = new MapBuilder<>();
        builder.map = new HashMap<>(3);
        return builder;
    }
    public static <K,V> MapBuilder<K,V> hashMap(){
        MapBuilder<K,V> builder=new MapBuilder<>();
        builder.map=new HashMap<>(3);
        return builder;
    }
    
    public MapBuilder<K, V> put(K key, V value) {
        map.put(key,value);
        return this;
    }
    
    public Map<K,V> build(){
        return map;
    }
}
