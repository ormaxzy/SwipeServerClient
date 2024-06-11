package com.example.client.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "client_prefs"
        private const val KEY_IP = "key_ip"
        private const val KEY_PORT = "key_port"
    }

    /**
     * Сохранение конфигурации IP и порта в SharedPreferences.
     */
    fun saveConfig(ip: String, port: String) {
        prefs.edit().apply {
            putString(KEY_IP, ip)
            putString(KEY_PORT, port)
            apply()
        }
    }

    /**
     * Получение сохраненного IP из SharedPreferences.
     */
    fun getIp(): String? {
        return prefs.getString(KEY_IP, null)
    }

    /**
     * Получение сохраненного порта из SharedPreferences.
     */
    fun getPort(): String? {
        return prefs.getString(KEY_PORT, null)
    }
}
