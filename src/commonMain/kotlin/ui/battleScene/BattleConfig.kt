package ui.battleScene

import core.Battle
import ui.tiledScene.Tile

class BattleConfig(
    val battle: Battle,
    val level: String,
    val tile: Tile,
    val musicName: String? = null
)