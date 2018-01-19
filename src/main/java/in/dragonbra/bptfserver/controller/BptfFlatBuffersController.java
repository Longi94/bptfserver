package in.dragonbra.bptfserver.controller;

import com.google.flatbuffers.FlatBufferBuilder;
import in.dragonbra.bptfserver.entity.ItemPrice;
import in.dragonbra.bptfserver.flatbuffers.Price;
import in.dragonbra.bptfserver.flatbuffers.Prices;
import in.dragonbra.bptfserver.repository.ItemPriceRepository;
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

    @Autowired
    public BptfFlatBuffersController(ItemPriceRepository itemPriceRepository) {
        this.itemPriceRepository = itemPriceRepository;
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
}
