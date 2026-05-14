package com.sahyadri.samrakshane.data.repository

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.sahyadri.samrakshane.domain.model.AlertType
import com.sahyadri.samrakshane.domain.repository.GenAiResult
import com.sahyadri.samrakshane.domain.repository.GenAiRepository
import org.json.JSONObject

class DefaultGenAiRepository(
    private val generativeModel: GenerativeModel
) : GenAiRepository {

    override suspend fun analyzePhoto(bitmap: Bitmap): GenAiResult {
        return try {
            val prompt = """
                Analyze this photo taken in the Western Ghats region.
                Identify if it contains a forest fire, a landslide, illegal logging, or a wildlife sighting.
                Respond strictly in valid JSON format with no markdown formatting:
                {
                    "suggestedAlertType": "FOREST_FIRE" | "LANDSLIDE" | "ILLEGAL_LOGGING" | "WILDLIFE",
                    "sceneDescription": "A concise, structured description of what is seen in the image."
                }
                If unsure, default to WILDLIFE and provide a description of the landscape.
            """.trimIndent()

            val inputContent = content {
                image(bitmap)
                text(prompt)
            }

            val response = generativeModel.generateContent(inputContent)
            val responseText = response.text?.trim()?.removePrefix("```json")?.removeSuffix("```") ?: "{}"

            val jsonObject = JSONObject(responseText)
            val alertTypeStr = jsonObject.optString("suggestedAlertType", "WILDLIFE")
            val description = jsonObject.optString("sceneDescription", "No description available.")
            
            val alertType = try {
                AlertType.valueOf(alertTypeStr)
            } catch (e: IllegalArgumentException) {
                AlertType.WILDLIFE
            }

            GenAiResult(alertType, description)
        } catch (e: Exception) {
            e.printStackTrace()
            GenAiResult(AlertType.WILDLIFE, "Failed to analyze photo: ${e.localizedMessage}")
        }
    }
}
