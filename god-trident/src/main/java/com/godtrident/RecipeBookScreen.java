package com.godtrident;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class RecipeBookScreen extends Screen {
   private final List<RecipeBookScreen.Page> pages = new ArrayList<>();
   private int pageIndex;
   private Button prevButton;
   private Button nextButton;

   public RecipeBookScreen() {
      super(Component.m_237113_("神之说明书"));
      this.buildPages();
   }

   private void buildPages() {
      this.pages.add(new RecipeBookScreen.TextPage("神之说明书\n\n记载三把神创三叉戟\n与「生命之心」的\n合成与用法。\n\n第 2 页为合成材料一览\n请翻页阅读。"));
      this.pages.add(new RecipeBookScreen.TextPage("【合成材料一览】\n\n神创-三叉戟-神\n金锭×3 下界合金锭×3 三叉戟\n\n神创-三叉戟-魔\n神 + 烈焰棒×2 + 岩浆膏\n\n神创-三叉戟-仙\n神 + 下界之星 + 紫水晶×2"));
      this.pages.add(new RecipeBookScreen.ImagePage(new ResourceLocation("god_trident", "textures/gui/recipe_god_trident.png"), "神创-三叉戟-神 · 合成表"));
      this.pages.add(new RecipeBookScreen.TextPage("神创-三叉戟-神\n\n【用法】\n· 右键蓄力投掷，沿准星飞行\n· 命中实体造成 25 点伤害\n· 不再引发爆炸\n· 自带忠诚，自动飞回\n· 近战 / 投掷伤害：25\n· 可交给女仆作为远程武器"));
      this.pages.add(new RecipeBookScreen.ImagePage(new ResourceLocation("god_trident", "textures/gui/recipe_god_trident_demon.png"), "神创-三叉戟-魔 · 合成表"));
      this.pages.add(new RecipeBookScreen.TextPage("神创-三叉戟-魔\n\n【用法】\n· 暗红色，散发酒红色粒子\n· 命中生物造成 79 点伤害\n· 附带点燃，类似火焰附加\n· 自带忠诚，自动飞回\n· 合成：神+烈焰棒×2+岩浆膏\n· 可交给女仆作为远程武器"));
      this.pages.add(new RecipeBookScreen.ImagePage(new ResourceLocation("god_trident", "textures/gui/recipe_god_trident_immortal.png"), "神创-三叉戟-仙 · 合成表"));
      this.pages.add(new RecipeBookScreen.TextPage("神创-三叉戟-仙\n\n【用法】\n· 白色与金色交织，白金粒子\n· 命中生物造成 100 点伤害\n· 在命中落点召唤闪电\n· 自带忠诚，自动飞回\n· 合成：神+下界之星+紫水晶×2\n· 可交给女仆作为远程武器"));
      this.pages.add(new RecipeBookScreen.ImagePage(new ResourceLocation("god_trident", "textures/gui/recipe_life_heart.png"), "生命之心 · 合成表"));
      this.pages.add(new RecipeBookScreen.TextPage("生命之心\n\n【用法】\n· 放入女仆饰品栏\n· 每秒回复 20 颗心\n· 仅对车万女仆生效"));
   }

   protected void m_7856_() {
      int var1 = this.f_96543_ / 2;
      int var2 = this.f_96544_ / 2;
      this.prevButton = (Button)this.m_142416_(
         Button.m_253074_(Component.m_237113_("上一页"), var1x -> this.flipPage(-1)).m_252987_(var1 - 110, var2 + 92, 90, 20).m_253136_()
      );
      this.nextButton = (Button)this.m_142416_(
         Button.m_253074_(Component.m_237113_("下一页"), var1x -> this.flipPage(1)).m_252987_(var1 + 20, var2 + 92, 90, 20).m_253136_()
      );
      this.updateButtons();
   }

   private void flipPage(int var1) {
      int var2 = this.pageIndex + var1;
      if (var2 >= 0 && var2 < this.pages.size()) {
         this.pageIndex = var2;
         this.updateButtons();
      }
   }

   private void updateButtons() {
      if (this.prevButton != null) {
         this.prevButton.f_93623_ = this.pageIndex > 0;
      }

      if (this.nextButton != null) {
         this.nextButton.f_93623_ = this.pageIndex < this.pages.size() - 1;
      }
   }

   public void m_88315_(GuiGraphics var1, int var2, int var3, float var4) {
      this.m_280273_(var1);
      int var5 = this.f_96543_ / 2;
      int var6 = this.f_96544_ / 2;
      short var7 = 220;
      short var8 = 200;
      int var9 = var5 - var7 / 2;
      int var10 = var6 - var8 / 2 - 8;
      var1.m_280509_(var9, var10, var9 + var7, var10 + var8, -990528);
      var1.m_280509_(var9, var10, var9 + var7, var10 + 2, -9741762);
      var1.m_280509_(var9, var10 + var8 - 2, var9 + var7, var10 + var8, -9741762);
      var1.m_280509_(var9, var10, var9 + 2, var10 + var8, -9741762);
      var1.m_280509_(var9 + var7 - 2, var10, var9 + var7, var10 + var8, -9741762);
      String var11 = "神之说明书";
      var1.m_280488_(this.f_96547_, var11, var5 - this.f_96547_.m_92895_(var11) / 2, var10 + 10, -12898530);
      RecipeBookScreen.Page var12 = this.pages.get(this.pageIndex);
      if (var12 instanceof RecipeBookScreen.ImagePage var13) {
         short var15 = 160;
         short var16 = 160;
         int var17 = var5 - var15 / 2;
         int var18 = var10 + 30;
         var1.m_280411_(var13.image, var17, var18, var15, var16, 0.0F, 0.0F, 256, 256, 256, 256);
         var1.m_280488_(this.f_96547_, var13.caption, var5 - this.f_96547_.m_92895_(var13.caption) / 2, var18 + var16 + 10, -12898530);
      } else if (var12 instanceof RecipeBookScreen.TextPage var14) {
         String[] var22 = var14.text.split("\n");
         int var23 = var10 + 34;

         for (String var20 : var22) {
            var1.m_280488_(this.f_96547_, var20, var5 - this.f_96547_.m_92895_(var20) / 2, var23, -12898530);
            var23 += 13;
         }
      }

      String var21 = this.pageIndex + 1 + " / " + this.pages.size();
      var1.m_280488_(this.f_96547_, var21, var5 - this.f_96547_.m_92895_(var21) / 2, var10 + var8 - 16, -9741762);
      super.m_88315_(var1, var2, var3, var4);
   }

   public boolean m_7933_(int var1, int var2, int var3) {
      if (var1 == 266) {
         this.flipPage(-1);
         return true;
      } else if (var1 == 267) {
         this.flipPage(1);
         return true;
      } else {
         return super.m_7933_(var1, var2, var3);
      }
   }

   private static final class ImagePage extends RecipeBookScreen.Page {
      final ResourceLocation image;
      final String caption;

      ImagePage(ResourceLocation var1, String var2) {
         this.image = var1;
         this.caption = var2;
      }
   }

   private abstract static class Page {
   }

   private static final class TextPage extends RecipeBookScreen.Page {
      final String text;

      TextPage(String var1) {
         this.text = var1;
      }
   }
}
