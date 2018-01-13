package models;

import java.util.Collections;
import java.util.List;

public class ListDTO<T> extends AbstractJsonMapping{
    private List<T> list = Collections.emptyList();
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
