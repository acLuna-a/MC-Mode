package com.godtrident;

import com.github.tartaricacid.touhoulittlemaid.api.bauble.IMaidBauble;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import net.minecraft.world.item.ItemStack;

public class LifeHeartBauble implements IMaidBauble {
   private static final float HEAL_PER_TICK = 2.0F;

   public void onTick(EntityMaid var1, ItemStack var2) {
      if (!var1.m_9236_().f_46443_) {
         var1.m_5634_(2.0F);
      }
   }
}
