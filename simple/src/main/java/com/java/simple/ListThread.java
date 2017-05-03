package com.java.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */
public class ListThread {
    private static volatile List list = new ArrayList();

    public void add(){
        list.add("zzzz");
    }

    public int size(){
        return list.size();
    }
}
