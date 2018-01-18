package in.dragonbra.bptfserver.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import in.dragonbra.bptfserver.model.tf2.Tf2Class;
import in.dragonbra.bptfserver.retrofit.BackpackTfInterface;
import in.dragonbra.bptfserver.retrofit.FcmInterface;
import in.dragonbra.bptfserver.retrofit.Tf2WebApiInterface;
import in.dragonbra.bptfserver.retrofit.deserializer.PricesDeserializer;
import in.dragonbra.bptfserver.retrofit.deserializer.Tf2ClassDeserializer;
import in.dragonbra.bptfserver.retrofit.interceptor.HeaderInterceptor;
import in.dragonbra.bptfserver.retrofit.interceptor.QueryParamInterceptor;
import in.dragonbra.bptfserver.retrofit.response.backpacktf.getprices.GetPricesPrice;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author lngtr
 * @since 7/21/2017
 */
@Configuration
public class RetrofitConfig {

    private static final int TIMEOUT = 120;

    @Value("${api.key.bptf}")
    private String backpackTfApiKey;

    @Value("${api.key.tf2-web}")
    private String tf2WebApiKey;

    @Value("${fcm.server-key}")
    private String fcmApiKey;

    @Bean
    public BackpackTfInterface backpackTfInterface() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new QueryParamInterceptor("key", backpackTfApiKey))
                .build();

        Type typeToken = new TypeToken<Map<String, GetPricesPrice>>() {}.getType();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(typeToken, new PricesDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BackpackTfInterface.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(BackpackTfInterface.class);
    }

    @Bean
    public Tf2WebApiInterface tf2WebApiInterface() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new QueryParamInterceptor("key", tf2WebApiKey))
                .build();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Tf2Class.class, new Tf2ClassDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Tf2WebApiInterface.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(Tf2WebApiInterface.class);
    }

    @Bean
    public FcmInterface fcmInterface() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor("Authorization", "key=" + fcmApiKey))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FcmInterface.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(FcmInterface.class);
    }
}