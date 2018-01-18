package in.dragonbra.bptfserver.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import in.dragonbra.bptfserver.entity.DecoratedWeapon;
import in.dragonbra.bptfserver.entity.OriginName;
import in.dragonbra.bptfserver.entity.ParticleSchema;

import java.util.List;

/**
 * @author lngtr
 * @since 8/13/2017
 */
public class ItemSchemaResponse {

    @JsonProperty("success")
    private Integer success;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("message")
    private String message;

    @JsonProperty("items")
    private List<ItemSchemaItemResponse> items;

    @JsonProperty("origins")
    private List<OriginName> originNames;

    @JsonProperty("particle_names")
    private List<ParticleSchema> particleNames;

    @JsonProperty("decorated_weapons")
    private List<DecoratedWeapon> decoratedWeapons;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ItemSchemaItemResponse> getItems() {
        return items;
    }

    public void setItems(List<ItemSchemaItemResponse> items) {
        this.items = items;
    }

    public List<OriginName> getOriginNames() {
        return originNames;
    }

    public void setOriginNames(List<OriginName> originNames) {
        this.originNames = originNames;
    }

    public List<ParticleSchema> getParticleNames() {
        return particleNames;
    }

    public void setParticleNames(List<ParticleSchema> particleNames) {
        this.particleNames = particleNames;
    }

    public List<DecoratedWeapon> getDecoratedWeapons() {
        return decoratedWeapons;
    }

    public void setDecoratedWeapons(List<DecoratedWeapon> decoratedWeapons) {
        this.decoratedWeapons = decoratedWeapons;
    }
}
