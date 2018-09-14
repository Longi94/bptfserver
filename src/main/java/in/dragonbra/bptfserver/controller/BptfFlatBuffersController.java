package in.dragonbra.bptfserver.controller;

import com.google.flatbuffers.FlatBufferBuilder;
import in.dragonbra.bptfserver.entity.*;
import in.dragonbra.bptfserver.flatbuffers.itemschema.Item;
import in.dragonbra.bptfserver.flatbuffers.itemschema.Origin;
import in.dragonbra.bptfserver.flatbuffers.itemschema.Particle;
import in.dragonbra.bptfserver.flatbuffers.prices.Price;
import in.dragonbra.bptfserver.flatbuffers.prices.Prices;
import in.dragonbra.bptfserver.repository.*;
import in.dragonbra.bptfserver.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lngtr
 * @since 2018-01-19
 */
@Controller
@RequestMapping("/fbs")
public class BptfFlatBuffersController {

    private final ItemPriceRepository itemPriceRepository;

    private final ItemSchemaRepository itemSchemaRepository;

    private final DecoratedWeaponRepository decoratedWeaponRepository;

    private final OriginNameRepository originNameRepository;

    private final ParticleSchemaRepository particleSchemaRepository;

    @Autowired
    public BptfFlatBuffersController(ItemPriceRepository itemPriceRepository,
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

    @RequestMapping("/prices")
    public void prices(@RequestParam(name = "since", defaultValue = "0") long since,
                       HttpServletResponse response) throws IOException {
        FlatBufferBuilder builder = new FlatBufferBuilder();

        Map<String, Integer> currencies = new HashMap<>();

        currencies.put("metal", builder.createString("metal"));
        currencies.put("keys", builder.createString("keys"));
        currencies.put("usd", builder.createString("usd"));
        currencies.put("hat", builder.createString("hat"));

        List<ItemPrice> entities = itemPriceRepository.findAllByUpdateTsGreaterThanEqual(since);

        int[] priceOffsets = new int[entities.size()];

        for (int i = 0; i < priceOffsets.length; i++) {
            ItemPrice entity = entities.get(i);
            priceOffsets[i] = Price.createPrice(
                    builder,
                    entity.getDefindex(),
                    entity.getQuality().shortValue(),
                    entity.getTradable(),
                    entity.getCraftable(),
                    entity.getPriceIndex(),
                    entity.getAustralium(),
                    entity.getPrice().floatValue(),
                    entity.getPriceMax() == null ? 0 : entity.getPriceMax().floatValue(),
                    entity.getPriceRaw().floatValue(),
                    currencies.get(entity.getCurrency()),
                    entity.getUpdateTs(),
                    entity.getDifference().floatValue(),
                    entity.getWeaponWear()
            );
        }

        int prices = Prices.createPricesVector(builder, priceOffsets);

        Prices.startPrices(builder);
        Prices.addPrices(builder, prices);
        int pricesBuf = Prices.endPrices(builder);

        builder.finish(pricesBuf);

        ByteBuffer buffer = builder.dataBuffer();

        response.setHeader("Content-Type", "application/octet-stream");
        Channels.newChannel(response.getOutputStream()).write(buffer);
    }

    @RequestMapping("item_schema")
    public void itemSchema(HttpServletResponse response) throws IOException {
        FlatBufferBuilder builder = new FlatBufferBuilder();

        List<ItemSchema> itemSchemaList = itemSchemaRepository.findAllIncludeNameOverrides();

        int[] itemOffsets = new int[itemSchemaList.size()];

        for (int i = 0; i < itemOffsets.length; i++) {
            ItemSchema schema = itemSchemaList.get(i);

            int nameOffset = builder.createString(schema.getItemName());
            int typeOffset = builder.createString(schema.getTypeName());

            int descriptionOffset = -1;
            if (schema.getItemDescription() != null) {
                descriptionOffset = builder.createString(schema.getItemDescription());
            }

            int imageOffset = -1;
            if (schema.getImageUrl() != null) {
                imageOffset = builder.createString(schema.getImageUrl());
            }

            int imageLargeOffset = -1;
            if (schema.getImageUrlLarge() != null) {
                imageLargeOffset = builder.createString(schema.getImageUrlLarge());
            }

            Item.startItem(builder);
            Item.addDefindex(builder, schema.getDefindex());
            Item.addName(builder, nameOffset);
            Item.addProper(builder, schema.getProperName());
            Item.addType(builder, typeOffset);

            if (descriptionOffset != -1) {
                Item.addDescription(builder, descriptionOffset);
            }

            if (imageOffset != -1) {
                Item.addImage(builder, imageOffset);
            }

            if (imageLargeOffset != -1) {
                Item.addImageLarge(builder, imageLargeOffset);
            }

            itemOffsets[i] = Item.endItem(builder);
        }

        int itemsVector = in.dragonbra.bptfserver.flatbuffers.itemschema.ItemSchema.createItemsVector(builder, itemOffsets);

        List<DecoratedWeapon> decoratedWeapons = CollectionUtils.toList(decoratedWeaponRepository.findAll());

        int[] decoratedWeaponOffsets = new int[decoratedWeapons.size()];

        for (int i = 0; i < decoratedWeaponOffsets.length; i++) {
            decoratedWeaponOffsets[i] = in.dragonbra.bptfserver.flatbuffers.itemschema.DecoratedWeapon.createDecoratedWeapon(
                    builder,
                    decoratedWeapons.get(i).getDefindex(),
                    decoratedWeapons.get(i).getGrade()
            );
        }

        int decoratedWeaponVector = in.dragonbra.bptfserver.flatbuffers.itemschema.ItemSchema.createDecoratedWeaponVector(builder, decoratedWeaponOffsets);

        List<OriginName> originNames = CollectionUtils.toList(originNameRepository.findAll());

        int[] originOffsets = new int[originNames.size()];

        for (int i = 0; i < originOffsets.length; i++) {
            int nameOffset = builder.createString(originNames.get(i).getName());
            originOffsets[i] = Origin.createOrigin(builder, originNames.get(i).getId().intValue(), nameOffset);
        }

        int originVector = in.dragonbra.bptfserver.flatbuffers.itemschema.ItemSchema.createOriginsVector(builder, originOffsets);

        List<ParticleSchema> particleSchemas = CollectionUtils.toList(particleSchemaRepository.findAll());

        int[] particleOffsets = new int[particleSchemas.size()];

        for (int i = 0; i < particleOffsets.length; i++) {
            int nameOffset = builder.createString(particleSchemas.get(i).getName());
            particleOffsets[i] = Particle.createParticle(builder, particleSchemas.get(i).getId().intValue(), nameOffset);
        }

        int particleVector = in.dragonbra.bptfserver.flatbuffers.itemschema.ItemSchema.createParticleVector(builder, particleOffsets);

        in.dragonbra.bptfserver.flatbuffers.itemschema.ItemSchema.startItemSchema(builder);
        in.dragonbra.bptfserver.flatbuffers.itemschema.ItemSchema.addItems(builder, itemsVector);
        in.dragonbra.bptfserver.flatbuffers.itemschema.ItemSchema.addDecoratedWeapon(builder, decoratedWeaponVector);
        in.dragonbra.bptfserver.flatbuffers.itemschema.ItemSchema.addOrigins(builder, originVector);
        in.dragonbra.bptfserver.flatbuffers.itemschema.ItemSchema.addParticle(builder, particleVector);
        int itemSchemaBuf = in.dragonbra.bptfserver.flatbuffers.itemschema.ItemSchema.endItemSchema(builder);

        builder.finish(itemSchemaBuf);

        ByteBuffer buffer = builder.dataBuffer();

        response.setHeader("Content-Type", "application/octet-stream");
        Channels.newChannel(response.getOutputStream()).write(buffer);
    }
}
