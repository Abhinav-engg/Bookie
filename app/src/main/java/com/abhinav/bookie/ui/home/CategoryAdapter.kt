package com.abhinav.bookie.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhinav.bookie.R
import com.abhinav.bookie.data.Category
import com.google.android.material.button.MaterialButton

class CategoryAdapter(
    private val categories: List<Category>,
    private val onCategoryClick: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition = 0

    class CategoryViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val categoryButton: MaterialButton =
            itemView.findViewById(R.id.categoryButton)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)

        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        holder.categoryButton.apply {
            text = categories[position].name

            if (position == selectedPosition) {
                setTextColor(context.getColor(R.color.white))
                backgroundTintList = context.getColorStateList(R.color.Primary)
                strokeWidth = 0
            } else {
                setTextColor(context.getColor(R.color.category_text))
                backgroundTintList = context.getColorStateList(R.color.Neutral)
                strokeColor = context.getColorStateList(R.color.category_outline)
                strokeWidth = 2
            }

            setOnClickListener {
                val oldPosition = selectedPosition
                selectedPosition = holder.bindingAdapterPosition
                notifyItemChanged(oldPosition)
                notifyItemChanged(selectedPosition)
                onCategoryClick(categories[selectedPosition])
            }
        }
    }

    override fun getItemCount(): Int = categories.size
}
