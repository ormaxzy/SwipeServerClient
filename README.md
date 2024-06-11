Привет! Меня зовут Феодор Маслов, и я являюсь Android-разработчиком, специализирующимся на использовании Kotlin. В данном репозитории представлен мой проект SwipeServerClient. Этот проект отражает мой опыт и навыки в области разработки приложений для Android.

# SwipeServerClient

SwipeServerClient - это приложение, состоящее из двух частей: клиента и сервера. Основная задача приложения - управление жестами (свайпы, клики, ввод текста) в браузере Google Chrome на клиентском устройстве с помощью сервера. Этот проект демонстрирует мои навыки в следующих областях:

### Работа с сетью
Я использовал Ktor для реализации WebSocket-соединения между клиентом и сервером. Клиент и сервер могут обмениваться командами для выполнения жестов и отчетами о результатах этих команд.

### База данных
На сервере я использовал Room для сохранения логов выполнения команд. Это позволяет отслеживать и сохранять историю всех выполненных жестов и их результаты.

### Dependency Injection (DI)
Я использовал Hilt для управления зависимостями в приложении, что повышает модульность и тестируемость кода. Это также упрощает управление жизненным циклом зависимостей и делает код более чистым и поддерживаемым.

### Jetpack Compose
Я использовал Jetpack Compose для создания пользовательского интерфейса (UI) приложения. Compose значительно упрощает разработку UI, делая код более декларативным и удобным для понимания и поддержки.

- **UI-компоненты**: Приложение содержит несколько экранов, включая экран конфигурации и основной экран с кнопками "Начать/Пауза" на клиенте и "Начать", "Выключить" и "Логи" на сервере.
- **State Management**: Использование ViewModel и состояния (state) в Compose для управления UI.
- **Тестируемость**: Compose позволяет легко создавать тесты для UI-компонентов.
- **Навигация**: Применена навигация с использованием Jetpack Navigation Compose, что позволяет легко управлять переходами между экранами и обеспечивает плавный пользовательский опыт.

### Чистая архитектура
Я применил принципы чистой архитектуры, разделив приложение на несколько слоев: presentation, domain и data. Это обеспечивает высокую масштабируемость, тестируемость и поддерживаемость кода.

- **Presentation Layer: Содержит UI-компоненты, такие как экраны конфигурации и основного экрана, а также ViewModel для управления состоянием UI.
- **Domain Layer: Включает use cases и бизнес-логику, обеспечивающую выполнение основных операций приложения.
- **Data Layer: Содержит репозитории, источники данных и модели данных, используемые для работы с сетью и базой данных.

### Читабельность кода
Я уделил особое внимание написанию читаемого и понятного кода, используя осмысленные имена переменных и методов, следуя принципам чистого кода. Каждый метод выполняет одну задачу, что делает код более понятным и легким в поддержке.

### Особенности приложения
· **Управление жестами**: Сервер отправляет команды для выполнения жестов в Google Chrome на клиентском устройстве. Клиент выполняет эти команды с помощью Accessibility Service и отправляет отчеты о выполнении обратно на сервер.
· **Логирование**: Сервер сохраняет логи всех выполненных команд и их результаты в локальной базе данных.
· **Пользовательский интерфейс**:
  - Клиент: Экран конфигурации для настройки IP и порта сервера, основной экран с кнопками "Начать/Пауза".
  - Сервер: Экран конфигурации для выбора порта, основной экран с кнопками "Начать", "Выключить" и "Логи".

### Персонализация
Для сохранения настроек приложения, таких как IP и порт сервера, я использовал Shared Preferences. Это позволяет сохранять пользовательские настройки даже после перезапуска приложения.

*Сборка стандартная: (Build -> Build App Bundle(s) / APK(s) -> Build APK(s))*

Я надеюсь, что этот проект продемонстрирует мои навыки и опыт в разработке Android-приложений с использованием Kotlin. Буду рад ответить на любые вопросы или обсудить дальнейшие детали. Спасибо за рассмотрение моей кандидатуры!

*************************

Hello! My name is Feodor Maslov, and I am an Android developer specializing in Kotlin. This repository presents my project SwipeServerClient. This project showcases my experience and skills in Android application development.

# SwipeServerClient

SwipeServerClient is an application consisting of two parts: a client and a server. The main task of the application is to manage gestures (swipes, clicks, text input) in the Google Chrome browser on the client device using the server. This project demonstrates my skills in the following areas:

### Network Operations
I used Ktor to implement WebSocket communication between the client and the server. The client and server can exchange commands to perform gestures and reports on the results of these commands.

### Database
On the server side, I used Room to save logs of command executions. This allows tracking and saving the history of all performed gestures and their results.

### Dependency Injection (DI)
I used Hilt to manage dependencies in the application, which enhances code modularity and testability. It also simplifies dependency lifecycle management and makes the code cleaner and more maintainable.

### Jetpack Compose
I used Jetpack Compose to create the user interface (UI) of the application. Compose significantly simplifies UI development, making the code more declarative and easier to understand and maintain.

- **UI Components**: The application includes several screens, including a configuration screen and a main screen with "Start/Pause" buttons on the client and "Start", "Stop", and "Logs" buttons on the server.
- **State Management**: Utilized ViewModel and state management in Compose to control the UI.
- **Testability**: Compose allows for easy creation of tests for UI components.
- **Navigation**: Implemented navigation using Jetpack Navigation Compose, which allows easy management of screen transitions and ensures a smooth user experience.

### Clean Architecture
I applied clean architecture principles, dividing the application into several layers: presentation, domain, and data. This ensures high scalability, testability, and maintainability of the code.

- **Presentation Layer**: Contains UI components, such as configuration and main screens, as well as ViewModel for managing UI state.
- **Domain Layer**: Includes use cases and business logic, ensuring the execution of the main operations of the application.
- **Data Layer**: Contains repositories, data sources, and data models used for network and database operations.

### Code Readability
I paid special attention to writing readable and understandable code, using meaningful variable and method names, and following clean code principles. Each method performs a single task, making the code more comprehensible and easier to maintain.

### Application Features
- **Gesture Management**: The server sends commands to perform gestures in Google Chrome on the client device. The client executes these commands using the Accessibility Service and sends execution reports back to the server.
- **Logging**: The server saves logs of all executed commands and their results in the local database.
- **User Interface**:
  - **Client**: Configuration screen for setting the server IP and port, main screen with "Start/Pause" buttons.
  - **Server**: Configuration screen for selecting the port, main screen with "Start", "Stop", and "Logs" buttons.

### Personalization
I used Shared Preferences to save application settings, such as the server IP and port. This allows user settings to be retained even after the application is restarted.

*Standard build process: (Build -> Build App Bundle(s) / APK(s) -> Build APK(s))*

I hope this project demonstrates my skills and experience in developing Android applications using Kotlin. I would be happy to answer any questions or discuss further details. Thank you for considering my application!
