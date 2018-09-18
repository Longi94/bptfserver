package in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema;

import com.google.gson.annotations.SerializedName;

/**
 * @author lngtr
 * @since 2018-09-18
 */
public class SchemaOverviewBody {

    @SerializedName("result")
    private SchemaOverviewResult result;

    public SchemaOverviewResult getResult() {
        return result;
    }

    public void setResult(SchemaOverviewResult result) {
        this.result = result;
    }
}
