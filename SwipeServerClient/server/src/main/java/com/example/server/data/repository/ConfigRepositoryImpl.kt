package com.example.server.data.repository

import android.content.SharedPreferences
import com.example.server.domain.repository.ConfigRepository
import javax.inject.Inject

class ConfigRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ConfigRepository {

    companion object {
        const val KEY_PORT = "config_port_key"
    }

    /**
     * Сохраняет конфигурацию порта.
     *
     * @param port Порт для сохранения.
     */
    override fun saveConfig(port: String) {
        if (port.isNotBlank()) {
            sharedPreferences.edit().putString(KEY_PORT, port).apply()
        }
    }

    /**
     * Получает сохраненный порт из конфигурации.
     *
     * @return Сохраненный порт или null, если он не сохранен.
     */
    override fun getPort(): String? {
        return sharedPreferences.getString(KEY_PORT, null)
    }
}
