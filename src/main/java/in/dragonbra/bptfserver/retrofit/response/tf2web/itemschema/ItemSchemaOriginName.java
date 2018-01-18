package in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema;

import com.google.gson.annotations.SerializedName;

/**
 * @author lngtr
 * @since 8/13/2017
 */
public class ItemSchemaOriginName {

    @SerializedName("origin")
    private Long origin;

    @SerializedName("name")
    private String name;

    public Long getOrigin() {
        return origin;
    }

    public void setOrigin(Long origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
