package in.dragonbra.bptfserver.scheduled;

import in.dragonbra.bptfserver.entity.ItemSchema;
import in.dragonbra.bptfserver.entity.MissingIcon;
import in.dragonbra.bptfserver.repository.ItemSchemaRepository;
import in.dragonbra.bptfserver.repository.MissingIconRepository;
import in.dragonbra.bptfserver.retrofit.response.backpacktf.getprices.GetPricesBody;
import in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema.ItemSchemaBody;
import in.dragonbra.bptfserver.service.BackpackTfApiService;
import in.dragonbra.bptfserver.service.BptfService;
import in.dragonbra.bptfserver.service.Tf2WebApiService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author lngtr
 * @since 7/21/2017
 */
@Component
@ConditionalOnProperty("cron.bptf.enabled")
public class BptfScheduledTasks {

    private static final Logger logger = LogManager.getLogger(BptfScheduledTasks.class);

    @Autowired
    private BackpackTfApiService backpackTfApiService;

    @Autowired
    private Tf2WebApiService tf2WebApiService;

    @Autowired
    private BptfService bptfService;

    @Autowired
    private MissingIconRepository missingIconRepository;

    @Autowired
    private ItemSchemaRepository itemSchemaRepository;

    @Value("${bptf.icon-dir}")
    private String iconDir;

    @Value("${bptf.pngquant-command}")
    private String pngquantCommand;

    @Scheduled(cron = "${cron.bptf.prices}")
    public void downloadPrices() throws IOException {
        logger.info("Downloading prices...");

        GetPricesBody body = backpackTfApiService.getPrices();

        bptfService.processGetPricesBody(body);
    }

    @Scheduled(cron = "${cron.bptf.item-schema}")
    public void downloadItemSchema() throws IOException {
        logger.info("Downloading itemschema...");

        ItemSchemaBody body = tf2WebApiService.getTf2ItemSchema();

        bptfService.processItemSchemaBody(body);
    }

    @Scheduled(cron = "${cron.bptf.missing-icons}")
    public void downloadMissingIcons() throws IOException, InterruptedException {
        logger.info("Checking for missing icons...");

        OkHttpClient client = new OkHttpClient();
        List<MissingIcon> missingIcons = missingIconRepository.getSimpleMissingIcons();

        int defindex;
        boolean failed;
        String path;
        FileOutputStream fos;

        for (MissingIcon missingIcon : missingIcons) {
            defindex = missingIcon.getDefindex();
            path = iconDir + defindex + ".png";
            failed = false;

            ItemSchema schema = itemSchemaRepository.findFirstByDefindex(defindex);

            if (schema != null && schema.getImageUrl() != null) {

                String url = schema.getImageUrl();

                logger.info("Downloading icon for " + defindex + " " + url);
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();

                if (response.isSuccessful() && response.body() != null) {
                    fos = new FileOutputStream(path);
                    fos.write(response.body().bytes());
                    fos.close();
                } else {
                    logger.warn("Failed to download " + url + " code " + response.code() + " " + response.message());
                    failed = true;
                }
            } else {
                logger.warn(defindex + " has no schema or icon url");
                failed = true;
            }

            if (failed) {
                missingIcon.setFailed(true);
                missingIconRepository.save(missingIcon);
            } else {
                String command = pngquantCommand.replaceAll("\\{0\\}", path);
                logger.info("Running command: " + command);

                Process proc = Runtime.getRuntime().exec(command);
                proc.waitFor();

                missingIconRepository.delete(missingIcon);
            }
        }
    }
}
