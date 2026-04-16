package com.example.clinicmanagerfront.data.model.enums

import androidx.compose.ui.graphics.Color
import com.example.clinicmanagerfront.ui.theme.*

enum class StatusEnum(
    val ru: String,
    val bgColor: Color,
    val textColor: Color
) {
    COMPLETED(
        ru = "Завершено",
        bgColor = StatusCompletedContainer,
        textColor = StatusCompletedText
    ),
    CONFIRMED(
        ru = "Подтверждено",
        bgColor = StatusConfirmedContainer,
        textColor = StatusConfirmedText
    ),
    IN_PROGRESS(
        ru = "В процессе",
        bgColor = Color(0xFFE3F2FD),
        textColor = Color(0xFF64B5F6)
    ),
    SCHEDULED(
        ru = "Запланировано",
        bgColor = StatusScheduledContainer,
        textColor = StatusScheduledText
    ),
    NO_SHOW(
        ru = "Не явился",
        bgColor = Color(0xFFFFF3E0),
        textColor = Color(0xFFFFB74D)
    ),
    CANCELLED(
        ru = "Отменено",
        bgColor = StatusCancelledContainer,
        textColor = StatusCancelledText
    )
}
