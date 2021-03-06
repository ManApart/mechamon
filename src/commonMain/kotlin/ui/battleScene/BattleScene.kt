package ui.battleScene

import WINDOW_HEIGHT
import WINDOW_WIDTH
import com.soywiz.klock.TimeSpan
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.scene.AlphaTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korio.async.launchImmediately
import ui.Button
import ui.Resources
import ui.battleScene.menus.BattleMenu
import ui.battleScene.menus.TopLevel
import ui.play
import ui.tiledScene.Direction
import ui.tiledScene.PlayerCharacter
import ui.tiledScene.TiledScene

class BattleScene(val config: BattleConfig) : Scene() {
    lateinit var controlsArea: Container
    lateinit var battleArea: Container
    lateinit var infoArea: Container
    private lateinit var activeMenu: BattleMenu
    lateinit var playerCombatant: Combatant
    lateinit var enemyCombatant: Combatant
    lateinit var background: Image

    override suspend fun Container.sceneInit() {
        config.battle.tick()
        val backgroundPic = Resources.getImage("battleBackgrounds/${config.battle.terrain.battleName}.png")
        background = Image(backgroundPic, 0.0, 0.0, smoothing = false)
        play(coroutineContext, "music/battle/${config.musicName}.mp3")
        val battle = config.battle
        val bot = battle.botA

        fixedSizeContainer(WINDOW_WIDTH, WINDOW_HEIGHT, clip = false) {
            solidRect(this.width, this.height, Colors.SLATEGRAY)
            controlsArea = fixedSizeContainer(300, WINDOW_HEIGHT, true)
            battleArea = fixedSizeContainer(160, 160, true) {
                scale = 6.0
                alignLeftToRightOf(controlsArea)
            }
            val remainingWidth = WINDOW_WIDTH - (300 + 160*6)
            infoArea = fixedSizeContainer(remainingWidth, WINDOW_HEIGHT, true) {
                alignLeftToRightOf(battleArea)
            }
        }

        playerCombatant = Combatant(bot, Direction.RIGHT, battleArea.unscaledWidth)
        enemyCombatant = Combatant(bot, Direction.LEFT, battleArea.unscaledWidth)
        playerCombatant.init()
        enemyCombatant.init()

        keys {
            up(Key.SPACE) {
                activeMenu.onAccept()
            }
            up(Key.ESCAPE) {
                activeMenu.onBack()
            }
        }

        draw(TopLevel(this@BattleScene))

    }

    fun drawBase(battleControls: BattleControls) {
        with(controlsArea) {
            removeChildren()
            addChild(battleControls)
            battleControls.init()
//            val backButton = Button(this, 80, 400, "Back") {}
//            addChild(backButton)
        }

        with(battleArea) {
            removeChildren()
            addChild(background)
            addChild(playerCombatant)
            addChild(enemyCombatant)
            playerCombatant.redraw()
            enemyCombatant.redraw()
        }

        with(infoArea){
            removeChildren()
        }
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