package com.example.server.data.network

import com.example.common.model.GestureCommand

/**
 * Класс для создания различных команд жестов.
 */
class GesturesList {

    /**
     * Создает команду для жеста "Свайп вверх".
     *
     * @return Команда жеста.
     */
    fun createSwipeUpCommand(): GestureCommand {
        return GestureCommand(type = "SWIPE_UP", duration = 500, mX = 500f, mY = 1500f, lX = 500f, lY = 500f)
    }

    /**
     * Создает команду для жеста "Свайп вниз".
     *
     * @return Команда жеста.
     */
    fun createSwipeDownCommand(): GestureCommand {
        return GestureCommand(type = "SWIPE_DOWN", duration = 500, mX = 500f, mY = 500f, lX = 500f, lY = 1500f)
    }

    /**
     * Создает команду для жеста "Тап по строке поиска".
     *
     * @return Команда жеста.
     */
    fun createTapSearchBarCommand(): GestureCommand {
        return GestureCommand(type = "TAP_SEARCH_BAR", duration = 100, mX = 500f, mY = 200f, lX = 500f, lY = 200f)
    }

    /**
     * Создает команду для ввода текста в строку поиска.
     *
     * @param text Текст для ввода.
     * @return Команда жеста.
     */
    fun createTypeSearchQueryCommand(text: String): GestureCommand {
        return GestureCommand(type = "TYPE_SEARCH_QUERY", duration = 1000, text = text, mX = 0f, mY = 0f, lX = 0f, lY = 0f)
    }

    /**
     * Создает команду для запуска поиска.
     *
     * @return Команда жеста.
     */
    fun createStartSearchCommand(): GestureCommand {
        return GestureCommand(type = "START_SEARCH", duration = 100, mX = 500f, mY = 300f, lX = 500f, lY = 300f)
    }

    /**
     * Создает команду для жеста "Тап по меню с тремя точками".
     *
     * @return Команда жеста.
     */
    fun createTapThreeDotsMenuCommand(): GestureCommand {
        return GestureCommand(type = "TAP_THREE_DOTS_MENU", duration = 100, mX = 950f, mY = 200f, lX = 950f, lY = 200f)
    }

    /**
     * Создает команду для добавления в избранное.
     *
     * @return Команда жеста.
     */
    fun createAddToFavoritesCommand(): GestureCommand {
        return GestureCommand(type = "ADD_TO_FAVORITES", duration = 100, mX = 600f, mY = 200f, lX = 600f, lY = 200f)
    }
}
