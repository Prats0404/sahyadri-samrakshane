package com.sahyadri.samrakshane.domain.repository

import android.graphics.Bitmap
import com.sahyadri.samrakshane.domain.model.AlertType

data class GenAiResult(
    val suggestedType: AlertType,
    val structuredDescription: String
)

interface GenAiRepository {
    suspend fun analyzePhoto(bitmap: Bitmap): GenAiResult
}
