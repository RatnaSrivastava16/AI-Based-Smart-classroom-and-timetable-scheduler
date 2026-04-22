package com.smarttimetable.scheduler.ui.common

import android.view.LayoutInflater
import android.widget.LinearLayout
import com.smarttimetable.scheduler.databinding.ItemDashboardInfoBinding

object DashboardUiRenderer {

    data class CardAction(
        val label: String,
        val onClick: () -> Unit
    )

    data class CardActions(
        val editAction: CardAction? = null,
        val deleteAction: CardAction? = null
    )

    fun renderInfoCards(
        container: LinearLayout,
        items: List<Triple<String, String, CardActions?>>,
        emptyTitle: String,
        emptySubtitle: String
    ) {
        container.removeAllViews()
        val inflater = LayoutInflater.from(container.context)
        val content = if (items.isEmpty()) {
            listOf(Triple(emptyTitle, emptySubtitle, null))
        } else {
            items
        }

        content.forEachIndexed { index, entry ->
            val binding = ItemDashboardInfoBinding.inflate(inflater, container, false)
            binding.infoTitle.text = entry.first
            binding.infoSubtitle.text = entry.second
            val actions = entry.third
            if (actions?.editAction == null) {
                binding.editButton.visibility = android.view.View.GONE
                binding.editButton.setOnClickListener(null)
            } else {
                binding.editButton.visibility = android.view.View.VISIBLE
                binding.editButton.text = actions.editAction.label
                binding.editButton.setOnClickListener { actions.editAction.onClick() }
            }
            if (actions?.deleteAction == null) {
                binding.deleteButton.visibility = android.view.View.GONE
                binding.deleteButton.setOnClickListener(null)
            } else {
                binding.deleteButton.visibility = android.view.View.VISIBLE
                binding.deleteButton.text = actions.deleteAction.label
                binding.deleteButton.setOnClickListener { actions.deleteAction.onClick() }
            }
            if (index == 0) {
                val params = binding.root.layoutParams as LinearLayout.LayoutParams
                params.topMargin = 0
                binding.root.layoutParams = params
            }
            container.addView(binding.root)
        }
    }
}
