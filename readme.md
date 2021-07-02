# Mechamon

Collect parts to battle bots

[![](example.png)](https://austinkucera.com/mechamon)

## Running

```
runJvm
runJs
jsBrowserDistribution
```

## Data

Doors are objects in the tileset and include `level` `x` and `y` as custom properties.

The first tile layer of a `tmx` has a custom property `music` (eg `bgm`). `music/bgm` is used on the tiled views and `music/battle/bgm` is used in battles.

Each terrain specifies a battle name (eg `Dirt`), which links to `battleBackgrounds/Dirt.png` that is loaded in a battle

## Pushing to web

```
aws s3 sync build/distributions/ s3://austinkucera.com/mechamon/ --delete
```

## Credits

Art
- Font from [Zelda Like Tilesets and Sprites](https://opengameart.org/content/zelda-like-tilesets-and-sprites)
- Tileset from [Isaiah658's Pixel Pack](https://opengameart.org/content/isaiah658s-pixel-pack-2)

Music
- [Role Music](https://freemusicarchive.org/music/Rolemusic/The_Pirate_And_The_Dancer)



## Thoughts

add range to parts

target square outside of battle

target part inside battle