package in.dragonbra.bptfserver.retrofit.response.backpacktf.getprices;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * @author lngtr
 * @since 7/21/2017
 */
public class GetPricesResponse {
    private Integer success;

    private String message;

    @SerializedName("current_time")
    private Long currentTime;

    @SerializedName("raw_usd_value")
    private Double rawUsdValue;

    @SerializedName("usd_currency")
    private String usdCurrency;

    @SerializedName("usd_currency_index")
    private Integer usdCurrencyIndex;

    private Map<String, GetPricesItem> items;

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

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }

    public Double getRawUsdValue() {
        return rawUsdValue;
    }

    public void setRawUsdValue(Double rawUsdValue) {
        this.rawUsdValue = rawUsdValue;
    }

    public String getUsdCurrency() {
        return usdCurrency;
    }

    public void setUsdCurrency(String usdCurrency) {
        this.usdCurrency = usdCurrency;
    }

    public Integer getUsdCurrencyIndex() {
        return usdCurrencyIndex;
    }

    public void setUsdCurrencyIndex(Integer usdCurrencyIndex) {
        this.usdCurrencyIndex = usdCurrencyIndex;
    }

    public Map<String, GetPricesItem> getItems() {
        return items;
    }

    public void setItems(Map<String, GetPricesItem> items) {
        this.items = items;
    }
}
