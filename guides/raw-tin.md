# Raw Tin Item Implementation Guide

## Scope
Add a `raw_tin` item that:
- appears in creative inventory,
- is dropped from `tin_ore` when mined with the correct tool,
- has its own inventory texture/model/lang entry.

## Implementation Order
1. Register `raw_tin` in code.
2. Add it to creative inventory tabs.
3. Update Tin Ore loot table to drop `raw_tin`.
4. Add item assets (model + texture) and localization.
5. Verify in-game behavior.

## Files To Update

### Code
- `src/main/java/dk/codingpirates/randers/bronze/Bronze.java`
  - add `RAW_TIN` item registration with item registry key,
  - add creative tab entries for `RAW_TIN` (e.g., `ItemGroups.INGREDIENTS` and `ItemGroups.SEARCH` for visibility).

### Loot
- `src/main/resources/data/bronze/loot_tables/blocks/tin_ore.json`
  - change drop target from `bronze:tin_ore` to `bronze:raw_tin`,
  - keep only `minecraft:survives_explosion` condition,
  - tool gating is handled by block `.requiresTool()` + tags (below).

### Tool Tags
- `src/main/resources/data/minecraft/tags/block/mineable/pickaxe.json`
  - add `bronze:tin_ore` so it's mineable by pickaxes.
- `src/main/resources/data/minecraft/tags/block/needs_stone_tool.json` (or `needs_diamond_tool.json` etc.)
  - add `bronze:tin_ore` to set the minimum tool tier for drops.

### Assets
- `src/main/resources/assets/bronze/models/item/raw_tin.json`
- `src/main/resources/assets/bronze/textures/item/raw_tin.png`
- `src/main/resources/assets/bronze/lang/en_us.json`

## Example Item Model
```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "bronze:item/raw_tin"
  }
}
```

## Important Path Notes
- **Loot tables:** `data/bronze/loot_tables/` (plural `loot_tables`)
- **Tags:** `data/minecraft/tags/block/` (singular `block`, not `blocks`)
- **Assets:** `assets/bronze/...` (never has `minecraft` prefix)

## Verification Checklist
- `/give @p bronze:raw_tin` works and item appears.
- `Raw Tin` appears in creative inventory (Ingredients or Search tabs).
- Mining `tin_ore` with correct-tier pickaxe drops `raw_tin`.
- Mining without a pickaxe does not drop anything (blocked by `.requiresTool()`).
- `raw_tin` texture renders in inventory, hand, and as an entity item.
