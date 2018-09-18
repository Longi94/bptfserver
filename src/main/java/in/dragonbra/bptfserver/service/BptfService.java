package in.dragonbra.bptfserver.service;

import in.dragonbra.bptfserver.entity.ItemPrice;
import in.dragonbra.bptfserver.entity.ItemSchema;
import in.dragonbra.bptfserver.entity.OriginName;
import in.dragonbra.bptfserver.entity.ParticleSchema;
import in.dragonbra.bptfserver.repository.ItemPriceRepository;
import in.dragonbra.bptfserver.retrofit.response.backpacktf.getprices.GetPricesBody;
import in.dragonbra.bptfserver.retrofit.response.backpacktf.getprices.GetPricesItem;
import in.dragonbra.bptfserver.retrofit.response.backpacktf.getprices.GetPricesPrice;
import in.dragonbra.bptfserver.retrofit.response.backpacktf.getprices.GetPricesResponse;
import in.dragonbra.bptfserver.retrofit.response.fcm.FcmResponse;
import in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author lngtr
 * @since 7/23/2017
 */
@Service
public class BptfService {

    private static final Logger logger = LogManager.getLogger(BptfService.class);

    private final ItemPriceRepository itemPriceRepository;

    private final EntityManager entityManager;

    private final FcmService fcmService;

    @Autowired
    public BptfService(ItemPriceRepository itemPriceRepository, EntityManager entityManager, FcmService fcmService) {
        this.itemPriceRepository = itemPriceRepository;
        this.entityManager = entityManager;
        this.fcmService = fcmService;
    }

    @Transactional
    public void processGetPricesBody(GetPricesBody body) {
        if (body == null) {
            // TODO: 7/23/2017
            throw new IllegalArgumentException("body is null");
        }

        GetPricesResponse response = body.getResponse();

        if (response == null) {
            // TODO: 7/23/2017
            throw new IllegalArgumentException("response is null");
        }

        if (response.getSuccess() != 1) {
            logger.warn("GetPrices api success code is " + response.getSuccess() + ", message " + response.getMessage());
            return;
        }

        itemPriceRepository.deleteAll();
        entityManager.flush();

        int count = 0;

        Long latestUpdateTime = itemPriceRepository.getLatestUpdateTime();
        if (latestUpdateTime == null) {
            latestUpdateTime = 0L;
        }

        boolean newItems = false;

        for (Map.Entry<String, GetPricesItem> itemEntry : response.getItems().entrySet()) {
            GetPricesItem item = itemEntry.getValue();

            List<Integer> defindexes = item.getDefindexes();
            Map<Integer, Map<String, Map<String, Map<String, GetPricesPrice>>>> prices = item.getPrices();
            if (item.getPrices() == null || defindexes == null || defindexes.isEmpty()) {
                continue;
            }

            for (Map.Entry<Integer, Map<String, Map<String, Map<String, GetPricesPrice>>>> qualityEntry : prices.entrySet()) {
                Integer quality = qualityEntry.getKey();

                for (Map.Entry<String, Map<String, Map<String, GetPricesPrice>>> tradabilityEntry : qualityEntry.getValue().entrySet()) {
                    boolean tradable = "Tradable".equals(tradabilityEntry.getKey());

                    for (Map.Entry<String, Map<String, GetPricesPrice>> craftabilityEntry : tradabilityEntry.getValue().entrySet()) {
                        boolean craftable = "Craftable".equals(craftabilityEntry.getKey());

                        for (Map.Entry<String, GetPricesPrice> priceEntry : craftabilityEntry.getValue().entrySet()) {
                            String priceIndexString = priceEntry.getKey();
                            GetPricesPrice price = priceEntry.getValue();

                            if (price.getValue() == null) {
                                continue;
                            }

                            int priceIndex;

                            if (priceIndexString.contains("-")) {
                                String[] split = priceIndexString.split("-");
                                priceIndex = Integer.parseInt(split[0]) + (Integer.parseInt(split[1]) << 16);
                            } else {
                                priceIndex = Integer.parseInt(priceIndexString);
                            }

                            newItems = newItems || price.getLastUpdate() > latestUpdateTime;

                            ItemPrice itemPrice = new ItemPrice();

                            itemPrice.setDefindex(defindexes.get(0));
                            itemPrice.setQuality(quality);
                            itemPrice.setTradable(tradable);
                            itemPrice.setCraftable(craftable);
                            itemPrice.setPriceIndex(priceIndex);
                            itemPrice.setPrice(price.getValue());
                            itemPrice.setPriceMax(price.getValueHigh());
                            itemPrice.setPriceRaw(price.getValueRaw());
                            itemPrice.setCurrency(price.getCurrency());
                            itemPrice.setUpdateTs(price.getLastUpdate());
                            itemPrice.setDifference(price.getDifference());
                            itemPrice.setAustralium(price.getAustralium() == null ? false : price.getAustralium());
                            itemPrice.setWeaponWear(0);

                            itemPriceRepository.save(itemPrice);

                            count++;
                        }
                    }
                }
            }
        }

        logger.info("Processed " + count + " prices");

        if (count > 0) {
            try {
                FcmResponse fcmResponse = fcmService.sendTopicNotification("price_updates");

                if (fcmResponse.getError() != null) {
                    logger.warn("FCM returned error: " + fcmResponse.getError());
                } else {
                    logger.info("FCM message id " + fcmResponse.getMessageId());
                }
            } catch (IOException e) {
                logger.error("Failed to send notification", e);
            }
        }
    }

    @Transactional
    public void processItemSchemaBody(SchemaOverviewBody body, SchemaItemsBody itemsBody) {

        if (body == null) {
            // TODO: 7/23/2017
            throw new IllegalArgumentException("body is null");
        }

        if (itemsBody == null) {
            // TODO: 7/23/2017
            throw new IllegalArgumentException("itemsBody is null");
        }

        SchemaOverviewResult result = body.getResult();
        SchemaItemsResult itemResult = itemsBody.getResult();

        if (result == null) {
            // TODO: 7/23/2017
            throw new IllegalArgumentException("result is null");
        }

        int count = 0;

        for (ItemSchemaItem item : itemResult.getItems()) {
            ItemSchema itemSchema = new ItemSchema();

            itemSchema.setDefindex(item.getDefindex());
            itemSchema.setItemName(item.getItemName());
            itemSchema.setItemDescription(item.getItemDescription());
            itemSchema.setProperName(item.getProperName());
            itemSchema.setTypeName(item.getItemTypeName());
            itemSchema.setImageUrl(item.getImageUrl());
            itemSchema.setImageUrlLarge(item.getImageUrlLarge());

            entityManager.merge(itemSchema);

            count++;
        }

        logger.info("processed " + count + " items");

        count = 0;

        for (ItemSchemaOriginName originName : result.getOriginNames()) {
            OriginName originNameEntity = new OriginName();

            originNameEntity.setName(originName.getName());
            originNameEntity.setId(originName.getOrigin());

            entityManager.merge(originNameEntity);

            count++;
        }

        logger.info("processed " + count + " origin names");

        count = 0;

        for (ItemSchemaParticle particle : result.getParticles()) {
            ParticleSchema particleSchema = new ParticleSchema();

            particleSchema.setId(particle.getId());
            particleSchema.setName(particle.getName());

            entityManager.merge(particleSchema);

            count++;
        }

        logger.info("processed " + count + " particles schemas");
    }
}
