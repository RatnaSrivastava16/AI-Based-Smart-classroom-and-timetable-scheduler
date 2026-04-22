package com.smarttimetable.scheduler.ai

import com.smarttimetable.scheduler.data.TimetableOption

interface TimetableSolver {
    val name: String
    fun generateTopTimetables(topN: Int = 3): List<TimetableOption>
}
