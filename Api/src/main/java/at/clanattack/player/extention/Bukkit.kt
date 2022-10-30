package at.clanattack.player.extention

import at.clanattack.message.getMessage
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import org.bukkit.Instrument
import org.bukkit.Note
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.Player
import java.time.Duration
import net.kyori.adventure.sound.Sound as PaperSound

fun Player.sendKeyedMessage(key: String, vararg placeholders: String) = this.sendMessage(getMessage(key, *placeholders))

fun Player.sendKeyedTitle(
    fadeIn: Number, stay: Number, fadeOut: Number, title: String?, subtitle: String?, vararg placeholders: String
) = this.showTitle(
    Title.title(
        if (title == null) Component.text("") else getMessage(title, *placeholders),
        if (subtitle == null) Component.text("") else getMessage(subtitle, *placeholders),
        Title.Times.times(
            Duration.ofSeconds(fadeIn.toLong()),
            Duration.ofSeconds(stay.toLong()),
            Duration.ofSeconds(fadeOut.toLong()),
        )
    )
)

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
