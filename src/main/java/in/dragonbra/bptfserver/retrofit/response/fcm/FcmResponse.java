package in.dragonbra.bptfserver.retrofit.response.fcm;

import com.google.gson.annotations.SerializedName;

/**
 * @author lngtr
 * @since 2017-09-28
 */
public class FcmResponse {

    @SerializedName("message_id")
    private String messageId;

    @SerializedName("error")
    private String error;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
