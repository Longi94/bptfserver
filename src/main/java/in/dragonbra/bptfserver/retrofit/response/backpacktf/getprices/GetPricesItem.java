package in.dragonbra.bptfserver.retrofit.response.backpacktf.getprices;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * @author lngtr
 * @since 7/21/2017
 */
public class GetPricesItem {

    @SerializedName("defindex")
    private List<Integer> defindexes;

    // NOW THIS IS BEAUTIFUL
    private Map<Integer, Map<String, Map<String, Map<String, GetPricesPrice>>>> prices;

    public List<Integer> getDefindexes() {
        return defindexes;
    }

    public void setDefindexes(List<Integer> defindexes) {
        this.defindexes = defindexes;
    }

    public Map<Integer, Map<String, Map<String, Map<String, GetPricesPrice>>>> getPrices() {
        return prices;
    }

    public void setPrices(Map<Integer, Map<String, Map<String, Map<String, GetPricesPrice>>>> prices) {
        this.prices = prices;
    }
}
