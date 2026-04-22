package com.smarttimetable.scheduler.ui.main

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.smarttimetable.scheduler.R
import com.smarttimetable.scheduler.data.TimetableSlot
import kotlin.math.max

class TimetableCanvasView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri")
    private val density = resources.displayMetrics.density
    private val headerHeight = 44f.dp
    private val rowHeight = 96f.dp
    private val periodColumnWidth = 68f.dp
    private val dayColumnWidth = 170f.dp
    private val cellGap = 10f.dp
    private val outerPadding = 16f.dp
    private val cardRadius = 18f.dp

    private var animationProgress = 1f
    private var slots: List<TimetableSlot> = emptyList()
    private var maxPeriod = 6

    private val textPrimary = ContextCompat.getColor(context, R.color.text_primary)
    private val textSecondary = ContextCompat.getColor(context, R.color.text_secondary)
    private val softBlue = ContextCompat.getColor(context, R.color.soft_blue)
    private val softGreen = ContextCompat.getColor(context, R.color.soft_green)
    private val softAmber = ContextCompat.getColor(context, R.color.soft_amber)
    private val conflictSoft = ContextCompat.getColor(context, R.color.conflict_soft)
    private val normalSoft = ContextCompat.getColor(context, R.color.normal_soft)
    private val gridLine = 0x1A102A43

    private val titlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = textPrimary
        textSize = 14f.sp
        typeface = android.graphics.Typeface.create(android.graphics.Typeface.DEFAULT, android.graphics.Typeface.BOLD)
    }

    private val subtitlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = textSecondary
        textSize = 11f.sp
    }

    private val periodPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = textPrimary
        textSize = 12f.sp
        typeface = android.graphics.Typeface.create(android.graphics.Typeface.DEFAULT, android.graphics.Typeface.BOLD)
    }

    private val cellPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = gridLine.toInt()
        strokeWidth = 1f.dp
        style = Paint.Style.STROKE
    }

    fun setTimetableSlots(newSlots: List<TimetableSlot>) {
        slots = newSlots
        maxPeriod = max(6, newSlots.maxOfOrNull { it.period } ?: 6)
        startRevealAnimation()
        requestLayout()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = (outerPadding * 2 + periodColumnWidth + days.size * dayColumnWidth + (days.size + 1) * cellGap).toInt()
        val desiredHeight = (outerPadding * 2 + headerHeight + maxPeriod * rowHeight + (maxPeriod + 1) * cellGap).toInt()
        setMeasuredDimension(
            resolveSize(desiredWidth, widthMeasureSpec),
            resolveSize(desiredHeight, heightMeasureSpec)
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val alphaScale = animationProgress.coerceIn(0f, 1f)
        canvas.save()
        canvas.translate(0f, 16f.dp * (1f - alphaScale))

        drawHeaders(canvas, alphaScale)
        drawGrid(canvas, alphaScale)

        canvas.restore()
    }

    private fun drawHeaders(canvas: Canvas, alphaScale: Float) {
        titlePaint.alpha = (255 * alphaScale).toInt()
        subtitlePaint.alpha = (255 * alphaScale).toInt()

        days.forEachIndexed { index, day ->
            val left = outerPadding + periodColumnWidth + cellGap + index * dayColumnWidth
            canvas.drawText(day, left + 8f.dp, outerPadding + 20f.dp, titlePaint)
        }
    }

    private fun drawGrid(canvas: Canvas, alphaScale: Float) {
        for (period in 1..maxPeriod) {
            val rowTop = outerPadding + headerHeight + cellGap + (period - 1) * rowHeight
            periodPaint.alpha = (255 * alphaScale).toInt()
            subtitlePaint.alpha = (255 * alphaScale).toInt()
            canvas.drawText("P$period", outerPadding, rowTop + 22f.dp, periodPaint)
            canvas.drawText("Period $period", outerPadding, rowTop + 40f.dp, subtitlePaint)

            days.forEachIndexed { index, day ->
                val left = outerPadding + periodColumnWidth + cellGap + index * dayColumnWidth
                val rect = RectF(left, rowTop, left + dayColumnWidth - cellGap, rowTop + rowHeight - cellGap)
                val slot = slots.firstOrNull { it.day == day && it.period == period }
                cellPaint.style = Paint.Style.FILL
                cellPaint.color = when {
                    slot == null -> normalSoft
                    slot.conflict -> conflictSoft
                    index % 3 == 0 -> softGreen
                    index % 3 == 1 -> softBlue
                    else -> softAmber
                }
                cellPaint.alpha = (255 * alphaScale).toInt()
                canvas.drawRoundRect(rect, cardRadius, cardRadius, cellPaint)
                canvas.drawRoundRect(rect, cardRadius, cardRadius, linePaint)

                if (slot != null) {
                    drawSlotText(canvas, rect, slot, alphaScale)
                } else {
                    drawEmptyCell(canvas, rect, alphaScale)
                }
            }
        }
    }

    private fun drawSlotText(canvas: Canvas, rect: RectF, slot: TimetableSlot, alphaScale: Float) {
        titlePaint.alpha = (255 * alphaScale).toInt()
        subtitlePaint.alpha = (255 * alphaScale).toInt()
        val classLabel = slot.subject.targetClassName.ifBlank { "General" }
        canvas.drawText(ellipsize(slot.subject.name, 16), rect.left + 10f.dp, rect.top + 24f.dp, titlePaint)
        canvas.drawText(ellipsize(classLabel, 16), rect.left + 10f.dp, rect.top + 42f.dp, subtitlePaint)
        canvas.drawText(ellipsize(slot.faculty.name, 18), rect.left + 10f.dp, rect.top + 60f.dp, subtitlePaint)
        canvas.drawText(ellipsize(slot.classroom.roomName, 18), rect.left + 10f.dp, rect.top + 78f.dp, subtitlePaint)
    }

    private fun drawEmptyCell(canvas: Canvas, rect: RectF, alphaScale: Float) {
        subtitlePaint.alpha = (170 * alphaScale).toInt()
        canvas.drawText("Free slot", rect.left + 10f.dp, rect.top + 44f.dp, subtitlePaint)
    }

    private fun startRevealAnimation() {
        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 450L
            addUpdateListener {
                animationProgress = it.animatedValue as Float
                invalidate()
            }
            start()
        }
    }

    private fun ellipsize(text: String, limit: Int): String {
        return if (text.length <= limit) text else text.take(limit - 1) + "…"
    }

    private val Float.dp: Float get() = this * density

    private val Float.sp: Float
        get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, resources.displayMetrics)
}
