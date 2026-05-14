package com.sahyadri.samrakshane.data.repository

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.Content
import com.sahyadri.samrakshane.domain.model.AlertType
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class DefaultGenAiRepositoryTest {

    private lateinit var generativeModel: GenerativeModel
    private lateinit var repository: DefaultGenAiRepository

    @Before
    fun setup() {
        generativeModel = mock(GenerativeModel::class.java)
        repository = DefaultGenAiRepository(generativeModel)
    }

    @Test
    fun `analyzePhoto parses valid JSON correctly`() = runTest {
        // Arrange
        val mockBitmap = mock(Bitmap::class.java)
        val validJsonResponse = """
            ```json
            {
                "suggestedAlertType": "FOREST_FIRE",
                "sceneDescription": "A large fire in the dry brush."
            }
            ```
        """.trimIndent()
        
        val mockResponse = mock(GenerateContentResponse::class.java)
        `when`(mockResponse.text).thenReturn(validJsonResponse)
        `when`(generativeModel.generateContent(any(Content::class.java))).thenReturn(mockResponse)

        // Act
        val result = repository.analyzePhoto(mockBitmap)

        // Assert
        assertEquals(AlertType.FOREST_FIRE, result.suggestedType)
        assertEquals("A large fire in the dry brush.", result.structuredDescription)
    }

    @Test
    fun `analyzePhoto defaults to WILDLIFE on invalid JSON`() = runTest {
        // Arrange
        val mockBitmap = mock(Bitmap::class.java)
        val invalidJsonResponse = "This is not json"
        
        val mockResponse = mock(GenerateContentResponse::class.java)
        `when`(mockResponse.text).thenReturn(invalidJsonResponse)
        `when`(generativeModel.generateContent(any(Content::class.java))).thenReturn(mockResponse)

        // Act
        val result = repository.analyzePhoto(mockBitmap)

        // Assert
        assertEquals(AlertType.WILDLIFE, result.suggestedType)
        assertEquals("Failed to analyze photo.", result.structuredDescription)
    }
}
