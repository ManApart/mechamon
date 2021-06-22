package data

import Terrain

val terrain = listOf(
    Terrain("Grassland", "A park of green and well kept paths.", flat = 8, water = 2),
    Terrain("Forest", "Gnarled roots coalesce into shooting trunks and splay into woven branches.", flat = 3, bumpy = 3, tightQuarters = 4),
).associateBy { it.name }