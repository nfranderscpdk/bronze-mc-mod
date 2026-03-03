package dk.codingpirates.randers.bronze;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.item.ItemGroups;

import java.util.function.Function;

public class Bronze implements ModInitializer {
    public static final String MOD_ID = "bronze";

    public static final Block TIN_ORE = registerBlockWithItem(
            "tin_ore",
            TinOre::new,
            AbstractBlock.Settings.create()
                    .mapColor(Blocks.IRON_ORE.getDefaultMapColor())
                    .instrument(Blocks.IRON_ORE.getDefaultState().getInstrument())
                    .strength(3.0F, 3.0F)
    );

    @Override
    public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
                .register(entries -> entries.add(TIN_ORE));
    }

    private static Block registerBlockWithItem(
            String name,
            Function<AbstractBlock.Settings, Block> factory,
            AbstractBlock.Settings settings
    ) {
        Identifier id = Identifier.of(MOD_ID, name);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);
        Block block = factory.apply(settings.registryKey(key));
        Registry.register(Registries.BLOCK, id, block);
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings().registryKey(itemKey)));
        return block;
    }
}
