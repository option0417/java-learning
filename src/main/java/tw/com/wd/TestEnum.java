package tw.com.wd;

import java.util.ArrayList;
import java.util.List;

public enum TestEnum {
    T1, T2;


    private List<String> list;

    private TestEnum() {
        list = new ArrayList<>();
    }

    public List<String> getList() {
        return this.list;
    }
}

