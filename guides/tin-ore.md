# Tin Ore Implementation Guide (Updated)

## What Changed From The Original Plan
- Minecraft/Fabric 1.21+ requires registry keys on both block and item settings.
- Tin Ore is added to creative inventory explicitly via Fabric item group events.
- Mining behavior is set through `AbstractBlock.Settings` during registration.
- Tool tags are in `data/minecraft/tags/block/...` (not under `data/bronze`).

## Current Implementation (Reference)

### 1) Register block + block item in `Bronze.java`
- File: `src/main/java/dk/codingpirates/randers/bronze/Bronze.java`
- Keep registration centralized in the mod initializer.
- Use `Identifier.of(MOD_ID, "tin_ore")`.
- Create and assign:
  - `RegistryKey<Block>` for block settings (`settings.registryKey(blockKey)`)
  - `RegistryKey<Item>` for item settings (`new Item.Settings().registryKey(itemKey)`)
- Register both in `Registries.BLOCK` and `Registries.ITEM`.

### 2) Set mining behavior in registration settings
- Build `TinOre` from configured settings, not by mutating inside `TinOre`.
- Use:
  - `AbstractBlock.Settings.copy(Blocks.IRON_ORE)`
  - `.strength(3.0F, 3.0F)`
- `TinOre` class should remain a thin wrapper over `Block`.

### 3) Add Tin Ore to creative inventory
- In `onInitialize()`, add Tin Ore using:
  - `ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)`
  - `entries.add(TIN_ORE)`

### 4) Add assets
Under `src/main/resources/assets/bronze`:
- `blockstates/tin_ore.json`
- `models/block/tin_ore.json`
- `models/item/tin_ore.json`
- `textures/block/tin_ore.png`
- `lang/en_us.json`

Current item model format:
```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "bronze:block/tin_ore"
  }
}
```

### 5) Add loot table + mining tags
Notice the confusion round plural vs singular in loot table vs tags and block vs blocks:

- Loot table:
  - `src/main/resources/data/bronze/loot_table/blocks/tin_ore.json`
- Mining tags:
  - `src/main/resources/data/minecraft/tags/block/mineable/pickaxe.json`
  - `src/main/resources/data/minecraft/tags/block/needs_stone_tool.json`

### 6) Verify behavior
- Check block appears in creative inventory tab.
- Confirm correct item icon/model in inventory.
- Confirm mining speed/tool requirement behaves like an ore block.
- Confirm block drops itself (or desired loot outcome).

## Optional Next Step
- Add overworld worldgen later (configured + placed feature + biome placement), once base block behavior is finalized.
