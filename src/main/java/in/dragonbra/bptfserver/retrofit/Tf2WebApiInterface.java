package in.dragonbra.bptfserver.retrofit;

import in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema.ItemSchemaBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author lngtr
 * @since 8/13/2017
 */
public interface Tf2WebApiInterface {

    String BASE_URL = "http://api.steampowered.com/";

    @GET("IEconItems_440/GetSchema/v0001/")
    Call<ItemSchemaBody> getTf2ItemSchema(@Query("language") String language);
}
