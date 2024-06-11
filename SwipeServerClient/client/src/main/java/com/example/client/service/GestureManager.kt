package com.example.client.service

import android.accessibilityservice.GestureDescription
import android.content.Context
import android.graphics.Path
import android.os.Bundle
import android.util.Log
import android.view.accessibility.AccessibilityNodeInfo
import com.example.common.model.GestureCommand
import com.example.common.model.GestureReport

/**
 * Класс для выполнения жестов на устройстве с помощью Accessibility Service.
 */
class GestureManager() {

    /**
     * Выполнение жеста на основе команды.
     *
     * @param command Команда для выполнения жеста.
     * @return Отчет о выполнении жеста.
     */
    fun performGesture(command: GestureCommand): GestureReport {
        return try {
            when (command.type) {
                "TYPE_SEARCH_QUERY" -> {
                    command.text?.let { inputText ->
                        getAccessibilityService()?.let { service ->
                            val rootNode = service.rootInActiveWindow
                            if (rootNode != null) {
                                findAndInputText(rootNode, inputText)
                                rootNode.recycle()
                            }
                        }
                    }
                }
                else -> {
                    val path = Path().apply {
                        moveTo(command.mX, command.mY)
                        lineTo(command.lX, command.lY)
                    }
                    val gesture = GestureDescription.Builder()
                        .addStroke(GestureDescription.StrokeDescription(path, 0, command.duration))
                        .build()
                    getAccessibilityService()?.executeGesture(gesture)
                }
            }
            GestureReport(command.type, true, System.currentTimeMillis())
        } catch (e: Exception) {
            Log.e("@@@GestureManager", "Error performing gesture: ${e.message}")
            GestureReport(command.type, false, System.currentTimeMillis())
        }
    }

    /**
     * Поиск и ввод текста в элемент ввода текста.
     *
     * @param node Узел дерева доступа, в который нужно ввести текст.
     * @param inputText Текст для ввода.
     */
    private fun findAndInputText(node: AccessibilityNodeInfo, inputText: String) {
        if (node.className == "android.widget.EditText") {
            val args = Bundle().apply {
                putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, inputText)
            }
            node.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, args)
            return
        }
        for (i in 0 until node.childCount) {
            val child = node.getChild(i)
            if (child != null) {
                findAndInputText(child, inputText)
            }
        }
    }

    /**
     * Получение экземпляра службы Accessibility.
     *
     * @return Экземпляр службы SwipeAccessibilityService или null, если служба не активна.
     */
    private fun getAccessibilityService(): SwipeAccessibilityService? {
        return SwipeAccessibilityService.getInstance()
    }
}
