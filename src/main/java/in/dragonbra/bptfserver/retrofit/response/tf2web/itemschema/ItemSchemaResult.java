package in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lngtr
 * @since 8/13/2017
 */
public class ItemSchemaResult {

    @SerializedName("originNames")
    private List<ItemSchemaOriginName> originNames = new ArrayList<>();

    @SerializedName("items")
    private List<ItemSchemaItem> items = new ArrayList<>();

    @SerializedName("attribute_controlled_attached_particles")
    private List<ItemSchemaParticle> particles = new ArrayList<>();

    public List<ItemSchemaOriginName> getOriginNames() {
        return originNames;
    }

    public void setOriginNames(List<ItemSchemaOriginName> originNames) {
        this.originNames = originNames;
    }

    public List<ItemSchemaItem> getItems() {
        return items;
    }

    public void setItems(List<ItemSchemaItem> items) {
        this.items = items;
    }

    public List<ItemSchemaParticle> getParticles() {
        return particles;
    }

    public void setParticles(List<ItemSchemaParticle> particles) {
        this.particles = particles;
    }
}
