package cn.izy1314.verticaltabrecycler;

import java.io.Serializable;
import java.util.List;

/**
 * Create by hsw
 * on 2018/12/3.
 */
public class TestData implements Serializable {
    String name;
    List<String> itemName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getItemName() {
        return itemName;
    }

    public void setItemName(List<String> itemName) {
        this.itemName = itemName;
    }
}
