package com.smarttimetable.scheduler.util

data class TimeSlot(
    val dayIndex: Int,
    val periodIndex: Int
)

object ScheduleConfig {
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri")
    const val periodsPerDay = 6
}
