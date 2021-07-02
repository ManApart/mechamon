package ui

import Game
import com.soywiz.klock.milliseconds
import com.soywiz.korau.sound.Sound
import com.soywiz.korio.async.delay

suspend fun play(musicName: String?, delay: Int = 500) {
    if (musicName != null) {
        val music = Resources.getMusic(musicName)
        play(music, delay)
    }
}

suspend fun play(music: Sound?, delay: Int = 500) {
    if (!Game.muted && music != null) {
        delay(delay.milliseconds)
        music.playForever()
    }
}