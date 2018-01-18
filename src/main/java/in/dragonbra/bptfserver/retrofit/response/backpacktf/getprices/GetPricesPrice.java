package in.dragonbra.bptfserver.retrofit.response.backpacktf.getprices;

import com.google.gson.annotations.SerializedName;

/**
 * @author lngtr
 * @since 7/21/2017
 */
public class GetPricesPrice {

    private String currency;

    private Double value;

    @SerializedName("value_high")
    private Double valueHigh;

    @SerializedName("value_raw")
    private Double valueRaw;

    @SerializedName("value_raw_high")
    private Double valueRawHigh;

    @SerializedName("last_update")
    private Long lastUpdate;

    private Double difference;

    private Boolean australium;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getValueHigh() {
        return valueHigh;
    }

    public void setValueHigh(Double valueHigh) {
        this.valueHigh = valueHigh;
    }

    public Double getValueRaw() {
        return valueRaw;
    }

    public void setValueRaw(Double valueRaw) {
        this.valueRaw = valueRaw;
    }

    public Double getValueRawHigh() {
        return valueRawHigh;
    }

    public void setValueRawHigh(Double valueRawHigh) {
        this.valueRawHigh = valueRawHigh;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Double getDifference() {
        return difference;
    }

    public void setDifference(Double difference) {
        this.difference = difference;
    }

    public Boolean getAustralium() {
        return australium;
    }

    public void setAustralium(Boolean australium) {
        this.australium = australium;
    }
}
