package in.dragonbra.bptfserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lngtr
 * @since 8/13/2017
 */
@Entity
@Table(name = "DECORATED_WEAPON")
public class DecoratedWeapon {

    @Id
    @Column(name = "DEFINDEX")
    private Integer defindex;

    @Column(name = "GRADE", nullable = false)
    private Integer grade;

    public Integer getDefindex() {
        return defindex;
    }

    public void setDefindex(Integer defindex) {
        this.defindex = defindex;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
