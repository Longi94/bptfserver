package in.dragonbra.bptfserver.repository;

import in.dragonbra.bptfserver.entity.MissingIcon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author lngtr
 * @since 2017-10-03
 */
public interface MissingIconRepository extends CrudRepository<MissingIcon, Long> {

    @Query(value = "SELECT * FROM missing_icon WHERE AUSTRALIUM = 0 AND WEAPON_WEAR = 0 AND FAILED = 0",
            nativeQuery = true)
    List<MissingIcon> getSimpleMissingIcons();
}
