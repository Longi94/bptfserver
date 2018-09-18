package in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lngtr
 * @since 2018-09-17
 */
public class SchemaItemsResult {

    @SerializedName("status")
    private int status;

    @SerializedName("items")
    private List<ItemSchemaItem> items = new ArrayList<>();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ItemSchemaItem> getItems() {
        return items;
    }

    public void setItems(List<ItemSchemaItem> items) {
        this.items = items;
    }
}
