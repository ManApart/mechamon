package ui.battleScene.menus

interface BattleMenu {
    suspend fun reDraw()
    suspend fun onAccept()
    suspend fun onBack()
}