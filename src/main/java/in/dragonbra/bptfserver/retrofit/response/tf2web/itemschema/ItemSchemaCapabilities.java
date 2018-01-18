package in.dragonbra.bptfserver.retrofit.response.tf2web.itemschema;

import com.google.gson.annotations.SerializedName;

/**
 * @author lngtr
 * @since 8/13/2017
 */
public class ItemSchemaCapabilities {

    @SerializedName("nameable")
    private Boolean nameable;

    @SerializedName("can_gift_wrap")
    private Boolean canGiftWrap;

    @SerializedName("can_craft_mark")
    private Boolean canCraftMark;

    @SerializedName("can_be_restored")
    private Boolean canBeRestored;

    @SerializedName("strange_parts")
    private Boolean strangeParts;

    @SerializedName("can_card_upgrade")
    private Boolean canCardUpgrade;

    @SerializedName("can_strangify")
    private Boolean canStrangify;

    @SerializedName("can_killstreakify")
    private Boolean canKillstreakify;

    @SerializedName("can_consume")
    private Boolean canConsume;

    public Boolean getNameable() {
        return nameable;
    }

    public void setNameable(Boolean nameable) {
        this.nameable = nameable;
    }

    public Boolean getCanGiftWrap() {
        return canGiftWrap;
    }

    public void setCanGiftWrap(Boolean canGiftWrap) {
        this.canGiftWrap = canGiftWrap;
    }

    public Boolean getCanCraftMark() {
        return canCraftMark;
    }

    public void setCanCraftMark(Boolean canCraftMark) {
        this.canCraftMark = canCraftMark;
    }

    public Boolean getCanBeRestored() {
        return canBeRestored;
    }

    public void setCanBeRestored(Boolean canBeRestored) {
        this.canBeRestored = canBeRestored;
    }

    public Boolean getStrangeParts() {
        return strangeParts;
    }

    public void setStrangeParts(Boolean strangeParts) {
        this.strangeParts = strangeParts;
    }

    public Boolean getCanCardUpgrade() {
        return canCardUpgrade;
    }

    public void setCanCardUpgrade(Boolean canCardUpgrade) {
        this.canCardUpgrade = canCardUpgrade;
    }

    public Boolean getCanStrangify() {
        return canStrangify;
    }

    public void setCanStrangify(Boolean canStrangify) {
        this.canStrangify = canStrangify;
    }

    public Boolean getCanKillstreakify() {
        return canKillstreakify;
    }

    public void setCanKillstreakify(Boolean canKillstreakify) {
        this.canKillstreakify = canKillstreakify;
    }

    public Boolean getCanConsume() {
        return canConsume;
    }

    public void setCanConsume(Boolean canConsume) {
        this.canConsume = canConsume;
    }
}
