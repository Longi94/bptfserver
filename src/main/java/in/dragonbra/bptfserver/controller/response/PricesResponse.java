package in.dragonbra.bptfserver.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author lngtr
 * @since 8/13/2017
 */
@Deprecated
public class PricesResponse {

    @JsonProperty("success")
    private Integer success;

    @JsonProperty("message")
    private String message;

    @JsonProperty("prices")
    private List<ItemPriceResponse> prices;

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

    public List<ItemPriceResponse> getPrices() {
        return prices;
    }

    public void setPrices(List<ItemPriceResponse> prices) {
        this.prices = prices;
    }
}
