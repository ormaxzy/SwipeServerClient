package com.example.client.data.repository

import com.example.client.data.local.SharedPreferencesManager
import com.example.client.domain.repository.ConfigRepository

class ConfigRepositoryImpl(private val sharedPreferencesManager: SharedPreferencesManager) :
    ConfigRepository {

    /**
     * Сохранение конфигурации IP и порта.
     */
    override fun saveConfig(ip: String, port: String) {
        sharedPreferencesManager.saveConfig(ip, port)
    }

    /**
     * Получение IP.
     */
    override fun getIp(): String? {
        return sharedPreferencesManager.getIp()
    }

    /**
     * Получение порта.
     */
    override fun getPort(): String? {
        return sharedPreferencesManager.getPort()
    }
}
