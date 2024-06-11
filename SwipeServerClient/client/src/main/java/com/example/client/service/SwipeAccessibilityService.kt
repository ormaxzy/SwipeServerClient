package com.example.client.service

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.util.Log
import android.view.accessibility.AccessibilityEvent

/**
 * Служба доступности для выполнения жестов.
 */
class SwipeAccessibilityService : AccessibilityService() {

    companion object {
        private var instance: SwipeAccessibilityService? = null

        /**
         * Получение текущего экземпляра службы.
         *
         * @return Экземпляр SwipeAccessibilityService или null, если служба не активна.
         */
        fun getInstance(): SwipeAccessibilityService? {
            return instance
        }
    }

    /**
     * Метод, вызываемый при создании службы.
     */
    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    /**
     * Метод, вызываемый при получении события доступности.
     *
     * @param event Событие доступности.
     */
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Обработка событий доступности
    }

    /**
     * Метод, вызываемый при прерывании службы доступности.
     */
    override fun onInterrupt() {
        Log.d("@@@SwipeAccessibilityService", "Service interrupted")
    }

    /**
     * Метод, вызываемый при уничтожении службы.
     */
    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }

    /**
     * Выполнение жеста на устройстве.
     *
     * @param gesture Описание жеста для выполнения.
     */
    fun executeGesture(gesture: GestureDescription) {
        val dispatched = dispatchGesture(gesture, object : GestureResultCallback() {
            override fun onCompleted(gestureDescription: GestureDescription?) {
                super.onCompleted(gestureDescription)
            }

            override fun onCancelled(gestureDescription: GestureDescription?) {
                super.onCancelled(gestureDescription)
            }
        }, null)
    }

}
