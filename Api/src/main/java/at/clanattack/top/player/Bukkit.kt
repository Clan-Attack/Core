package at.clanattack.top.player

import org.bukkit.Instrument
import org.bukkit.Note
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.Player
import net.kyori.adventure.sound.Sound as PaperSound

fun Player.playNote(instrument: Instrument, note: Note) = this.playNote(this.location, instrument, note)

fun Player.playSound(sound: Sound, volume: Float = 100F, pitch: Float = 1F) =
    this.playSound(this.location, sound, volume, pitch)

fun Player.playSound(sound: Sound, category: SoundCategory, volume: Float = 100F, pitch: Float = 1F) =
    this.playSound(this.location, sound, category, volume, pitch)

fun Player.playSoundFollow(sound: Sound, volume: Float = 100F, pitch: Float = 1F) =
    this.playSound(PaperSound.sound(sound.key, PaperSound.Source.MASTER, volume, pitch), PaperSound.Emitter.self())

fun Player.playSoundFollow(sound: Sound, category: SoundCategory, volume: Float = 100F, pitch: Float = 1F) =
    this.playSound(
        PaperSound.sound(sound.key, PaperSound.Source.valueOf(category.toString()), volume, pitch),
        PaperSound.Emitter.self()
    )
