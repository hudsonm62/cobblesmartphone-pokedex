package com.ziroau.cobblesmartphone.pokedex.config


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nbp.cobblemon_smartphone.CobblemonSmartphone
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class PokedexActionConfig {
    var pokedexColour: String = "red"
    var actionEnabled: Boolean = true

    companion object {
        private const val PATH = "config/cobblesmartphone-pokedex.json"
        private val GSON: Gson = GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create()

        fun load(): PokedexActionConfig {
            val configFile = File(PATH)
            configFile.parentFile.mkdirs()

            var config: PokedexActionConfig
            try {
                if (!configFile.exists()) {
                    configFile.createNewFile()
                }
                val fileReader = FileReader(configFile)
                config = GSON.fromJson(fileReader, PokedexActionConfig::class.java) ?: PokedexActionConfig()
                fileReader.close()
            } catch (e: Exception) {
                CobblemonSmartphone.LOGGER.error(e.message, e)
                config = PokedexActionConfig()
            }

            config.save()
            return config
        }
    }

    fun save() {
        val configFile = File(PATH)
        try {
            val fileWriter = FileWriter(configFile)
            GSON.toJson(this, fileWriter)
            fileWriter.flush()
            fileWriter.close()
        } catch (e: Exception) {
            CobblemonSmartphone.LOGGER.error(e.message, e)
        }
    }
}