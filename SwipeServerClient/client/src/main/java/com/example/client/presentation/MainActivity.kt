package com.example.client.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import com.example.client.presentation.nav.NavGraph
import com.example.client.presentation.theme.SwipeClientTheme
import com.example.client.service.AccessibilityPermissionManager
import com.example.client.service.SwipeAccessibilityService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var accessibilityPermissionManager: AccessibilityPermissionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        accessibilityPermissionManager = AccessibilityPermissionManager(this)
        setContent {
            SwipeClientTheme {
                LaunchedEffect(Unit) {
                    if (!accessibilityPermissionManager.isAccessibilityServiceEnabled(
                            SwipeAccessibilityService::class.java
                        )
                    ) {
                        accessibilityPermissionManager.showAccessibilityPermissionDialog()
                    }
                }
                NavGraph(
                    onSettingsClick = { accessibilityPermissionManager.openAccessibilitySettings() },
                    onCheckPermissions = { checkPermissions() }
                )
            }
        }
    }

    /**
     * Проверка разрешений для Accessibility Service.
     *
     * @return true, если разрешение предоставлено, иначе false.
     */
    private fun checkPermissions(): Boolean {
        return if (!accessibilityPermissionManager.isAccessibilityServiceEnabled(
                SwipeAccessibilityService::class.java
            )
        ) {
            accessibilityPermissionManager.showAccessibilityPermissionDialog()
            false
        } else {
            true
        }
    }
}
