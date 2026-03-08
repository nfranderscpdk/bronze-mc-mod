# Bronze Bar Implementation Guide (Smelting & Blasting Recipe)

## Scope
Add a `bronze_bar` item that:
- appears in creative inventory (INGREDIENTS tab),
- is obtained by blasting `fuzed_copper_tin` in a blasting furnace only (not craftable in regular furnace or smoker),
- has its own inventory texture/model/lang entry.

## Overview
The Bronze Bar is created by smelting the Fuzed Copper & Tin item in a blasting furnace. 
This combines copper and tin ingots into a single bronze bar, which can become a key crafting ingredient for 
advanced tools and items.

## Implementation Order
1. Register `bronze_bar` in code.
2. Add it to creative inventory tabs.
3. Create smelting recipe files (smelting, blasting, smoking).
4. Add item assets (model + texture) and localization.
5. Verify in-game behavior.

## Files To Create/Update

### Code
- `src/main/java/dk/codingpirates/randers/bronze/Bronze.java`
  - add `BRONZE_BAR` item registration with item registry key,
  - add creative tab entries for `BRONZE_BAR` (e.g., `ItemGroups.INGREDIENTS` for visibility).

### Recipes
- `src/main/resources/data/bronze/recipe/smelting/bronze_bar_from_fuzed_copper_tin.json`
  - smelting recipe: `fuzed_copper_tin` → `bronze_bar` (1000 XP, 200 ticks / 10 seconds).
- `src/main/resources/data/bronze/recipe/smelting/bronze_bar_from_fuzed_copper_tin_blasting.json`
  - blasting recipe: `fuzed_copper_tin` → `bronze_bar` (1000 XP, 100 ticks / 5 seconds).
- `src/main/resources/data/bronze/recipe/smelting/bronze_bar_from_fuzed_copper_tin_smoking.json`
  - smoking recipe: `fuzed_copper_tin` → `bronze_bar` (1000 XP, 100 ticks / 5 seconds).

### Assets
- `src/main/resources/assets/bronze/models/item/bronze_bar.json`
- `src/main/resources/assets/bronze/textures/item/bronze_bar.png`
- `src/main/resources/assets/bronze/lang/en_us.json` (update with Bronze Bar entry)

## Step-by-Step Implementation

### Step 1: Register the Bronze Bar Item
Edit `src/main/java/dk/codingpirates/randers/bronze/Bronze.java`:

```java
public static final Item BRONZE_BAR = registerItem("bronze_bar", BronzeBar::new, new Item.Settings());
```

Add to the `onInitialize()` method to register creative inventory tabs:

```java
ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
    .register(entries -> entries.add(BRONZE_BAR));
ItemGroupEvents.modifyEntriesEvent(ItemGroups.SEARCH)
    .register(entries -> entries.add(BRONZE_BAR));
```

### Step 2: Create the Item Class
Create `src/main/java/dk/codingpirates/randers/bronze/BronzeBar.java`:

```java
package dk.codingpirates.randers.bronze;

import net.minecraft.item.Item;

public class BronzeBar extends Item {
    public BronzeBar(Settings settings) {
        super(settings);
    }
}
```

### Step 3: Create Item Model
Create `src/main/resources/assets/bronze/models/item/bronze_bar.json`:

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "bronze:item/bronze_bar"
  }
}
```

### Step 4: Create Item Texture
Create `src/main/resources/assets/bronze/textures/item/bronze_bar.png`:
- A 16x16 PNG image representing a bronze ingot/bar
- Should have a distinctive appearance from Tin Bar (more golden/brown tones)
- Consider using metallic colors to represent bronze metal

**Texture Guidelines:**
- Use RGB colors that represent bronze (mix of copper and tin colors)
- Suggested color palette: golden-brown (#B87333) with highlights and shadows
- Add shading to make it look 3D and metallic

### Step 5: Add Language Translation
Edit `src/main/resources/assets/bronze/lang/en_us.json`:

Add the entry:
```json
"item.bronze.bronze_bar": "Bronze Bar"
```

### Step 6: Create Smelting Recipes

#### Standard Smelting Recipe
Create `src/main/resources/data/bronze/recipe/smelting/bronze_bar_from_fuzed_copper_tin.json`:

```json
{
  "type": "minecraft:smelting",
  "category": "misc",
  "ingredient": "bronze:fuzed_copper_tin",
  "result": { "id": "bronze:bronze_bar" },
  "experience": 1000,
  "cookingtime": 200
}
```

**Field Explanations:**
- `type`: Recipe type (always `minecraft:smelting` for furnace recipes)
- `category`: Recipe category for display in recipe book (`misc`)
- `ingredient`: Input item (the Fuzed Copper & Tin)
- `result`: Output item with object format `{ "id": "namespace:item_name" }`
- `experience`: XP awarded to player (1000 = 50 levels)
- `cookingtime`: Cooking time in ticks (200 = 10 seconds at normal furnace speed)

#### Blasting Recipe (Blast Furnace)
Create `src/main/resources/data/bronze/recipe/smelting/bronze_bar_from_fuzed_copper_tin_blasting.json`:

```json
{
  "type": "minecraft:blasting",
  "category": "misc",
  "ingredient": "bronze:fuzed_copper_tin",
  "result": { "id": "bronze:bronze_bar" },
  "experience": 1000,
  "cookingtime": 100
}
```

**Field Explanations:**
- `type`: `minecraft:blasting` for blast furnace recipes
- `cookingtime`: Reduced from 200 to 100 (5 seconds) - blast furnaces are faster
- All other fields remain the same as smelting

#### Smoking Recipe (Smoker)
Create `src/main/resources/data/bronze/recipe/smelting/bronze_bar_from_fuzed_copper_tin_smoking.json`:

```json
{
  "type": "minecraft:smoking",
  "category": "misc",
  "ingredient": "bronze:fuzed_copper_tin",
  "result": { "id": "bronze:bronze_bar" },
  "experience": 1000,
  "cookingtime": 100
}
```

**Field Explanations:**
- `type`: `minecraft:smoking` for smoker recipes
- Cooking time is the same as blasting (faster than regular furnace)

### Step 7: Verify in-Game
1. Build the mod using `./gradlew build`
2. Run the game with `./gradlew runClient`
3. Open creative inventory and search for "bronze_bar"
4. The item should appear with the correct texture and name
5. Test the smelting recipes:
   - Place Fuzed Copper & Tin in a furnace, blast furnace, or smoker
   - The item should convert to Bronze Bar with the specified cooking time
   - Confirm you receive the correct XP amount

## Troubleshooting

### Item doesn't appear in inventory
- Verify item registration in `Bronze.java`
- Check item group registration in `onInitialize()`
- Rebuild the project

### Texture isn't showing
- Verify texture file location: `src/main/resources/assets/bronze/textures/item/bronze_bar.png`
- Ensure filename matches the texture path in `bronze_bar.json`
- Rebuild resources

### Smelting recipe doesn't work
- Verify recipe JSON syntax (valid JSON)
- Check ingredient and result IDs match registered items
- Ensure recipe files are in correct location: `src/main/resources/data/bronze/recipe/smelting/`
- Rebuild the project
- Check server logs for recipe parsing errors

### Wrong crafting time
- Verify `cookingtime` value in recipe JSON:
  - 200 ticks = 10 seconds (standard furnace)
  - 100 ticks = 5 seconds (blast furnace/smoker)
  - 1 tick = 0.05 seconds

## Summary
The Bronze Bar serves as a final crafted item combining copper and tin. The three recipe variants allow players to choose between different furnace types based on their needs. The standard furnace is slowest but uses common materials, while the blast furnace and smoker offer faster processing with the same XP rewards.

