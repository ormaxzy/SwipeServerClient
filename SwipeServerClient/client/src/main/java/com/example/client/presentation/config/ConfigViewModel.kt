package com.example.client.presentation.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.client.domain.usecase.GetConfigUseCase
import com.example.client.domain.usecase.SaveConfigUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfigViewModel @Inject constructor(
    private val saveConfigUseCase: SaveConfigUseCase,
    private val getConfigUseCase: GetConfigUseCase
) : ViewModel() {

    /**
     * Сохранение конфигурации IP и порта.
     *
     * @param ip IP адрес сервера.
     * @param port Порт сервера.
     */
    fun saveConfig(ip: String, port: String) {
        viewModelScope.launch {
            saveConfigUseCase.execute(ip, port)
        }
    }

    /**
     * Получение сохраненного IP адреса.
     *
     * @return Сохраненный IP адрес или null, если он не был сохранен.
     */
    fun getIp(): String? {
        return getConfigUseCase.getIp()
    }

    /**
     * Получение сохраненного порта.
     *
     * @return Сохраненный порт или null, если он не был сохранен.
     */
    fun getPort(): String? {
        return getConfigUseCase.getPort()
    }
}
