package ui.battleScene

import WINDOW_SIZE
import com.soywiz.klock.TimeSpan
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.scene.AlphaTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korio.async.launchImmediately
import core.Battle
import core.Bot
import ui.Resources
import ui.play
import ui.tiledScene.Direction
import ui.tiledScene.PlayerCharacter
import ui.tiledScene.TiledScene
import kotlin.properties.Delegates

class BattleScene(private val config: BattleConfig) : Scene() {
    var battleControls: BattleControls by Delegates.notNull()
    var screen: Container by Delegates.notNull()

    override suspend fun Container.sceneInit() {
        val background = Resources.getImage("battleBackgrounds/${config.battle.terrain.battleName}.png")
        play(coroutineContext, "music/battle/${config.musicName}.mp3")
        val battle = config.battle
        val bot = battle.botA

        val playerCombatant = Combatant(bot, Direction.RIGHT)
        val enemyCombatant = Combatant(bot, Direction.LEFT)

        battleControls = getBattleControls(battle, bot)

        keys {
            up(Key.SPACE) {
                battleControls.selectedAction?.action?.invoke()
            }
        }


        fixedSizeContainer(WINDOW_SIZE, WINDOW_SIZE, clip = false) {
            screen = scaleView(WINDOW_SIZE, WINDOW_SIZE, 2.0, false) {
                Image(background, 0.0, 0.0, smoothing = false).addTo(this)
                addChild(playerCombatant)
                playerCombatant.init()

                addChild(enemyCombatant)
                enemyCombatant.init()

                addChild(battleControls)
                battleControls.init()

            }
        }
    }

    private fun getBattleControls(battle: Battle, bot: Bot, highlighted: Direction? = null): BattleControls {
        val up = BattleOption(battle, bot, "Inspect")
        val right = BattleOption(battle, bot, "Action")
        val left = BattleOption(battle, bot, "Flee") { endBattle() }
        val down = BattleOption(battle, bot, "Self")
        return BattleControls(up, down, left, right, ::reDrawOptions, highlighted)
    }

    private fun reDrawOptions(highlighted: Direction? = null) {
        screen.removeChild(battleControls)
        battleControls = getBattleControls(config.battle, config.battle.botA, highlighted)
        screen.addChild(battleControls)
        battleControls.init()
    }

    private fun endBattle() {
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