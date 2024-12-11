package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectPojo {
    private String name;
    private String type;
    private boolean exotic;

    public boolean isExotic() {
        return exotic;
    }

    public void setExotic(boolean exotic) {
        this.exotic = exotic;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
