package okhttp;


import java.io.Serializable;

/**
 * Created by lixiaonan on 2019/2/15.
 */
public class Book implements Serializable {
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
