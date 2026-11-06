package com.godtrident;

import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
   modid = "god_trident",
   value = {Dist.CLIENT},
   bus = Bus.MOD
)
public class ClientSetup {
   @SubscribeEvent
   public static void onRegisterRenderers(RegisterRenderers var0) {
      var0.registerEntityRenderer((EntityType)GodTrident.GOD_TRIDENT_ENTITY.get(), GodTridentRenderer::new);
   }
}
