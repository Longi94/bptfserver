package in.dragonbra.bptfserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * @author lngtr
 * @since 7/23/2017
 */
@Entity
@Table(name = "ITEM_PRICE", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_UNIQUE_ITEM",
                columnNames = {"DEFINDEX", "QUALITY", "TRADABLE", "CRAFTABLE", "PRICE_INDEX", "AUSTRALIUM", "WEAPON_WEAR"})
})
public class ItemPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private BigInteger id;

    @Column(name = "DEFINDEX", nullable = false)
    @JsonProperty("defindex")
    private Integer defindex;

    @Column(name = "QUALITY", nullable = false)
    @JsonProperty("quality")
    private Integer quality;

    @Column(name = "TRADABLE", nullable = false)
    @JsonProperty("tradable")
    private Boolean tradable;

    @Column(name = "CRAFTABLE", nullable = false)
    @JsonProperty("craftable")
    private Boolean craftable;

    @Column(name = "PRICE_INDEX", nullable = false)
    @JsonProperty("price_index")
    private Integer priceIndex;

    @Column(name = "AUSTRALIUM", nullable = false)
    @JsonProperty("australium")
    private Boolean australium;

    @Column(name = "PRICE", nullable = false)
    @JsonProperty("price")
    private Double price;

    @Column(name = "PRICE_MAX")
    @JsonProperty("price_max")
    private Double priceMax;

    @Column(name = "PRICE_RAW", nullable = false)
    @JsonProperty("price_raw")
    private Double priceRaw;

    @Column(name = "CURRENCY", nullable = false)
    @JsonProperty("currency")
    private String currency;

    @Column(name = "UPDATE_TS", nullable = false)
    @JsonProperty("update_ts")
    private Long updateTs;

    @Column(name = "DIFFERENCE", nullable = false)
    @JsonProperty("difference")
    private Double difference;

    @Column(name = "WEAPON_WEAR", nullable = false)
    @JsonProperty("weapon_wear")
    private Integer weaponWear;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

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

    public Boolean getTradable() {
        return tradable;
    }

    public void setTradable(Boolean tradable) {
        this.tradable = tradable;
    }

    public Boolean getCraftable() {
        return craftable;
    }

    public void setCraftable(Boolean craftable) {
        this.craftable = craftable;
    }

    public Integer getPriceIndex() {
        return priceIndex;
    }

    public void setPriceIndex(Integer priceIndex) {
        this.priceIndex = priceIndex;
    }

    public Boolean getAustralium() {
        return australium;
    }

    public void setAustralium(Boolean australium) {
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
