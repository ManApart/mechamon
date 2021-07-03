package ui.battleScene

import WINDOW_SIZE
import com.soywiz.klock.TimeSpan
import com.soywiz.korge.scene.AlphaTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korio.async.launchImmediately
import ui.Resources
import ui.battleScene.menus.TopLevel
import ui.play
import ui.tiledScene.Direction
import ui.tiledScene.PlayerCharacter
import ui.tiledScene.TiledScene
import kotlin.coroutines.CoroutineContext
import kotlin.properties.Delegates

class BattleScene(val config: BattleConfig) : Scene() {
    var screen: Container by Delegates.notNull()
    var context: CoroutineContext by Delegates.notNull()

    override suspend fun Container.sceneInit() {
        config.battle.tick()
        val backgroundPic = Resources.getImage("battleBackgrounds/${config.battle.terrain.battleName}.png")
        val background = Image(backgroundPic, 0.0, 0.0, smoothing = false)
        play(coroutineContext, "music/battle/${config.musicName}.mp3")
        val battle = config.battle
        val bot = battle.botA

        val playerCombatant = Combatant(bot, Direction.RIGHT)
        val enemyCombatant = Combatant(bot, Direction.LEFT)


        fixedSizeContainer(WINDOW_SIZE, WINDOW_SIZE, clip = false) {
            screen = scaleView(WINDOW_SIZE, WINDOW_SIZE, 2.0, false)
            context = coroutineContext
        }

        val options = TopLevel(this@BattleScene, background, playerCombatant, enemyCombatant)
        options.reDraw()
    }


    fun endBattle() {
        launchImmediately {
            sceneContainer.changeTo<TiledScene>(
                config.level,
                PlayerCharacter(config.battle.botA),
                config.tile.x,
                config.tile.y,
                transition = AlphaTransition,
                time = TimeSpan(500.0)
            )
        }
    }
}