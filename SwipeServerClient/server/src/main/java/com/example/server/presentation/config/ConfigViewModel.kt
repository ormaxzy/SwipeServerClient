package com.example.server.presentation.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.server.domain.usecase.GetConfigUseCase
import com.example.server.domain.usecase.SaveConfigUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfigViewModel @Inject constructor(
    private val saveConfigUseCase: SaveConfigUseCase,
    private val getConfigUseCase: GetConfigUseCase

) : ViewModel() {

    /**
     * Сохраняет конфигурацию порта.
     *
     * @param port Порт для сохранения.
     */
    fun saveConfig(port: String) {
        viewModelScope.launch {
            saveConfigUseCase.execute(port)
        }
    }

    fun getPort(): String? {
        return getConfigUseCase.getPort()
    }
}
