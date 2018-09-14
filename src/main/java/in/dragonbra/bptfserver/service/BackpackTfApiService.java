package in.dragonbra.bptfserver.service;

import in.dragonbra.bptfserver.retrofit.BackpackTfInterface;
import in.dragonbra.bptfserver.retrofit.response.backpacktf.getprices.GetPricesBody;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * @author lngtr
 * @since 7/23/2017
 */
@Service
public class BackpackTfApiService {

    private static final Logger logger = LogManager.getLogger(BackpackTfApiService.class);

    private final BackpackTfInterface backpackTfInterface;

    @Autowired
    public BackpackTfApiService(BackpackTfInterface backpackTfInterface) {
        this.backpackTfInterface = backpackTfInterface;
    }

    public GetPricesBody getPrices() throws IOException {
        Call<GetPricesBody> call = backpackTfInterface.getPrices();

        Response<GetPricesBody> response = call.execute();

        logger.info("response code: " + response.code() + ", response message: " + response.message());

        return response.body();
    }
}
