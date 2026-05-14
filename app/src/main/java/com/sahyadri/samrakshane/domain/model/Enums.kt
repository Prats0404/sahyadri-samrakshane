package com.sahyadri.samrakshane.domain.model

enum class AlertType {
    FOREST_FIRE,
    LANDSLIDE,
    ILLEGAL_LOGGING,
    WILDLIFE
}

enum class AlertStatus {
    REPORTED,
    VERIFIED,
    TEAM_DISPATCHED
}

enum class SyncStatus {
    PENDING,
    SYNCED
}
