package in.dragonbra.bptfserver.retrofit.interceptor;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author lngtr
 * @since 7/21/2017
 */
public class QueryParamInterceptor implements Interceptor {

    private String key;
    private String value;

    public QueryParamInterceptor(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter(key, value)
                .build();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
