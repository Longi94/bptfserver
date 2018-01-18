package in.dragonbra.bptfserver.retrofit.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import in.dragonbra.bptfserver.model.tf2.Tf2Class;

import java.lang.reflect.Type;

/**
 * @author lngtr
 * @since 8/13/2017
 */
public class Tf2ClassDeserializer implements JsonDeserializer<Tf2Class> {
    @Override
    public Tf2Class deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Tf2Class.from(json.getAsString());
    }
}
