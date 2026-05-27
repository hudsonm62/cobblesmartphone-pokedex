package com.ziroau.cobblesmartphone.pokedex

import com.nbp.cobblemon_smartphone.api.SmartphoneActionRegistry
import com.ziroau.cobblesmartphone.pokedex.config.PokedexActionConfig
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

class SmartphonePokedexApp : ModInitializer {
	companion object {
		const val ID = "smartphone-pokedex-app"
		val logger = LoggerFactory.getLogger(ID)
		lateinit var config: PokedexActionConfig
	}

	override fun onInitialize() {
		logger.info("Initializing Smartphone Pokedex App")

		config = PokedexActionConfig.load()

		try {
			SmartphoneActionRegistry.register(PokedexAction)
			logger.info("Smartphone Pokedex App initialized successfully")
		} catch (e: Exception) {
			logger.error("Failed to register Pokedex action", e)
		}

		val modContainer = FabricLoader.getInstance().getModContainer("smartphone-pokedex-app").orElse(null)
		ResourceManagerHelper.registerBuiltinResourcePack(
			Identifier.of(ID, "bluepokedexicon"),
			modContainer,
			Text.literal("Blue Pokedex Smartphone Icon"),
			ResourcePackActivationType.NORMAL
		)
	}
}
