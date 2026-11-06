package com.godtrident;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class LifeHeartItem extends Item {
   public LifeHeartItem(Properties var1) {
      super(var1);
   }

   public void m_7373_(ItemStack var1, Level var2, List<Component> var3, TooltipFlag var4) {
      var3.add(Component.m_237113_("§a女仆饰品：佩戴后每秒回复 20 颗心"));
      var3.add(Component.m_237113_("§7仅对车万女仆有效"));
   }
}
