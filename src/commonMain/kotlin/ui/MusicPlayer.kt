package ui

import Game
import com.soywiz.klock.milliseconds
import com.soywiz.korau.sound.Sound
import com.soywiz.korio.async.delay
import com.soywiz.korio.async.launchImmediately
import kotlin.coroutines.CoroutineContext

suspend fun play(ctx: CoroutineContext, musicName: String?, delay: Int = 500) {
    if (musicName != null) {
        val music = Resources.getMusic(musicName)
        play(ctx, music, delay)
    }
}

suspend fun play(ctx: CoroutineContext, music: Sound?, delay: Int = 500) {
    if (!Game.muted && music != null) {
        launchImmediately(ctx) {
            delay(delay.milliseconds)
            music.playForever()
        }
    }
}