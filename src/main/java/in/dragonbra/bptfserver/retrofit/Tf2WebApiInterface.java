package in.dragonbra.bptfserver.retrofit;

import in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema.SchemaItemsBody;
import in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema.SchemaOverviewBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author lngtr
 * @since 8/13/2017
 */
public interface Tf2WebApiInterface {

    String BASE_URL = "http://api.steampowered.com/";

    @GET("IEconItems_440/GetSchemaOverview/v0001/")
    Call<SchemaOverviewBody> getTf2SchemaOverview(@Query("language") String language);

    @GET("IEconItems_440/GetSchemaItems/v0001/")
    Call<SchemaItemsBody> getTf2SchemaItems(
            @Query("start") Integer start,
            @Query("language") String language
    );
}
