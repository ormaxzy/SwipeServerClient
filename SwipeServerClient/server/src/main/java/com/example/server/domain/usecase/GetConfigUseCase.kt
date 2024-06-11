package com.example.server.domain.usecase

import com.example.server.domain.repository.ConfigRepository
import javax.inject.Inject

class GetConfigUseCase @Inject constructor(
    private val configRepository: ConfigRepository
) {

    /**
     * Получает сохраненный порт из репозитория конфигурации.
     *
     * @return Сохраненный порт как строку или null, если порт не сохранен.
     */
    fun getPort(): String? {
        return configRepository.getPort()
    }
}
