package com.godtrident;

import com.github.tartaricacid.touhoulittlemaid.TouhouLittleMaid;
import com.github.tartaricacid.touhoulittlemaid.api.ILittleMaid;
import com.github.tartaricacid.touhoulittlemaid.item.bauble.BaubleManager;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class LifeHeartTlm implements ILittleMaid {
   private static boolean extensionAdded;

   private LifeHeartTlm() {
   }

   public static void register() {
      addExtension();
   }

   private static void addExtension() {
      if (!extensionAdded) {
         try {
            TouhouLittleMaid.EXTENSIONS.add(new LifeHeartTlm());
            extensionAdded = true;
         } catch (Throwable var1) {
         }
      }
   }

   public static void mergeIntoBaubleMap() {
      try {
         if (GodTrident.LIFE_HEART == null) {
            return;
         }

         Object var0 = GodTrident.LIFE_HEART.get();
         LifeHeartBauble var1 = new LifeHeartBauble();
         Field var2 = BaubleManager.class.getDeclaredField("BAUBLES");
         var2.setAccessible(true);
         Object var3 = var2.get(null);
         if (!(var3 instanceof Map)) {
            return;
         }

         HashMap var4 = new HashMap((Map)var3);
         ResourceLocation var5 = ForgeRegistries.ITEMS.getKey((Item)var0);
         RegistryObject var6 = RegistryObject.create(var5, ForgeRegistries.ITEMS);
         var4.put(var6, var1);
         var4.put(GodTrident.LIFE_HEART, var1);
         var2.set(null, var4);
      } catch (Throwable var7) {
      }
   }

   public void bindMaidBauble(BaubleManager var1) {
      try {
         var1.bind((Item)GodTrident.LIFE_HEART.get(), new LifeHeartBauble());
         var1.bind(GodTrident.LIFE_HEART, new LifeHeartBauble());
      } catch (Throwable var3) {
      }
   }
}
