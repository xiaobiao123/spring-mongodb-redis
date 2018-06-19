package model.H8build.build2.t1;

import java.util.ArrayList;

/**
 * Created by gwb on 2018/6/14.
 */
public class ProductPbject {

    private ArrayList list = new ArrayList();

    public void addObject(Object object) {
        list.add(object);
    }
    public void getObject() {
        for (Object o : list) {
            System.out.println(o.toString());
        }
    }


}
