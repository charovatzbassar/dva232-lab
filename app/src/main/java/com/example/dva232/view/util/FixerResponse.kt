package com.example.dva232.view.util

import kotlinx.serialization.Serializable

@Serializable
data class FixerResponse(
    val success: Boolean?,
    val timestamp: Int?,
    val base: String?,
    val date: String?,
    val rates: Map<String, Double>?
)
