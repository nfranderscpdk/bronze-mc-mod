# Fuzed Copper & Tin Crafting Recipe Implementation Plan

## Overview
This guide outlines the steps to add a crafting recipe for a Fuzed Copper & Tin using 7 copper ingots arranged in a semi-circle pattern and 1 tin bar to complete the circle. The result is a new Fuzed Copper & Tin item.

## Steps

### 1. Register the Fuzed Copper & Tin Item
- Add the `FUZED_COPPER_TIN` field to the `Bronze.java` class.
- Register it using the `registerItem` method.
- Add it to the appropriate item groups (e.g., INGREDIENTS).

### 2. Create Item Texture
- Create `fuzed_copper_tin.png` in `src/main/resources/assets/bronze/textures/item/`.
- The texture should be a 16x16 PNG image representing the fuzed copper & tin (similar to other textures).

### 3. Create Item Model
- Create `fuzed_copper_tin.json` in `src/main/resources/assets/bronze/models/item/`.
- Use the standard generated item model template, referencing the texture.

### 4. Add Language Translation
- Add the translation key `"item.bronze.fuzed_copper_tin": "Fuzed Copper & Tin"` to `en_us.json`.

### 5. Create Crafting Recipe
- Create `fuzed_copper_tin1.json` in `src/main/resources/data/bronze/recipe/`.
- Define a shaped crafting recipe with the pattern:
  ```
  CCC
  C C
  CTC
  ```
  Where:
  - C = minecraft:copper_ingot
  - T = bronze:tin_bar
- Set the result to 1 bronze:fuzed_copper_tin.

Now repeat this 3 more times, where you rotate the pattern 90 degrees each time, and name the files 
`fuzed_copper_tin2.json`, `fuzed_copper_tin3.json`, and `fuzed_copper_tin4.json`.

### 6. Test the Implementation
- Build the mod and test the recipe in-game to ensure it works correctly.</content>
<parameter name="filePath">/home/stsmr/mcmods/bronze/guides/bronze-ingot-recipe.md
