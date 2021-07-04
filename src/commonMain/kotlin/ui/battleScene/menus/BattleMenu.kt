package ui.battleScene.menus

interface BattleMenu {
    suspend fun draw()
    suspend fun onAccept()
    suspend fun onBack()
}