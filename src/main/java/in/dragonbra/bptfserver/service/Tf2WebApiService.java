package in.dragonbra.bptfserver.service;

import in.dragonbra.bptfserver.retrofit.Tf2WebApiInterface;
import in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema.ItemSchemaBody;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * @author lngtr
 * @since 8/13/2017
 */
@Service
public class Tf2WebApiService {

    private static final String LANGUAGE = "en";

    private static final Logger logger = LogManager.getLogger(BackpackTfApiService.class);

    @Autowired private Tf2WebApiInterface tf2WebApiInterface;

    public ItemSchemaBody getTf2ItemSchema() throws IOException {
        Call<ItemSchemaBody> call = tf2WebApiInterface.getTf2ItemSchema(LANGUAGE);

        Response<ItemSchemaBody> response = call.execute();

        logger.info("response code: " + response.code() + ", response message: " + response.message());

        return response.body();
    }
}
