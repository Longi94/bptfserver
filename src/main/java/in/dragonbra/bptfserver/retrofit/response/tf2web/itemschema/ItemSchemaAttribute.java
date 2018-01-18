package in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema;

import com.google.gson.annotations.SerializedName;

/**
 * @author lngtr
 * @since 8/13/2017
 */
public class ItemSchemaAttribute {

    @SerializedName("name")
    private String name;

    @SerializedName("class")
    private String clazz;

    @SerializedName("value")
    private Double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
