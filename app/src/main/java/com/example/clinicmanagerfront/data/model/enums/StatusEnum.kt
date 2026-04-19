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
    PENDING(
        ru = "В ожидании",
        bgColor = StatusPendingContainer,
        textColor = StatusPendingText
    ),
    IN_PROGRESS(
        ru = "В процессе",
        bgColor = StatusInProgressContainer,
        textColor = StatusInProgressText
    ),
    SCHEDULED(
        ru = "Запланировано",
        bgColor = StatusScheduledContainer,
        textColor = StatusScheduledText
    ),
    NO_SHOW(
        ru = "Не явился",
        bgColor = StatusNoShowContainer,
        textColor = StatusNoShowText
    ),
    CANCELLED(
        ru = "Отменено",
        bgColor = StatusCancelledContainer,
        textColor = StatusCancelledText
    )
}
