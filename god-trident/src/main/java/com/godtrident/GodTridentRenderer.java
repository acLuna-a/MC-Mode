package com.godtrident;

import net.minecraft.client.renderer.entity.ThrownTridentRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.ThrownTrident;

public class GodTridentRenderer extends ThrownTridentRenderer {
   private static final ResourceLocation TEXTURE = new ResourceLocation("god_trident", "textures/entity/god_trident.png");
   private static final ResourceLocation TEXTURE_DEMON = new ResourceLocation("god_trident", "textures/entity/god_trident_demon.png");
   private static final ResourceLocation TEXTURE_IMMORTAL = new ResourceLocation("god_trident", "textures/entity/god_trident_immortal.png");

   public GodTridentRenderer(Context var1) {
      super(var1);
   }

   public ResourceLocation m_5478_(ThrownTrident var1) {
      if (var1 instanceof ThrownGodTrident var2) {
         int var3 = var2.getGodVariant();
         if (var3 == 1) {
            return TEXTURE_DEMON;
         }

         if (var3 == 2) {
            return TEXTURE_IMMORTAL;
         }
      }

      return TEXTURE;
   }
}
