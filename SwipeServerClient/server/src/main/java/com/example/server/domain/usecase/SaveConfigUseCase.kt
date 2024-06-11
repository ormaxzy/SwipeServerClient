package com.example.server.domain.usecase

import com.example.server.domain.repository.ConfigRepository

class SaveConfigUseCase(
    private val configRepository: ConfigRepository
) {

    /**
     * Сохраняет конфигурацию порта.
     *
     * @param port Порт для сохранения.
     */
    fun execute(port: String) {
        configRepository.saveConfig(port)
    }
}
