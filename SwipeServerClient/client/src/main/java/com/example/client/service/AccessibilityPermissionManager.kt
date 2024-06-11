package com.example.client.service

import android.content.Context
import android.provider.Settings
import android.text.TextUtils
import android.app.AlertDialog
import android.content.ComponentName
import android.content.Intent
import com.example.client.R

/**
 * Менеджер разрешений для Accessibility Service.
 *
 * @param context Контекст приложения.
 */
class AccessibilityPermissionManager(private val context: Context) {

    /**
     * Открывает настройки Accessibility на устройстве.
     */
    fun openAccessibilitySettings() {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        context.startActivity(intent)
    }

    /**
     * Проверяет, включена ли служба Accessibility.
     *
     * @param service Класс службы, которую необходимо проверить.
     * @return true, если служба включена, иначе false.
     */
    fun isAccessibilityServiceEnabled(service: Class<SwipeAccessibilityService>): Boolean {
        val enabledServices = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        )
        if (enabledServices.isNullOrEmpty()) {
            return false
        }
        val colonSplitter = TextUtils.SimpleStringSplitter(':')
        colonSplitter.setString(enabledServices)
        while (colonSplitter.hasNext()) {
            val componentName = colonSplitter.next()
            if (componentName.equals(
                    ComponentName(context, service).flattenToString(),
                    ignoreCase = true
                )
            ) {
                return true
            }
        }
        return false
    }

    /**
     * Показывает диалоговое окно с просьбой предоставить разрешение для Accessibility Service.
     */
    fun showAccessibilityPermissionDialog() {
        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.accessibility_permission_required))
            .setMessage(context.getString(R.string.accessibility_permission_message))
            .setPositiveButton(context.getString(R.string.settings)) { _, _ ->
                openAccessibilitySettings()
            }
            .setNegativeButton(context.getString(R.string.cancel), null)
            .show()
    }
}
