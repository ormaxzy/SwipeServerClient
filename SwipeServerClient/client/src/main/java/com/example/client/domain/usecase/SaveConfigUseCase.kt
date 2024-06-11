package com.example.client.domain.usecase

import com.example.client.domain.repository.ConfigRepository


class SaveConfigUseCase(private val configRepository: ConfigRepository) {

    /**
     * Сохранение IP адреса и порта.
     *
     * @param ip IP адрес сервера.
     * @param port Порт сервера.
     */
    fun execute(ip: String, port: String) {
        configRepository.saveConfig(ip, port)
    }
}