package in.dragonbra.bptfserver.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author lngtr
 * @since 8/13/2017
 */
public class ItemSchemaItemResponse {

    @JsonProperty("defindex")
    private Integer defindex;

    @JsonProperty("name")
    private String itemName;

    @JsonProperty("description")
    private String itemDescription;

    @JsonProperty("type_name")
    private String typeName;

    @JsonProperty("proper_name")
    private Integer properName;

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

    public Integer getProperName() {
        return properName;
    }

    public void setProperName(Integer properName) {
        this.properName = properName;
    }
}
