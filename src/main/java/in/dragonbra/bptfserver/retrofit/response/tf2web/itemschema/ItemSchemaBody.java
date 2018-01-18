package in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema;

import com.google.gson.annotations.SerializedName;

/**
 * @author lngtr
 * @since 8/13/2017
 */
public class ItemSchemaBody {

    @SerializedName("result")
    private ItemSchemaResult result;

    public ItemSchemaResult getResult() {
        return result;
    }

    public void setResult(ItemSchemaResult result) {
        this.result = result;
    }
}
