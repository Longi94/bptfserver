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
            "  CASE WHEN (image_override.IMAGE_URL IS NULL) " +
            "    THEN item_schema.IMAGE_URL " +
            "  ELSE image_override.IMAGE_URL " +
            "  END AS `IMAGE_URL`, " +
            "  CASE WHEN (image_override.IMAGE_URL_LARGE IS NULL) " +
            "    THEN item_schema.IMAGE_URL_LARGE " +
            "  ELSE image_override.IMAGE_URL_LARGE " +
            "  END AS `IMAGE_URL_LARGE` " +
            "FROM item_schema " +
            "  LEFT JOIN name_override ON item_schema.DEFINDEX = name_override.DEFINDEX " +
            "  LEFT JOIN image_override ON item_schema.DEFINDEX = image_override.DEFINDEX " +
            "ORDER BY item_schema.DEFINDEX ASC", nativeQuery = true)
    List<ItemSchema> findAllIncludeOverrides();

    ItemSchema findFirstByDefindex(int defindex);
}
