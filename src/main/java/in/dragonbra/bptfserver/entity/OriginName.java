package in.dragonbra.bptfserver.entity;

import javax.persistence.*;

/**
 * @author lngtr
 * @since 8/13/2017
 */
@Entity
@Table(name = "ORIGIN_NAMES")
public class OriginName {

    @Id
    private Long id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
