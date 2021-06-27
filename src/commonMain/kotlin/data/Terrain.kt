package data

import core.Terrain

val terrains = listOf(
    Terrain("Grass", "A park of green and well kept paths.", flat = 8, water = 2),
    Terrain("Sand", "Flat sands", flat = 10),
    Terrain("Wall", "An unpassable wall."),
    Terrain("Door", "An entry or exit.", 5, 5, 5),
    Terrain("Dirt", "Hard packed dirt.", flat = 10),
    Terrain("Bush", "Weeds and brackets all intertwined", bumpy = 4, tightQuarters = 6),
    Terrain(
        "Forest",
        "Gnarled roots coalesce into shooting trunks and splay into woven branches.",
        flat = 3,
        bumpy = 3,
        tightQuarters = 4
    ),
    Terrain("Boulder", "A large, impassable rock"),
).associateBy { it.name }