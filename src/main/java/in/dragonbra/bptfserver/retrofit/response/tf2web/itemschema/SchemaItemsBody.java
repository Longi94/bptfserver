package in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema;

import com.google.gson.annotations.SerializedName;

/**
 * @author lngtr
 * @since 2018-09-17
 */
public class SchemaItemsBody {

    @SerializedName("result")
    private SchemaItemsResult result;

    public SchemaItemsResult getResult() {
        return result;
    }

    public void setResult(SchemaItemsResult result) {
        this.result = result;
    }
}
