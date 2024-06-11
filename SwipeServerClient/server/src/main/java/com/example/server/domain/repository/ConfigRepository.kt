package com.example.server.domain.repository

interface ConfigRepository {

    /**
     * Сохранение порта в конфигурации.
     *
     * @param port Порт сервера.
     */
    fun saveConfig(port: String)

    /**
     * Получение сохраненного порта из конфигурации.
     *
     * @return Сохраненный порт или null, если он не был сохранен.
     */
    fun getPort(): String?
}