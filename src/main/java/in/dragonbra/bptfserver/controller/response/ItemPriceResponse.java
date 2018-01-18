package in.dragonbra.bptfserver.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author lngtr
 * @since 8/13/2017
 */
public class ItemPriceResponse {

    @JsonProperty("defindex")
    private Integer defindex;

    @JsonProperty("quality")
    private Integer quality;

    @JsonProperty("tradable")
    private Integer tradable;

    @JsonProperty("craftable")
    private Integer craftable;

    @JsonProperty("price_index")
    private Integer priceIndex;

    @JsonProperty("australium")
    private Integer australium;

    @JsonProperty("value")
    private Double price;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("value_high")
    private Double priceMax;

    @JsonProperty("value_raw")
    private Double priceRaw;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("last_update")
    private Long updateTs;

    @JsonProperty("difference")
    private Double difference;

    @JsonProperty("weapon_wear")
    private Integer weaponWear;

    public Integer getDefindex() {
        return defindex;
    }

    public void setDefindex(Integer defindex) {
        this.defindex = defindex;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Integer getTradable() {
        return tradable;
    }

    public void setTradable(Integer tradable) {
        this.tradable = tradable;
    }

    public Integer getCraftable() {
        return craftable;
    }

    public void setCraftable(Integer craftable) {
        this.craftable = craftable;
    }

    public Integer getPriceIndex() {
        return priceIndex;
    }

    public void setPriceIndex(Integer priceIndex) {
        this.priceIndex = priceIndex;
    }

    public Integer getAustralium() {
        return australium;
    }

    public void setAustralium(Integer australium) {
        this.australium = australium;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Double priceMax) {
        this.priceMax = priceMax;
    }

    public Double getPriceRaw() {
        return priceRaw;
    }

    public void setPriceRaw(Double priceRaw) {
        this.priceRaw = priceRaw;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Long updateTs) {
        this.updateTs = updateTs;
    }

    public Double getDifference() {
        return difference;
    }

    public void setDifference(Double difference) {
        this.difference = difference;
    }

    public Integer getWeaponWear() {
        return weaponWear;
    }

    public void setWeaponWear(Integer weaponWear) {
        this.weaponWear = weaponWear;
    }
}
