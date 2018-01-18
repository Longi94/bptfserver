package in.dragonbra.bptfserver.retrofit.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author lngtr
 * @since 2017-09-28
 */
public class HeaderInterceptor implements Interceptor {

    private String name;
    private String value;

    public HeaderInterceptor(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .addHeader(name, value);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
