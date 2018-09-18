package in.dragonbra.bptfserver.service;

import in.dragonbra.bptfserver.retrofit.Tf2WebApiInterface;
import in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema.SchemaItemsBody;
import in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema.SchemaOverviewBody;
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

    private final Tf2WebApiInterface tf2WebApiInterface;

    @Autowired
    public Tf2WebApiService(Tf2WebApiInterface tf2WebApiInterface) {
        this.tf2WebApiInterface = tf2WebApiInterface;
    }

    public SchemaOverviewBody getTf2SchemaOverview() throws IOException {
        Call<SchemaOverviewBody> call = tf2WebApiInterface.getTf2SchemaOverview(LANGUAGE);

        Response<SchemaOverviewBody> response = call.execute();

        logger.info("response code: " + response.code() + ", response message: " + response.message());

        return response.body();
    }

    public SchemaItemsBody getTf2SchemaItems() throws IOException {
        Call<SchemaItemsBody> call = tf2WebApiInterface.getTf2SchemaItems(LANGUAGE);

        Response<SchemaItemsBody> response = call.execute();

        logger.info("response code: " + response.code() + ", response message: " + response.message());

        return response.body();
    }
}
