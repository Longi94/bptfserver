package in.dragonbra.bptfserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * @author lngtr
 * @since 8/13/2017
 */
@Entity
@Table(name = "ITEM_SCHEMA")
public class ItemSchema {

    @Id
    @Column(name = "DEFINDEX")
    @JsonProperty("defindex")
    private Integer defindex;

    @Column(name = "ITEM_NAME", nullable = false)
    @JsonProperty("item_name")
    private String itemName;

    @Column(name = "ITEM_DESCRIPTION", length = 1000)
    @JsonProperty("item_description")
    private String itemDescription;

    @Column(name = "TYPE_NAME", nullable = false, length = 100)
    @JsonProperty("type_name")
    private String typeName;

    @Column(name = "PROPER_NAME", nullable = false)
    @JsonProperty("proper_name")
    private Boolean properName;

    @Column(name = "IMAGE_URL")
    @JsonIgnore
    private String imageUrl;

    @Column(name = "IMAGE_URL_LARGE")
    @JsonIgnore
    private String imageUrlLarge;

    public Integer getDefindex() {
        return defindex;
    }

    public void setDefindex(Integer defindex) {
        this.defindex = defindex;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Boolean getProperName() {
        return properName;
    }

    public void setProperName(Boolean properName) {
        this.properName = properName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrlLarge() {
        return imageUrlLarge;
    }

    public void setImageUrlLarge(String imageUrlLarge) {
        this.imageUrlLarge = imageUrlLarge;
    }
}
