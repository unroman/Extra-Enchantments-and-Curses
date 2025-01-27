package net.fabricmc.extraenchantments.enchantments;

import net.fabricmc.extraenchantments.curses.CurseOfZeus;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.tag.DamageTypeTags;


public class Electrified extends Enchantment {

    private int hitsTarget;
    private int hitsAttacker;

    public Electrified(Rarity weight, EquipmentSlot[] slotTypes) {
        super(weight, EnchantmentTarget.WEARABLE, slotTypes);
        hitsTarget = 0;
        hitsAttacker = 0;
    }

    @Override
    public int getProtectionAmount(int level, DamageSource source) {
        return source.isIn(DamageTypeTags.IS_LIGHTNING) ? 4 : 0;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public int getMinPower(int level) {
        return 40;
    }

    @Override
    public int getMaxPower(int level) {
        return 70;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return !(other instanceof CurseOfZeus);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        int rng = (int) (Math.random() * 11);
        if (target instanceof LivingEntity && ((LivingEntity) target).getRecentDamageSource() != null) {
            if (user.getWorld().isThundering()) {
                hitsTarget++;
                if (rng <= 2 && hitsTarget == 1) {
                    LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(target.getWorld());
                    assert lightningEntity != null;
                    lightningEntity.refreshPositionAfterTeleport(target.getX(), target.getY(), target.getZ());
                    target.getWorld().spawnEntity(lightningEntity);
                }
                if (hitsTarget == 4) {
                    hitsTarget = 0;
                }
            }
        }
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        int rng = (int) (Math.random() * 11);
        if (attacker instanceof LivingEntity) {
            if (user.getWorld().isThundering()) {
                hitsAttacker++;
                if (rng <= 2 && hitsAttacker == 1) {
                    LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(attacker.getWorld());
                    assert lightningEntity != null;
                    lightningEntity.refreshPositionAfterTeleport(attacker.getX(), attacker.getY(), attacker.getZ());
                    attacker.getWorld().spawnEntity(lightningEntity);
                }
                if (hitsAttacker == 4) {
                    hitsAttacker = 0;
                }
            }
        }
    }
}
