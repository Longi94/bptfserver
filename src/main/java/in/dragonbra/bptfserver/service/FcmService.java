package in.dragonbra.bptfserver.service;

import in.dragonbra.bptfserver.retrofit.FcmInterface;
import in.dragonbra.bptfserver.retrofit.body.fcm.FcmBody;
import in.dragonbra.bptfserver.retrofit.response.fcm.FcmResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * @author lngtr
 * @since 2017-09-28
 */
@Service
public class FcmService {

    private static final Logger logger = Logger.getLogger(FcmService.class);

    private final FcmInterface fcmInterface;

    @Autowired
    public FcmService(FcmInterface fcmInterface) {
        this.fcmInterface = fcmInterface;
    }

    public FcmResponse sendTopicNotification(String topic) throws IOException {
        return sendTopicNotification(topic, "");
    }

    public FcmResponse sendTopicNotification(String topic, String data) throws IOException {
        FcmBody body = new FcmBody();

        body.setTo("/topics/" + topic);
        body.setData(new FcmBody.Data(data));

        Call<FcmResponse> call = fcmInterface.sendNotification(body);

        Response<FcmResponse> response = call.execute();

        logger.info("response code: " + response.code() + ", response message: " + response.message());

        return response.body();
    }
}
