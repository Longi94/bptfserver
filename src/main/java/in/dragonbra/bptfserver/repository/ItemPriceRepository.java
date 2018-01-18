package in.dragonbra.bptfserver.repository;

import in.dragonbra.bptfserver.entity.ItemPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author lngtr
 * @since 7/23/2017
 */
public interface ItemPriceRepository extends CrudRepository<ItemPrice, Long> {

    @Query(value = "SELECT MAX(UPDATE_TS) FROM ITEM_PRICE", nativeQuery = true)
    Long getLatestUpdateTime();

    List<ItemPrice> findAllByUpdateTsGreaterThanEqual(long since);
}
