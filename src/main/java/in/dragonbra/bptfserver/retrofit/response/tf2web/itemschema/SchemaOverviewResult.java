package in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lngtr
 * @since 2018-09-18
 */
public class SchemaOverviewResult {

    @SerializedName("status")
    private int status;

    @SerializedName("attribute_controlled_attached_particles")
    private List<ItemSchemaParticle> particles = new ArrayList<>();

    @SerializedName("originNames")
    private List<ItemSchemaOriginName> originNames = new ArrayList<>();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ItemSchemaParticle> getParticles() {
        return particles;
    }

    public void setParticles(List<ItemSchemaParticle> particles) {
        this.particles = particles;
    }

    public List<ItemSchemaOriginName> getOriginNames() {
        return originNames;
    }

    public void setOriginNames(List<ItemSchemaOriginName> originNames) {
        this.originNames = originNames;
    }
}
