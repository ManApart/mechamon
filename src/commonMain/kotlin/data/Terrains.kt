package data

import core.Terrain

val terrains = listOf(
    Terrain("Grass", "Grass", "A park of green and well kept paths.", flat = 8, water = 2),
    Terrain("Sand", "Sand", "Flat sands", flat = 10),
    Terrain("Wall", "None","An unpassable wall."),
    Terrain("Door", "None","An entry or exit.", 5, 5, 5),
    Terrain("Dirt", "Dirt","Hard packed dirt.", flat = 10),
    Terrain("Bush", "Bush","Weeds and brackets all intertwined", bumpy = 4, tightQuarters = 6),
    Terrain(
        "Forest",
        "Forest",
        "Gnarled roots coalesce into shooting trunks and splay into woven branches.",
        flat = 3,
        bumpy = 3,
        tightQuarters = 4
    ),
    Terrain("Boulder", "None", "A large, impassable rock."),
    Terrain("Water", "None", "Deep water."),
).associateBy { it.name }