package com.ziroau.cobblesmartphone.pokedex

import com.nbp.cobblemon_smartphone.api.SmartphoneActionRegistry
import com.ziroau.cobblesmartphone.pokedex.config.PokedexActionConfig
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

class SmartphonePokedexApp : ModInitializer {
	companion object {
		val logger = LoggerFactory.getLogger("smartphone-pokedex-app")
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
	}
}
