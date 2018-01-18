package retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import in.dragonbra.bptfserver.retrofit.deserializer.PricesDeserializer;
import in.dragonbra.bptfserver.retrofit.response.backpacktf.getprices.GetPricesBody;
import in.dragonbra.bptfserver.retrofit.response.backpacktf.getprices.GetPricesPrice;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class JsonSerializationTest {

    @Test
    public void testDeserializeGetPrices() {
        Type typeToken = new TypeToken<Map<String, GetPricesPrice>>() {}.getType();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(typeToken, new PricesDeserializer())
                .create();

        InputStream inputstream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("get_prices_response.json");
        GetPricesBody body = gson.fromJson(new InputStreamReader(inputstream), GetPricesBody.class);

        assertNotNull(body);

        assertNotNull(body.getResponse());

        assertNotNull(body.getResponse().getCurrentTime());
        assertNotNull(body.getResponse().getRawUsdValue());
        assertNotNull(body.getResponse().getUsdCurrency());
        assertNotNull(body.getResponse().getUsdCurrencyIndex());
        assertNotNull(body.getResponse().getItems());
    }
}
