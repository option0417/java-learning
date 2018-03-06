package tw.com.wd.obj;

import java.io.Serializable;

public class BObj implements Serializable {
    private String name;
    private int id;
    private transient static final AObj aObj;

    static {
        aObj = new AObj();
        aObj.setId(1);
        aObj.setName("NameA");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AObj getaObj() {
        return aObj;
    }
}
