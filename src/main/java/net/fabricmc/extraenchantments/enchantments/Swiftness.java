package net.fabricmc.extraenchantments.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;

public class Swiftness extends Enchantment {
    public Swiftness(Rarity weight, EquipmentSlot[] slotTypes) {
        super(weight, EnchantmentTarget.WEAPON, slotTypes);
    }

    public int getMinPower(int level) {
        return 1 + 10 * (level - 1);
    }

    public int getMaxPower(int level) {
        return super.getMinPower(level) + 50;
    }

    public int getMaxLevel() {
        return 5;
    }

    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof PickaxeItem || stack.getItem() instanceof AxeItem || stack.getItem() instanceof ShovelItem || stack.getItem() instanceof HoeItem || super.isAcceptableItem(stack);
    }
}
