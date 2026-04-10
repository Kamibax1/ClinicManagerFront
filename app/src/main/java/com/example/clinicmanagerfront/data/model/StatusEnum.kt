package com.example.clinicmanagerfront.data.model

enum class StatusEnum(val ru: String) {
    SCHEDULED("Запланировано"),
    CONFIRMED("Подтверждено"),
    COMPLETED("Завершено"),
    CANCELLED("Отменено")
}
