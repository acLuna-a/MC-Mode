package com.godtrident;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod("god_trident")
public class GodTrident {
   public static final String MOD_ID = "god_trident";
   private static final ResourceKey<CreativeModeTab> COMBAT_TAB = ResourceKey.m_135785_(Registries.f_279569_, new ResourceLocation("minecraft", "combat"));
   private static final ResourceKey<CreativeModeTab> TOOLS_TAB = ResourceKey.m_135785_(
      Registries.f_279569_, new ResourceLocation("minecraft", "tools_and_utilities")
   );
   public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "god_trident");
   public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "god_trident");

   // variant: 0 = 神(25) / 1 = 魔(79, 点燃) / 2 = 仙(100, 引雷)
   public static final RegistryObject<Item> GOD_TRIDENT = ITEMS.register(
      "god_trident", () -> new GodTridentItem(tridentProps(), 25.0, 0)
   );
   public static final RegistryObject<Item> GOD_TRIDENT_DEMON = ITEMS.register(
      "god_trident_demon", () -> new GodTridentItem(tridentProps(), 79.0, 1)
   );
   public static final RegistryObject<Item> GOD_TRIDENT_IMMORTAL = ITEMS.register(
      "god_trident_immortal", () -> new GodTridentItem(tridentProps(), 100.0, 2)
   );
   public static final RegistryObject<Item> LIFE_HEART = ITEMS.register(
      "life_heart", () -> new LifeHeartItem(new Properties().m_41487_(1).m_41497_(Rarity.EPIC))
   );
   public static final RegistryObject<Item> FLYING_ITEM_BOOK = ITEMS.register(
      "flying_item_book", () -> new FlyingItemBook(new Properties().m_41487_(1).m_41497_(Rarity.UNCOMMON))
   );
   public static final RegistryObject<EntityType<ThrownGodTrident>> GOD_TRIDENT_ENTITY = ENTITY_TYPES.register(
      "god_trident",
      () -> Builder.<ThrownGodTrident>m_20704_(ThrownGodTrident::new, MobCategory.MISC)
         .m_20699_(0.5F, 0.5F).m_20702_(10).m_20717_(10).m_20712_("god_trident")
   );

   private static Properties tridentProps() {
      return new Properties().m_41487_(1).m_41499_(2500).m_41497_(Rarity.EPIC);
   }

   public GodTrident(FMLJavaModLoadingContext var1) {
      ITEMS.register(var1.getModEventBus());
      ENTITY_TYPES.register(var1.getModEventBus());
      var1.getModEventBus().addListener(GodTrident::onCreativeTab);
      var1.getModEventBus().addListener(GodTrident::onLoadComplete);
      MinecraftForge.EVENT_BUS.register(GodTrident.class);
      LifeHeartTlm.register();
   }

   private static void onLoadComplete(FMLLoadCompleteEvent var0) {
      var0.enqueueWork(LifeHeartTlm::mergeIntoBaubleMap);
   }

   @SubscribeEvent
   public static void onPlayerLogin(PlayerLoggedInEvent var0) {
      LifeHeartTlm.mergeIntoBaubleMap();
      FlyingItemBook.giveToPlayer(var0.getEntity());
   }

   @SubscribeEvent
   public static void onCreativeTab(BuildCreativeModeTabContentsEvent var0) {
      if (var0.getTabKey().equals(COMBAT_TAB)) {
         var0.accept(GOD_TRIDENT);
         var0.accept(GOD_TRIDENT_DEMON);
         var0.accept(GOD_TRIDENT_IMMORTAL);
      }

      if (var0.getTabKey().equals(TOOLS_TAB)) {
         var0.accept(LIFE_HEART);
         var0.accept(FLYING_ITEM_BOOK);
      }
   }
}
