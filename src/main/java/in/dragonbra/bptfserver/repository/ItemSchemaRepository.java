package in.dragonbra.bptfserver.repository;

import in.dragonbra.bptfserver.entity.ItemSchema;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author lngtr
 * @since 8/13/2017
 */
public interface ItemSchemaRepository extends CrudRepository<ItemSchema, Long> {

    @Query(value = "SELECT " +
            "  item_schema.DEFINDEX, " +
            "  CASE WHEN (name_override.NAME IS NULL) " +
            "    THEN item_schema.ITEM_NAME " +
            "  ELSE name_override.NAME " +
            "  END AS `ITEM_NAME`, " +
            "  item_schema.ITEM_DESCRIPTION, " +
            "  item_schema.TYPE_NAME, " +
            "  item_schema.PROPER_NAME, " +
            "  item_schema.IMAGE_URL, " +
            "  item_schema.IMAGE_URL_LARGE " +
            "FROM item_schema " +
            "  LEFT JOIN name_override ON item_schema.DEFINDEX = name_override.DEFINDEX " +
            "ORDER BY item_schema.DEFINDEX ASC", nativeQuery = true)
    List<ItemSchema> findAllIncludeNameOverrides();

    ItemSchema findFirstByDefindex(int defindex);
}
