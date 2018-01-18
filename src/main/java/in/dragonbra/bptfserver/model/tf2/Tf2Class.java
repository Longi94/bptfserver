package in.dragonbra.bptfserver.model.tf2;

import java.util.Arrays;

/**
 * @author lngtr
 * @since 8/13/2017
 */
public enum Tf2Class {
    SCOUT("Scout"),
    SOLDIER("Soldier"),
    PYRO("Pyro"),
    DEMOMAN("Demoman"),
    HEAVY("Heavy"),
    ENGINEER("Engineer"),
    MEDIC("Medic"),
    SNIPER("Sniper"),
    SPY("Spy");

    private final String name;

    Tf2Class(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Tf2Class from(String name) {
        return Arrays.stream(Tf2Class.values())
                .filter(clazz -> clazz.name.equals(name))
                .findFirst()
                .orElse(null);
    }
}
