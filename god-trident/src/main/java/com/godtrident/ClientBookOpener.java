package com.godtrident;

import net.minecraft.client.Minecraft;

public final class ClientBookOpener {
   private ClientBookOpener() {
   }

   public static void open() {
      Minecraft.m_91087_().m_91152_(new RecipeBookScreen());
   }
}
