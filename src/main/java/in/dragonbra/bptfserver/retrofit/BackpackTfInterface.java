package in.dragonbra.bptfserver.retrofit;

import in.dragonbra.bptfserver.retrofit.response.backpacktf.getprices.GetPricesBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author lngtr
 * @since 7/21/2017
 */
public interface BackpackTfInterface {

    String BASE_URL = "http://backpack.tf/api/";

    @GET("IGetPrices/v4/?compress=1&app_id=440&format=json&raw=1&since=0") // TODO always download all prices for now
    Call<GetPricesBody> getPrices();

}
