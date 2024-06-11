package com.example.client.domain.usecase

import com.example.client.domain.repository.ConfigRepository


class GetConfigUseCase(private val configRepository: ConfigRepository) {

    /**
     * Получение IP адреса.
     *
     * @return Сохраненный IP адрес или null, если он не был сохранен.
     */
    fun getIp(): String? {
        return configRepository.getIp()
    }

    /**
     * Получение порта.
     *
     * @return Сохраненный порт или null, если он не был сохранен.
     */
    fun getPort(): String? {
        return configRepository.getPort()
    }
}