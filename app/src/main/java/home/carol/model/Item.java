package home.carol.model;

/**
 * Created by tonnyquintos on 12/26/15.
 */
public class Item {

    String name;
    Boolean on;

    String url;

    public Item(String name, Boolean on, String url){
        this.name = name;
        this.on = on;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOn() {
        return on;
    }

    public void setOn(Boolean on) {
        this.on = on;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
