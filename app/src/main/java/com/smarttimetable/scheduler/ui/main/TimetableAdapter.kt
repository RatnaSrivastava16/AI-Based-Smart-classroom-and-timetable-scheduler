package com.smarttimetable.scheduler.ui.main

import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarttimetable.scheduler.R
import com.smarttimetable.scheduler.data.DisplayRow
import com.smarttimetable.scheduler.data.RowType
import com.smarttimetable.scheduler.databinding.ItemTimetableHeaderBinding
import com.smarttimetable.scheduler.databinding.ItemTimetableRowBinding

class TimetableAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<DisplayRow>()

    fun submit(list: List<DisplayRow>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].type == RowType.HEADER) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val binding = ItemTimetableHeaderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            HeaderViewHolder(binding)
        } else {
            val binding = ItemTimetableRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            SlotViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is HeaderViewHolder) {
            holder.bind(item)
        } else if (holder is SlotViewHolder) {
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int = items.size

    class HeaderViewHolder(private val binding: ItemTimetableHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DisplayRow) {
            binding.headerTitle.text = item.header
            binding.headerFitness.text = item.fitness
        }
    }

    class SlotViewHolder(private val binding: ItemTimetableRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DisplayRow) {
            val slot = item.slot ?: return
            binding.slotText.text = "Period ${slot.period}"
            val classLabel = slot.subject.targetClassName.ifBlank { "General" }
            binding.detailsText.text = "${slot.subject.name} (${classLabel}) | ${slot.faculty.name} | ${slot.classroom.roomName}"

            val ctx = binding.root.context
            if (slot.conflict) {
                binding.rowContainer.background = ContextCompat.getDrawable(ctx, R.drawable.bg_timetable_row_conflict)
                val red = ContextCompat.getColor(ctx, R.color.conflict_red)
                binding.slotText.setTextColor(red)
                binding.detailsText.setTextColor(red)
            } else {
                binding.rowContainer.background = ContextCompat.getDrawable(ctx, R.drawable.bg_timetable_row_normal)
                binding.slotText.setTextColor(ContextCompat.getColor(ctx, R.color.text_primary))
                binding.detailsText.setTextColor(ContextCompat.getColor(ctx, R.color.text_secondary))
            }
        }
    }
}
