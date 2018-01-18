package in.dragonbra.bptfserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * @author lngtr
 * @since 2017-10-03
 */
@Entity
@Table(name = "MISSING_ICON", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_UNIQUE_ICON",
                columnNames = {"DEFINDEX", "AUSTRALIUM", "WEAPON_WEAR"})
})
public class MissingIcon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(name = "DEFINDEX", nullable = false)
    @JsonProperty("defindex")
    private Integer defindex;

    @Column(name = "AUSTRALIUM", nullable = false)
    @JsonProperty("australium")
    private Boolean australium;

    @Column(name = "WEAPON_WEAR", nullable = false)
    @JsonProperty("weapon_wear")
    private Integer weaponWear;

    @Column(name = "FAILED", nullable = false)
    @JsonProperty("failed")
    private Boolean failed;

    public MissingIcon() {
        this(null);
    }

    public MissingIcon(Integer defindex) {
        this.defindex = defindex;
        australium = false;
        weaponWear = 0;
        failed = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDefindex() {
        return defindex;
    }

    public void setDefindex(Integer defindex) {
        this.defindex = defindex;
    }

    public Boolean getAustralium() {
        return australium;
    }

    public void setAustralium(Boolean australium) {
        this.australium = australium;
    }

    public Integer getWeaponWear() {
        return weaponWear;
    }

    public void setWeaponWear(Integer weaponWear) {
        this.weaponWear = weaponWear;
    }

    public Boolean getFailed() {
        return failed;
    }

    public void setFailed(Boolean failed) {
        this.failed = failed;
    }
}
