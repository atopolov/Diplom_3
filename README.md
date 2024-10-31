# Дипломный проект 3 из 3 частей (UI-тесты)

## Стек технологий используемый в проекте

- Java 11
- Apache Maven 3.8.6
- Junit 4.13.2
- RestAssured 5.3.0
- Selenium 4.25.0
- Allure 2.12.0
- Javafaker 1.0.2

## Необходимые команды

# Чтобы запустить тесты в Google Chrome, нужно использовать команду:
mvn clean test

# Чтобы запустить тесты в браузере Mozilla Firefox, нужно использовать команду:
mvn clean test --define browser=firefox

# Генерации отчета Allure:
mvn allure:report

# Открыть отчет Allure в браузере:
mvn allure:serve



