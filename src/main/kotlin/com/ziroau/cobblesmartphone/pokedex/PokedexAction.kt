package com.ziroau.cobblesmartphone.pokedex

import com.cobblemon.mod.common.CobblemonSounds
import com.cobblemon.mod.common.client.CobblemonClient
import com.nbp.cobblemon_smartphone.api.SmartphoneAction
import com.cobblemon.mod.common.client.gui.pokedex.PokedexGUI
import com.cobblemon.mod.common.client.pokedex.PokedexType
import com.ziroau.cobblesmartphone.pokedex.SmartphonePokedexApp.Companion.logger
import net.minecraft.client.MinecraftClient
import net.minecraft.util.Identifier

object PokedexAction : SmartphoneAction {
    override val id = "smartphone-pokedex-app:pokedex"
    override val texture = Identifier.of("smartphone-pokedex-app", "textures/gui/buttons/pokedex_icon.png")
    override val hoverTexture = Identifier.of("smartphone-pokedex-app", "textures/gui/buttons/hover_pokedex_icon.png")

    fun getPokedexType(): PokedexType {
        return try {
            PokedexType.valueOf(SmartphonePokedexApp.config.pokedexColour.uppercase())
        } catch (e: Exception) {
            logger.error("Failed to get pokedex colour type - Defaulting to 'red'", e)

            // TODO: Match Pokedex colour to smartphone colour, instead of using custom colour
            // return value
            PokedexType.RED
        }
    }

    override fun onClick() {
        val client = MinecraftClient.getInstance()
        val player = client.player ?: return
        val pokedexManager = CobblemonClient.clientPokedexData
        val pokedexType = getPokedexType()

        // close smartphone gui
        client.setScreen(null)


        // open pokedex
        player.playSound(CobblemonSounds.POKEDEX_CLICK, 0.5f, 1f)
        PokedexGUI.open(pokedexManager, pokedexType)
    }

    // always enable app
    override fun isEnabled(): Boolean {
        return SmartphonePokedexApp.config.actionEnabled
    }
}
