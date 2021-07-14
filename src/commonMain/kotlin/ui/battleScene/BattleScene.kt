package ui.battleScene

import WINDOW_SIZE
import com.soywiz.klock.TimeSpan
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.scene.AlphaTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korio.async.launchImmediately
import ui.Resources
import ui.battleScene.menus.BattleMenu
import ui.battleScene.menus.TopLevel
import ui.play
import ui.tiledScene.Direction
import ui.tiledScene.PlayerCharacter
import ui.tiledScene.TiledScene
import kotlin.properties.Delegates

class BattleScene(val config: BattleConfig) : Scene() {
    var screen: Container by Delegates.notNull()
    var activeMenu: BattleMenu by Delegates.notNull()


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
            screen = fixedSizeContainer(WINDOW_SIZE, WINDOW_SIZE, false) {
                scale = 4.0
            }
        }

        keys {
            up(Key.SPACE) {
                activeMenu.onAccept()
            }
            up(Key.ESCAPE) {
                activeMenu.onBack()
            }
        }

        draw(TopLevel(this@BattleScene, background, playerCombatant, enemyCombatant))

    }

    fun draw(menu: BattleMenu) {
        launchImmediately {
            activeMenu = menu
            menu.draw()
        }
    }

    fun endBattle() {
        launchImmediately {
            sceneContainer.changeTo<TiledScene>(
                config.level,
                PlayerCharacter(config.battle.botA),
                config.tile.point,
                transition = AlphaTransition,
                time = TimeSpan(500.0)
            )
        }
    }
}