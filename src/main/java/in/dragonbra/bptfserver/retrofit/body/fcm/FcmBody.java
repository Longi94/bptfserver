package in.dragonbra.bptfserver.retrofit.body.fcm;

import com.google.gson.annotations.SerializedName;

/**
 * @author lngtr
 * @since 2017-09-28
 */
public class FcmBody {

    @SerializedName("to")
    private String to;

    @SerializedName("data")
    private Data data;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String message;

        public Data(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
