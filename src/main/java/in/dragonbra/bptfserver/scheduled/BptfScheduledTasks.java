package in.dragonbra.bptfserver.scheduled;

import in.dragonbra.bptfserver.retrofit.response.backpacktf.getprices.GetPricesBody;
import in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema.SchemaItemsBody;
import in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema.SchemaOverviewBody;
import in.dragonbra.bptfserver.service.BackpackTfApiService;
import in.dragonbra.bptfserver.service.BptfService;
import in.dragonbra.bptfserver.service.Tf2WebApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author lngtr
 * @since 7/21/2017
 */
@Component
@ConditionalOnProperty("cron.bptf.enabled")
public class BptfScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(BptfScheduledTasks.class);

    private final BackpackTfApiService backpackTfApiService;

    private final Tf2WebApiService tf2WebApiService;

    private final BptfService bptfService;

    @Autowired
    public BptfScheduledTasks(BackpackTfApiService backpackTfApiService,
                              Tf2WebApiService tf2WebApiService,
                              BptfService bptfService) {
        this.backpackTfApiService = backpackTfApiService;
        this.tf2WebApiService = tf2WebApiService;
        this.bptfService = bptfService;
    }

    @Scheduled(cron = "${cron.bptf.prices}")
    public void downloadPrices() throws IOException {
        logger.info("Downloading prices...");

        GetPricesBody body = backpackTfApiService.getPrices();

        bptfService.processGetPricesBody(body);
    }

    @Scheduled(cron = "${cron.bptf.item-schema}")
    public void downloadItemSchema() throws IOException {
        logger.info("Downloading itemschema...");

        SchemaOverviewBody overviewBody = tf2WebApiService.getTf2SchemaOverview();

        bptfService.processSchemaOverviewBody(overviewBody);

        Integer next = 0;

        while (next != null) {
            SchemaItemsBody itemsBody = tf2WebApiService.getTf2SchemaItems(next);
            bptfService.processItemSchemaBody(itemsBody);

            if (itemsBody == null || itemsBody.getResult() == null) {
                next = null;
            } else {
                next = itemsBody.getResult().getNext();
            }
        }
    }
}
