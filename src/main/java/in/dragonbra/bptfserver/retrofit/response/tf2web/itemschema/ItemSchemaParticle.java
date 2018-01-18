package in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema;

import com.google.gson.annotations.SerializedName;

/**
 * @author lngtr
 * @since 8/13/2017
 */
public class ItemSchemaParticle {

    @SerializedName("system")
    private String system;

    @SerializedName("id")
    private Long id;

    @SerializedName("attach_to_rootbone")
    private Boolean attachToRootbone;

    @SerializedName("name")
    private String name;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAttachToRootbone() {
        return attachToRootbone;
    }

    public void setAttachToRootbone(Boolean attachToRootbone) {
        this.attachToRootbone = attachToRootbone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
