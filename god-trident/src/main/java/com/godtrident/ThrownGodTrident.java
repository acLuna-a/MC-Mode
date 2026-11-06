package com.godtrident;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class ThrownGodTrident extends ThrownTrident {
   private static final EntityDataAccessor<Boolean> DATA_RETURNING = SynchedEntityData.m_135353_(ThrownGodTrident.class, EntityDataSerializers.f_135035_);
   // 变体必须走 SynchedEntityData：客户端的投掷物是新建实例，读不到服务端的 thrownStack
   private static final EntityDataAccessor<Integer> DATA_VARIANT = SynchedEntityData.m_135353_(ThrownGodTrident.class, EntityDataSerializers.f_135028_);
   private static final double RETURN_SPEED = 1.35;
   private static final double RETURN_DISTANCE = 1.8;
   private static final DustParticleOptions GOLD_DUST = new DustParticleOptions(new Vector3f(1.0F, 0.82F, 0.12F), 1.15F);
   private static final DustParticleOptions WINE_DUST = new DustParticleOptions(new Vector3f(0.55F, 0.03F, 0.12F), 1.15F);
   private static final DustParticleOptions PLATINUM_DUST = new DustParticleOptions(new Vector3f(0.98F, 0.95F, 0.82F), 1.15F);
   private ItemStack thrownStack = ItemStack.f_41583_;
   private int flyTicks;

   public ThrownGodTrident(EntityType<? extends ThrownGodTrident> var1, Level var2) {
      super(var1, var2);
   }

   public ThrownGodTrident(Level var1, LivingEntity var2, ItemStack var3) {
      super((EntityType)GodTrident.GOD_TRIDENT_ENTITY.get(), var1);
      this.m_5602_(var2);
      Vec3 var4 = var2.m_146892_();
      this.m_6034_(var4.f_82479_, var4.f_82480_ - 0.1, var4.f_82481_);
      this.thrownStack = var3.m_41777_();
      this.setGodVariant(GodTridentItem.variantOf(var3.m_41720_()));
   }

   protected void m_8097_() {
      super.m_8097_();
      this.f_19804_.m_135372_(DATA_RETURNING, false);
      this.f_19804_.m_135372_(DATA_VARIANT, 0);
   }

   public int getGodVariant() {
      return (Integer)this.f_19804_.m_135370_(DATA_VARIANT);
   }

   private void setGodVariant(int var1) {
      this.f_19804_.m_135381_(DATA_VARIANT, var1);
   }

   protected ItemStack m_7941_() {
      return this.thrownStack.m_41777_();
   }

   public void m_7378_(CompoundTag var1) {
      super.m_7378_(var1);
      if (var1.m_128425_("GodTridentStack", 10)) {
         this.thrownStack = ItemStack.m_41712_(var1.m_128469_("GodTridentStack"));
         this.setGodVariant(GodTridentItem.variantOf(this.thrownStack.m_41720_()));
      }
   }

   public void m_7380_(CompoundTag var1) {
      super.m_7380_(var1);
      if (!this.thrownStack.m_41619_()) {
         var1.m_128365_("GodTridentStack", this.thrownStack.m_41739_(new CompoundTag()));
      }
   }

   public boolean m_6469_(DamageSource var1, float var2) {
      return false;
   }

   protected void m_5790_(EntityHitResult var1) {
      Entity var2 = var1.m_82443_();
      Level var3 = this.m_9236_();
      if (!var3.f_46443_) {
         int variant = this.getGodVariant();
         float var4 = variant == 1 ? 79.0F : (variant == 2 ? 100.0F : 25.0F);
         if (var2 instanceof LivingEntity var5) {
            var4 += EnchantmentHelper.m_44833_(this.m_7941_(), var5.m_6336_());
         }

         Entity var10 = this.m_19749_();
         Object var6 = var10 == null ? this : var10;
         boolean var7 = var2.m_6469_(this.m_269291_().m_269525_(this, (Entity)var6), var4);
         if (var7 && var2 instanceof LivingEntity var8) {
            if (var10 instanceof LivingEntity var9) {
               EnchantmentHelper.m_44823_(var8, var9);
               EnchantmentHelper.m_44896_(var9, var8);
            }

            this.m_7761_(var8);
         }

         // 魔：火焰附加式点燃；仙：在命中落点召唤闪电
         if (var2 instanceof LivingEntity) {
            if (variant == 1) {
               var2.m_20254_(5);
            } else if (variant == 2) {
               this.summonLightning(var2, var10);
            }
         }

         this.m_37287_(true);
         this.f_19850_ = true;
      }
   }

   private void summonLightning(Entity var1, Entity var2) {
      Level var3 = this.m_9236_();
      if (!var3.f_46443_) {
         LightningBolt var4 = EntityType.f_20465_.m_20615_(var3);
         if (var4 != null) {
            var4.m_6034_(var1.m_20185_(), var1.m_20186_(), var1.m_20189_());
            if (var2 instanceof ServerPlayer var5) {
               var4.m_20879_(var5);
            }

            var3.m_7967_(var4);
            var3.m_6269_(null, var4, SoundEvents.f_12090_, SoundSource.WEATHER, 2.0F, 1.0F);
         }
      }
   }

   public void m_8119_() {
      Level var1 = this.m_9236_();
      if (var1.f_46443_) {
         this.spawnGodParticles();
      }

      this.flyTicks++;
      Entity var2 = this.m_19749_();
      boolean var3 = this.m_37286_() || this.flyTicks > 60;
      if (!var1.f_46443_ && var2 != null && var2.m_6084_() && var3) {
         this.m_37287_(true);
         this.f_19850_ = true;
         Vec3 var4 = var2.m_146892_().m_82546_(this.m_20182_());
         double var5 = var4.m_82553_();
         if (var5 < 1.8) {
            this.returnToOwner(var2);
         } else {
            Vec3 var7 = var4.m_82541_().m_82542_(1.35, 1.35, 1.35);
            this.m_20256_(var7);
            this.m_20219_(this.m_20182_().m_82549_(var7));
         }
      } else {
         super.m_8119_();
      }
   }

   private void returnToOwner(Entity var1) {
      ItemStack var2 = this.m_7941_();
      if (var1 instanceof Player var3 && !var3.m_150109_().m_36054_(var2)) {
         var3.m_7197_(var2, false, false);
      }

      if (!this.m_9236_().f_46443_) {
         this.m_9236_().m_6269_(null, this, SoundEvents.f_12516_, SoundSource.PLAYERS, 1.0F, 1.0F);
      }

      this.m_146870_();
   }

   private void spawnGodParticles() {
      Level var1 = this.m_9236_();
      if (var1.f_46441_ != null) {
         int variant = this.getGodVariant();
         DustParticleOptions dust = variant == 1 ? WINE_DUST : (variant == 2 ? PLATINUM_DUST : GOLD_DUST);
         ParticleOptions spark = variant == 1 ? ParticleTypes.f_123744_ : ParticleTypes.f_123810_;
         for (int var2 = 0; var2 < 3; var2++) {
            double var3 = (var1.f_46441_.m_188500_() - 0.5) * 0.7;
            double var5 = (var1.f_46441_.m_188500_() - 0.5) * 0.7;
            double var7 = (var1.f_46441_.m_188500_() - 0.5) * 0.7;
            double var9 = (var1.f_46441_.m_188500_() - 0.5) * 0.08;
            double var11 = (var1.f_46441_.m_188500_() - 0.5) * 0.08;
            double var13 = (var1.f_46441_.m_188500_() - 0.5) * 0.08;
            Vec3 var15 = this.m_20182_();
            var1.m_7106_(dust, var15.f_82479_ + var3, var15.f_82480_ + var5, var15.f_82481_ + var7, var9, var11, var13);
            if (var2 == 0) {
               var1.m_7106_(
                  spark, var15.f_82479_ + var3 * 0.5, var15.f_82480_ + var5 * 0.5, var15.f_82481_ + var7 * 0.5, var9, var11 + 0.02, var13
               );
            }
         }
      }
   }

   private boolean m_37286_() {
      return (Boolean)this.f_19804_.m_135370_(DATA_RETURNING);
   }

   private void m_37287_(boolean var1) {
      this.f_19804_.m_135381_(DATA_RETURNING, var1);
   }
}
