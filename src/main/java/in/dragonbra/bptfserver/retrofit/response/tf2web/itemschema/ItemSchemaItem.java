package in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema;

import com.google.gson.annotations.SerializedName;
import in.dragonbra.bptfserver.model.tf2.Tf2Class;

import java.util.List;
import java.util.Set;

/**
 * @author lngtr
 * @since 8/13/2017
 */
public class ItemSchemaItem {

    @SerializedName("name")
    private String name;

    @SerializedName("defindex")
    private Integer defindex;

    @SerializedName("item_class")
    private String itemClass;

    @SerializedName("item_type_name")
    private String itemTypeName;

    @SerializedName("item_name")
    private String itemName;

    @SerializedName("item_description")
    private String itemDescription;

    @SerializedName("proper_name")
    private Boolean properName;

    @SerializedName("item_slot")
    private String itemSlot;

    @SerializedName("model_player")
    private String modelPlayer;

    @SerializedName("item_quality")
    private Integer itemQuality;

    @SerializedName("image_inventory")
    private String imageInventory;

    @SerializedName("min_ilevel")
    private Integer minIlevel;

    @SerializedName("max_ilevel")
    private Integer maxIlevel;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("image_url_large")
    private String imageUrlLarge;

    @SerializedName("craft_class")
    private String craftClass;

    @SerializedName("craft_material_type")
    private String craftMaterialType;

    @SerializedName("drop_type")
    private String dropType;

    @SerializedName("holiday_restriction")
    private String holidayRestriction;

    @SerializedName("capabilities")
    private ItemSchemaCapabilities capabilities;

    @SerializedName("used_by_classes")
    private Set<Tf2Class> usedByClasses;

    private List<ItemSchemaAttribute> attributes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDefindex() {
        return defindex;
    }

    public void setDefindex(Integer defindex) {
        this.defindex = defindex;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public Boolean getProperName() {
        return properName;
    }

    public void setProperName(Boolean properName) {
        this.properName = properName;
    }

    public String getItemSlot() {
        return itemSlot;
    }

    public void setItemSlot(String itemSlot) {
        this.itemSlot = itemSlot;
    }

    public String getModelPlayer() {
        return modelPlayer;
    }

    public void setModelPlayer(String modelPlayer) {
        this.modelPlayer = modelPlayer;
    }

    public Integer getItemQuality() {
        return itemQuality;
    }

    public void setItemQuality(Integer itemQuality) {
        this.itemQuality = itemQuality;
    }

    public String getImageInventory() {
        return imageInventory;
    }

    public void setImageInventory(String imageInventory) {
        this.imageInventory = imageInventory;
    }

    public Integer getMinIlevel() {
        return minIlevel;
    }

    public void setMinIlevel(Integer minIlevel) {
        this.minIlevel = minIlevel;
    }

    public Integer getMaxIlevel() {
        return maxIlevel;
    }

    public void setMaxIlevel(Integer maxIlevel) {
        this.maxIlevel = maxIlevel;
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

    public String getCraftClass() {
        return craftClass;
    }

    public void setCraftClass(String craftClass) {
        this.craftClass = craftClass;
    }

    public String getCraftMaterialType() {
        return craftMaterialType;
    }

    public void setCraftMaterialType(String craftMaterialType) {
        this.craftMaterialType = craftMaterialType;
    }

    public ItemSchemaCapabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(ItemSchemaCapabilities capabilities) {
        this.capabilities = capabilities;
    }

    public Set<Tf2Class> getUsedByClasses() {
        return usedByClasses;
    }

    public void setUsedByClasses(Set<Tf2Class> usedByClasses) {
        this.usedByClasses = usedByClasses;
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

    public String getDropType() {
        return dropType;
    }

    public void setDropType(String dropType) {
        this.dropType = dropType;
    }

    public String getHolidayRestriction() {
        return holidayRestriction;
    }

    public void setHolidayRestriction(String holidayRestriction) {
        this.holidayRestriction = holidayRestriction;
    }

    public List<ItemSchemaAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ItemSchemaAttribute> attributes) {
        this.attributes = attributes;
    }
}
