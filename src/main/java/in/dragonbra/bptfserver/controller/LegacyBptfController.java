package in.dragonbra.bptfserver.controller;

import in.dragonbra.bptfserver.controller.response.ItemPriceResponse;
import in.dragonbra.bptfserver.controller.response.ItemSchemaItemResponse;
import in.dragonbra.bptfserver.controller.response.ItemSchemaResponse;
import in.dragonbra.bptfserver.controller.response.PricesResponse;
import in.dragonbra.bptfserver.repository.*;
import in.dragonbra.bptfserver.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * @author lngtr
 * @since 7/24/2017
 */
@Deprecated
@RestController
@RequestMapping("/bptf/legacy")
public class LegacyBptfController {

    private static final Logger logger = LoggerFactory.getLogger(LegacyBptfController.class);

    private final ItemPriceRepository itemPriceRepository;

    private final ItemSchemaRepository itemSchemaRepository;

    private final DecoratedWeaponRepository decoratedWeaponRepository;

    private final OriginNameRepository originNameRepository;

    private final ParticleSchemaRepository particleSchemaRepository;

    @Autowired
    public LegacyBptfController(ItemPriceRepository itemPriceRepository,
                                ItemSchemaRepository itemSchemaRepository,
                                DecoratedWeaponRepository decoratedWeaponRepository,
                                OriginNameRepository originNameRepository,
                                ParticleSchemaRepository particleSchemaRepository) {
        this.itemPriceRepository = itemPriceRepository;
        this.itemSchemaRepository = itemSchemaRepository;
        this.decoratedWeaponRepository = decoratedWeaponRepository;
        this.originNameRepository = originNameRepository;
        this.particleSchemaRepository = particleSchemaRepository;
    }

    @RequestMapping(value = "/prices", method = RequestMethod.GET)
    public PricesResponse prices(@RequestParam(name = "since", defaultValue = "0") long since) {
        PricesResponse response = new PricesResponse();

        response.setSuccess(1);
        response.setPrices(itemPriceRepository.findAllByUpdateTsGreaterThanEqual(since).stream().map(entity -> {
            ItemPriceResponse price = new ItemPriceResponse();

            price.setAustralium(entity.getAustralium() ? 1 : 0);
            price.setCraftable(entity.getCraftable() ? 1 : 0);
            price.setCurrency(entity.getCurrency());
            price.setDefindex(entity.getDefindex());
            price.setDifference(entity.getDifference());
            price.setPrice(entity.getPrice());
            price.setPriceIndex(entity.getPriceIndex());
            price.setPriceMax(entity.getPriceMax());
            price.setPriceRaw(entity.getPriceRaw());
            price.setQuality(entity.getQuality());
            price.setTradable(entity.getTradable() ? 1 : 0);
            price.setUpdateTs(entity.getUpdateTs());
            price.setWeaponWear(entity.getWeaponWear());

            return price;
        }).collect(Collectors.toList()));

        return response;
    }

    @RequestMapping(value = "/item_schema", method = RequestMethod.GET)
    public ItemSchemaResponse itemSchema() {
        ItemSchemaResponse response = new ItemSchemaResponse();

        response.setItems(itemSchemaRepository.findAllIncludeOverrides().stream().map(entity -> {
            ItemSchemaItemResponse item = new ItemSchemaItemResponse();

            item.setDefindex(entity.getDefindex());
            item.setItemDescription(entity.getItemDescription());
            item.setItemName(entity.getItemName());
            item.setProperName(entity.getProperName() ? 1 : 0);
            item.setTypeName(entity.getTypeName());

            return item;
        }).collect(Collectors.toList()));
        response.setDecoratedWeapons(CollectionUtils.toList(decoratedWeaponRepository.findAll()));
        response.setOriginNames(CollectionUtils.toList(originNameRepository.findAll()));
        response.setParticleNames(CollectionUtils.toList(particleSchemaRepository.findAll()));
        response.setSuccess(1);

        return response;
    }
}