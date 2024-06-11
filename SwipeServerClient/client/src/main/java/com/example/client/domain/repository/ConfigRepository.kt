package com.example.client.domain.repository

interface ConfigRepository {
    /**
     * Сохранение IP и порта в конфигурации.
     *
     * @param ip IP адрес сервера.
     * @param port Порт сервера.
     */
    fun saveConfig(ip: String, port: String)

    /**
     * Получение сохраненного IP из конфигурации.
     *
     * @return Сохраненный IP адрес или null, если он не был сохранен.
     */
    fun getIp(): String?

    /**
     * Получение сохраненного порта из конфигурации.
     *
     * @return Сохраненный порт или null, если он не был сохранен.
     */
    fun getPort(): String?
}