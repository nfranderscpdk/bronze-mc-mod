# Tin Bar Implementation Guide (Smelting Recipe)

## Scope
Add a `tin_bar` item that:
- appears in creative inventory,
- is obtained by smelting `raw_tin` in a furnace (or other smelting devices),
- has its own inventory texture/model/lang entry.

## Implementation Order
1. Register `tin_bar` in code.
2. Add it to creative inventory tabs.
3. Create smelting recipe files.
4. Add item assets (model + texture) and localization.
5. Verify in-game behavior.

## Files To Create/Update

### Code
- `src/main/java/dk/codingpirates/randers/bronze/Bronze.java`
  - add `TIN_BAR` item registration with item registry key,
  - add creative tab entries for `TIN_BAR` (e.g., `ItemGroups.INGREDIENTS` for visibility).

### Recipes
- `src/main/resources/data/bronze/recipe/smelting/tin_bar_from_raw_tin.json`
  - smelting recipe: `raw_tin` → `tin_bar` (0.7 XP, 200 ticks / 10 seconds).
- `src/main/resources/data/bronze/recipe/smelting/tin_bar_from_raw_tin_blasting.json`
  - blasting recipe: `raw_tin` → `tin_bar` (0.7 XP, 100 ticks / 5 seconds).
- `src/main/resources/data/bronze/recipe/smelting/tin_bar_from_raw_tin_smoking.json`
  - smoking recipe: `raw_tin` → `tin_bar` (0.7 XP, 100 ticks / 5 seconds).

### Assets
- `src/main/resources/assets/bronze/models/item/tin_bar.json`
- `src/main/resources/assets/bronze/textures/item/tin_bar.png`
- `src/main/resources/assets/bronze/lang/en_us.json` (update with Tin Bar entry)

## Example Smelting Recipe Format (Minecraft 1.21+)

**Actual working format used in this mod:**
```json
{
  "type": "minecraft:smelting",
  "category": "misc",
  "ingredient": "bronze:raw_tin",
  "result": { "id": "bronze:tin_bar" },
  "experience": 0.7,
  "cookingtime": 200
}
```

**Field Explanations:**
- `type`: Recipe type (`minecraft:smelting`, `minecraft:blasting`, or `minecraft:smoking`)
- `category`: Recipe category for recipe book organization (e.g., "misc", "food", "blocks")
- `ingredient`: Item to smelt - can be string or object with `"item"` key
- `result`: Output item as object with `"id"` key (can also include `"count"` if > 1)
- `experience`: XP awarded per smelted item (float value)
- `cookingtime`: Ticks to complete smelting
  - Furnace: 200 ticks = 10 seconds
  - Blast Furnace: 100 ticks = 5 seconds (2x faster)
  - Smoker: 100 ticks = 5 seconds (2x faster)

**Alternative verbose format (also valid):**
```json
{
  "type": "minecraft:smelting",
  "category": "misc",
  "ingredient": {
    "item": "bronze:raw_tin"
  },
  "result": {
    "id": "bronze:tin_bar",
    "count": 1
  },
  "experience": 0.7,
  "cookingtime": 200
}
```

## Important Path Notes

⚠️ **CRITICAL: Use SINGULAR directory names!**

| Resource Type | ❌ WRONG (Plural) | ✅ CORRECT (Singular) |
|---------------|-------------------|----------------------|
| Recipes | `data/bronze/recipes/` | `data/bronze/recipe/` |
| Loot Tables | `data/bronze/loot_tables/` | `data/bronze/loot_table/` |
| Block Tags | `data/minecraft/tags/blocks/` | `data/minecraft/tags/block/` |

**Correct Paths:**
- **Recipes:** `data/bronze/recipe/smelting/` (singular `recipe`, NOT `recipes`)
- **Loot Tables:** `data/bronze/loot_table/blocks/` (singular `loot_table`)
- **Tags:** `data/minecraft/tags/block/` (singular `block`)
- **Assets:** `assets/bronze/...` (never has `minecraft` prefix for mod assets)
- **Models:** `assets/bronze/models/item/` (singular `item`, not `items`)
- **Textures:** `assets/bronze/textures/item/`

**Full Directory Structure:**
```
src/main/resources/
├── data/
│   └── bronze/
│       ├── loot_table/         ← singular!
│       │   └── blocks/
│       └── recipe/             ← singular!
│           └── smelting/
└── assets/
    └── bronze/
        ├── models/
        │   └── item/           ← singular!
        └── textures/
            └── item/           ← singular!
```

## Verification Checklist
- `/give @p bronze:tin_bar` works and item appears.
- `Tin Bar` appears in creative inventory (Ingredients tab).
- Can place `raw_tin` in furnace and smelt to get `tin_bar`.
- Furnace, Blast Furnace, and Smoker all accept the recipe.
- `tin_bar` texture renders in inventory, hand, and as an entity item.
- XP is awarded on smelting completion.

## Optional Extensions
- Add crafting recipe (e.g., 9 tin bars → tin block later)
- Add tool crafting recipes using tin bar as material

