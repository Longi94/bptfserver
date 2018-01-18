package in.dragonbra.bptfserver.retrofit.deserializer;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import in.dragonbra.bptfserver.retrofit.response.backpacktf.getprices.GetPricesPrice;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lngtr
 * @since 7/22/2017
 */
public class PricesDeserializer implements JsonDeserializer<Map<String, GetPricesPrice>> {

    private Gson gson = new Gson();

    @Override
    public Map<String, GetPricesPrice> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Map<String, GetPricesPrice> values = new HashMap<>();
        if (json.isJsonArray()) {
            int i = 0;
            for (JsonElement e : json.getAsJsonArray()) {
                values.put(String.valueOf(i++), context.deserialize(e, GetPricesPrice.class));
            }
        } else if (json.isJsonObject()) {
            Type typeToken = new TypeToken<Map<String, GetPricesPrice>>() {}.getType();
            values = gson.fromJson(json, typeToken);
        } else {
            throw new JsonParseException("Unexpected JSON type: " + json.getClass());
        }
        return values;
    }
}
