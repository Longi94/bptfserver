package in.dragonbra.bptfserver.retrofit;

import in.dragonbra.bptfserver.retrofit.body.fcm.FcmBody;
import in.dragonbra.bptfserver.retrofit.response.fcm.FcmResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author lngtr
 * @since 2017-09-28
 */
public interface FcmInterface {

    String BASE_URL = "https://fcm.googleapis.com/";

    @Headers("Content-Type:application/json")
    @POST("fcm/send")
    Call<FcmResponse> sendNotification(@Body FcmBody body);
}
