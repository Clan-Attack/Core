package at.clanattack.impl.message

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.JoinConfiguration
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration

class Colorizing {

    private val colorMap = mapOf(
        '0' to NamedTextColor.BLACK,
        '1' to NamedTextColor.DARK_BLUE,
        '2' to NamedTextColor.DARK_GREEN,
        '3' to NamedTextColor.DARK_AQUA,
        '4' to NamedTextColor.DARK_RED,
        '5' to NamedTextColor.DARK_PURPLE,
        '6' to NamedTextColor.GOLD,
        '7' to NamedTextColor.GRAY,
        '8' to NamedTextColor.DARK_GRAY,
        '9' to NamedTextColor.BLUE,
        'a' to NamedTextColor.GREEN,
        'b' to NamedTextColor.AQUA,
        'c' to NamedTextColor.RED,
        'd' to NamedTextColor.LIGHT_PURPLE,
        'e' to NamedTextColor.YELLOW,
        'f' to NamedTextColor.WHITE,
    )

    private val decorationMap = mapOf(
        'k' to TextDecoration.OBFUSCATED,
        'l' to TextDecoration.BOLD,
        'm' to TextDecoration.STRIKETHROUGH,
        'n' to TextDecoration.UNDERLINED,
        'o' to TextDecoration.ITALIC,
    )

    fun colorize(message: String, code: Char): Component {
        var color: TextColor? = null
        val components = mutableListOf<ComponentLike>()
        val decorations = mutableListOf<TextDecoration>()
        val buffer = mutableListOf<String>()

        message.split(code)
            .filter { it.isNotEmpty() }
            .forEach {
                when (val char = it[0].lowercaseChar()) {
                    '#' -> run {
                        if (it.length < 7) {
                            buffer.add(it.substring(7))
                            return@run
                        }

                        components.add(Component.text(buffer.joinToString(), color, decorations.toSet()))
                        buffer.clear()

                        val hex = it.substring(0, 7)
                        color = TextColor.fromHexString(hex)
                        if (it.length > 7) buffer.add(it.substring(7))
                    }

                    'r' -> {
                        components.add(Component.text(buffer.joinToString(), color, decorations.toSet()))
                        buffer.clear()

                        color = null
                        decorations.clear()
                        if (it.length > 1) buffer.add(it.substring(1))
                    }

                    in colorMap.keys -> {
                        components.add(Component.text(buffer.joinToString(), color, decorations.toSet()))
                        buffer.clear()

                        color = colorMap[char]
                        decorations.clear()
                        if (it.length > 1) buffer.add(it.substring(1))
                    }

                    in decorationMap.keys -> {
                        components.add(Component.text(buffer.joinToString(), color, decorations.toSet()))
                        buffer.clear()

                        decorations.add(decorationMap[char]!!)
                        if (it.length > 1) buffer.add(it.substring(1))
                    }

                    else -> buffer.add(char + it)
                }

            }

        if (buffer.isNotEmpty()) {
            components.add(Component.text(buffer.joinToString(), color, decorations.toSet()))
            buffer.clear()
        }

        return Component.join(JoinConfiguration.noSeparators(), components)
    }

}