# Tin Ore Implementation Plan

## Checklist
- [ ] Confirm where blocks/items are registered in the mod entrypoint.
- [ ] Register the `TinOre` block and its `BlockItem`.
- [ ] Add assets: blockstate, models, textures, and lang entry.
- [ ] Add data: loot table and tags.
- [ ] Decide on worldgen and add configured/placed features if needed.
- [ ] Validate via data generation and a quick in-game check.

## Steps
1. Identify the registration pattern used in the project (entrypoint or helper class).
2. Register the `TinOre` block and add it to any creative tab if used.
3. Register a `BlockItem` for `TinOre` with matching identifier.
4. Create assets under `src/main/resources/assets/bronze`:
   - `blockstates/tin_ore.json`
   - `models/block/tin_ore.json`
   - `models/item/tin_ore.json`
   - `textures/block/tin_ore.png`
   - `lang/en_us.json` entry
5. Add data under `src/main/resources/data/bronze`:
   - `loot_tables/blocks/tin_ore.json`
   - `tags/blocks/pickaxe_mineable.json` (and `needs_stone_tool.json` if required)
6. If worldgen is needed, add configured/placed features and biome modifiers or Fabric API worldgen setup.
7. Run data generation (if used) and verify the block appears in-game with correct drops.

## Notes
- If you use data generation, add `TinOre` to the generator setup (block state, model, loot).
- Keep identifiers consistent (e.g., `bronze:tin_ore`).

