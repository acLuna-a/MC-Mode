package com.godtrident;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import java.util.List;
import java.util.UUID;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class GodTridentItem extends TridentItem {
   private static final UUID BASE_ATTACK_DAMAGE_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
   private static final UUID BASE_ATTACK_SPEED_UUID = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
   private final Multimap<Attribute, AttributeModifier> defaultModifiers;
   private final int variant;

   public GodTridentItem(Properties var1) {
      this(var1, 25.0, 0);
   }

   public GodTridentItem(Properties var1, double damage, int variant) {
      super(var1);
      this.variant = variant;
      Builder var2 = ImmutableMultimap.builder();
      var2.put(Attributes.f_22281_, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", damage, Operation.ADDITION));
      var2.put(Attributes.f_22283_, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -2.9, Operation.ADDITION));
      this.defaultModifiers = var2.build();
   }

   public int getGodVariant() {
      return this.variant;
   }

   public static int variantOf(Item item) {
      return item instanceof GodTridentItem var1 ? var1.getGodVariant() : 0;
   }

   public Multimap<Attribute, AttributeModifier> m_7167_(EquipmentSlot var1) {
      return var1 == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.m_7167_(var1);
   }

   public void m_5551_(ItemStack var1, Level var2, LivingEntity var3, int var4) {
      if (var3 instanceof Player var5) {
         int var6 = this.m_8105_(var1) - var4;
         if (var6 >= 10) {
            if (!var2.f_46443_) {
               var1.m_41622_(1, var5, var2x -> var5.m_21190_(var3.m_7655_()));
               ThrownGodTrident var7 = new ThrownGodTrident(var2, var5, var1);
               var7.m_37251_(var5, var5.m_146909_(), var5.m_146908_(), 0.0F, 2.5F, 1.0F);
               if (var5.m_150110_().f_35937_) {
                  var7.f_36705_ = Pickup.CREATIVE_ONLY;
               }

               var2.m_7967_(var7);
               var2.m_6269_(null, var7, SoundEvents.f_12520_, SoundSource.PLAYERS, 1.0F, 1.0F);
               if (!var5.m_150110_().f_35937_) {
                  var5.m_150109_().m_36057_(var1);
               }

               var5.m_36246_(Stats.f_12982_.m_12902_(this));
            }
         }
      }
   }

   public void m_7373_(ItemStack var1, Level var2, List<Component> var3, TooltipFlag var4) {
      var3.add(Component.m_237113_("§6自带附魔：忠诚"));
      if (this.variant == 1) {
         var3.add(Component.m_237113_("§c命中生物造成 79 点伤害并点燃目标"));
         var3.add(Component.m_237113_("§7近战 / 投掷伤害：79"));
      } else if (this.variant == 2) {
         var3.add(Component.m_237113_("§b命中生物造成 100 点伤害并在落点引雷"));
         var3.add(Component.m_237113_("§7近战 / 投掷伤害：100"));
      } else {
         var3.add(Component.m_237113_("§e命中实体造成 25 点伤害，不再引发爆炸"));
         var3.add(Component.m_237113_("§7近战 / 投掷伤害：25"));
      }
   }
}
