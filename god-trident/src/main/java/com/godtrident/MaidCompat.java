package com.godtrident;

import java.lang.reflect.Method;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
   modid = "god_trident",
   bus = Bus.FORGE
)
public final class MaidCompat {
   private MaidCompat() {
   }

   @SubscribeEvent
   public static void onEntityJoin(EntityJoinLevelEvent var0) {
      Entity var1 = var0.getEntity();
      if (!(var1 instanceof ThrownGodTrident)) {
         if (var1 instanceof ThrownTrident var2) {
            ItemStack var3 = readThrownStack(var2);
            if (isGodTrident(var3)) {
               var0.setCanceled(true);
               Level var4 = var0.getLevel();
               if (!var4.f_46443_) {
                  if (var2.m_19749_() instanceof LivingEntity var6) {
                     ItemStack var7 = var3.m_41619_() ? var6.m_21205_().m_41777_() : var3.m_41777_();
                     ThrownGodTrident var8 = new ThrownGodTrident(var4, var6, var7);
                     var8.m_6034_(var2.m_20185_(), var2.m_20186_(), var2.m_20189_());
                     var8.m_20256_(var2.m_20184_());
                     var8.m_20242_(true);
                     var8.f_36705_ = var2.f_36705_;
                     var4.m_7967_(var8);
                  }
               }
            }
         }
      }
   }

   private static boolean isGodTrident(ItemStack var0) {
      return var0 != null && !var0.m_41619_() && var0.m_41720_() instanceof GodTridentItem;
   }

   private static ItemStack readThrownStack(ThrownTrident var0) {
      ItemStack var1 = tryPickupItem(var0);
      if (isGodTrident(var1)) {
         return var1;
      } else {
         if (var0.m_19749_() instanceof LivingEntity var3 && isGodTrident(var3.m_21205_())) {
            return var3.m_21205_();
         }

         return var1;
      }
   }

   private static ItemStack tryPickupItem(ThrownTrident var0) {
      try {
         Method var6 = ThrownTrident.class.getDeclaredMethod("m_7941_");
         var6.setAccessible(true);
         return var6.invoke(var0) instanceof ItemStack var8 ? var8 : ItemStack.f_41583_;
      } catch (ReflectiveOperationException var5) {
         try {
            Method var1 = ThrownTrident.class.getMethod("m_7941_");
            return var1.invoke(var0) instanceof ItemStack var3 ? var3 : ItemStack.f_41583_;
         } catch (ReflectiveOperationException var4) {
            return ItemStack.f_41583_;
         }
      }
   }
}
