package com.godtrident;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class FlyingItemBook extends Item {
   public FlyingItemBook(Properties var1) {
      super(var1);
   }

   public InteractionResultHolder<ItemStack> m_7203_(Level var1, Player var2, InteractionHand var3) {
      ItemStack var4 = var2.m_21120_(var3);
      if (var1.f_46443_) {
         DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ClientBookOpener::open);
      }

      return InteractionResultHolder.m_19092_(var4, var1.f_46443_);
   }

   public static void giveToPlayer(Player var0) {
      if (var0 != null && !var0.m_9236_().f_46443_) {
         if (var0 instanceof ServerPlayer) {
            MinecraftServer var1 = var0.m_9236_().m_7654_();
            if (var1 != null) {
               ServerLevel var2 = var1.m_129783_();
               if (var2 != null) {
                  DimensionDataStorage var3 = var2.m_8895_();
                  BookGivenData var4 = (BookGivenData)var3.m_164861_(BookGivenData::load, BookGivenData::new, "god_trident_starter_book");
                  if (!var4.isGiven()) {
                     var4.markGiven();
                     var3.m_164855_("god_trident_starter_book", var4);
                     ItemStack var5 = new ItemStack((ItemLike)GodTrident.FLYING_ITEM_BOOK.get());
                     if (!var0.m_150109_().m_36054_(var5)) {
                        var0.m_7197_(var5, false, false);
                     }

                     var0.m_5661_(Component.m_237113_("§b[神之说明书]§r 已发放，手持右键可阅读"), false);
                  }
               }
            }
         }
      }
   }

   public void m_7373_(ItemStack var1, Level var2, List<Component> var3, TooltipFlag var4) {
      var3.add(Component.m_237113_("§7手持并右键阅读"));
   }
}
