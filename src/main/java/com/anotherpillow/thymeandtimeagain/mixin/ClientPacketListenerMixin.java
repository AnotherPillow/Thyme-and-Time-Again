package com.anotherpillow.thymeandtimeagain.mixin;

import com.anotherpillow.thymeandtimeagain.ThymeAndTimeAgain;
import com.anotherpillow.thymeandtimeagain.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.resources.sounds.GuardianAttackSoundInstance;
import net.minecraft.client.resources.sounds.SnifferSoundInstance;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @Shadow
    private static ItemStack findTotem(Player p_104928_) {
        return null;
    }

    @Shadow
    private ClientLevel level;

    @Inject(
            method="Lnet/minecraft/client/multiplayer/ClientPacketListener;handleEntityEvent(Lnet/minecraft/network/protocol/game/ClientboundEntityEventPacket;)V",
            at=@At("HEAD"),
            cancellable = true
    )
    public void handleEntityEvent(ClientboundEntityEventPacket p_105010_, CallbackInfo ci) {
//        PacketUtils.ensureRunningOnSameThread(p_105010_, this, ThymeAndTimeAgain.mc); // maybe this should be done?? ehh prob fine
        Entity entity = p_105010_.getEntity(this.level);
        if (p_105010_.getEventId() == 36 && entity != null) {
            int i = 40;
            ThymeAndTimeAgain.mc.particleEngine.createTrackingEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, 30);
            this.level.playLocalSound(entity.getX(), entity.getY(), entity.getZ(), SoundEvents.TOTEM_USE, entity.getSoundSource(), 1.0F, 1.0F, false);
            if (entity == ThymeAndTimeAgain.mc.player) {
                ThymeAndTimeAgain.mc.gameRenderer.displayItemActivation(ModItems.TEMPORAL_TOTEM.toStack());
            }
            ci.cancel();
        }
    }
}
