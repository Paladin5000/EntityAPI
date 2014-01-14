package io.snw.entityapi.entity;

import io.snw.entityapi.api.ControllableEntityType;
import io.snw.entityapi.api.EntitySound;
import org.bukkit.entity.Spider;

public class ControllableCaveSpider extends ControllableAttackingBaseEntity<Spider> {

    public ControllableCaveSpider(ControllableCaveSpiderEntity entityHandle) {
        super(ControllableEntityType.CAVE_SPIDER);
        this.handle = entityHandle;
        this.loot = entityHandle.getBukkitLoot();
    }

    public void initSounds() {
        this.setSound(EntitySound.IDLE, "mob.spider.say");
        this.setSound(EntitySound.HURT, "mob.spider.say");
        this.setSound(EntitySound.DEATH, "mob.spider.death");
        this.setSound(EntitySound.STEP, "mob.spider.step");
    }
}