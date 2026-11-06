package com.godtrident;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class BookGivenData extends SavedData {
   private boolean given;

   public static BookGivenData load(CompoundTag var0) {
      BookGivenData var1 = new BookGivenData();
      var1.given = var0.m_128471_("Given");
      return var1;
   }

   public CompoundTag m_7176_(CompoundTag var1) {
      var1.m_128379_("Given", this.given);
      return var1;
   }

   public boolean isGiven() {
      return this.given;
   }

   public void markGiven() {
      this.given = true;
      this.m_77762_();
   }
}
