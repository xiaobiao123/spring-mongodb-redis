package model.H8build.build2;

import com.google.common.collect.Lists;

import java.util.List;

//Product类
class Product {

    List<String> parts = Lists.newArrayList();

    public void Add(String part) {
        parts.add(part);
    }

    public void Show() {
        for (int i = 0; i < parts.size(); i++) {
            System.out.println(parts.get(i));
        }
    }
};