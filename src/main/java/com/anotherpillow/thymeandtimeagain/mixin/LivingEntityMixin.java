package com.anotherpillow.thymeandtimeagain.mixin;

import com.anotherpillow.thymeandtimeagain.ThymeAndTimeAgain;
import com.anotherpillow.thymeandtimeagain.item.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Shadow
    public ItemStack getItemInHand(InteractionHand p_21121_) {
        return null;
    }

    @Inject(
            method="Lnet/minecraft/world/entity/LivingEntity;checkTotemDeathProtection(Lnet/minecraft/world/damagesource/DamageSource;)Z",
            at=@At("HEAD"),
            cancellable = true
    )
    public void checkTotemDeathProtection(DamageSource p_21263_, CallbackInfoReturnable<Boolean> cir) {
        if (p_21263_.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return;
        } else {
            ItemStack itemstack = null;

            for (InteractionHand interactionhand : InteractionHand.values()) {
                ItemStack itemstack1 = this.getItemInHand(interactionhand);
                if (itemstack1.is(ModItems.TEMPORAL_TOTEM.get()) && net.neoforged.neoforge.common.CommonHooks.onLivingUseTotem((LivingEntity)(Object)this, p_21263_, itemstack1, interactionhand)) {
                    itemstack = itemstack1.copy();
                    itemstack1.shrink(1);
                    break;
                }
            }

            if (itemstack == null) return;

            if (itemstack != null) {
                if ((LivingEntity)(Object)this instanceof ServerPlayer serverplayer) {
                    serverplayer.awardStat(Stats.ITEM_USED.get(ModItems.TEMPORAL_TOTEM.get()), 1);
                    CriteriaTriggers.USED_TOTEM.trigger(serverplayer, itemstack);
                    ((LivingEntity)(Object)this).gameEvent(GameEvent.ITEM_INTERACT_FINISH);

                    Vec3 dest = ThymeAndTimeAgain.pastPositions.getFirst();
                    serverplayer.teleportTo(dest.x, dest.y, dest.z);
                }

                ((LivingEntity)(Object)this).setHealth(6.0F);
                ((LivingEntity)(Object)this).addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 5 /*5 seconds */, 1));
                ((LivingEntity)(Object)this).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 1 /*5 seconds */, 20));
                ((LivingEntity)(Object)this).level().broadcastEntityEvent((LivingEntity)(Object)this, (byte)36);
            }


            if (itemstack != null) {
                cir.setReturnValue(itemstack != null);
                cir.cancel();
            }
        }
    }
}
