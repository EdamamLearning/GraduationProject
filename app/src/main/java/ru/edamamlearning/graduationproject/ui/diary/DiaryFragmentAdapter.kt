package ru.edamamlearning.graduationproject.ui.diary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.edamamlearning.graduationproject.databinding.ItemSearchBinding
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.utils.extensions.loadPicture
import ru.edamamlearning.graduationproject.utils.roundAp

class DiaryFragmentAdapter(
    private val isFoodChoice: (FoodDomainModel) -> Boolean,
    private val diaryClickHandler: (FoodDomainModel) -> Boolean,
) : ListAdapter<FoodDomainModel, DiaryFragmentAdapter.DiaryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        return DiaryViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<FoodDomainModel>() {

        override fun areItemsTheSame(oldItem: FoodDomainModel, newItem: FoodDomainModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FoodDomainModel,
            newItem: FoodDomainModel
        ): Boolean {
            return oldItem.foodId == newItem.foodId
        }
    }

    inner class DiaryViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: FoodDomainModel) {
            binding.label.text = model.label
            binding.category.text = model.category
            binding.proteinCount.text = roundAp(model.nutrients.protein)
            binding.fatsCount.text = roundAp(model.nutrients.fat)
            binding.carbohydratesCount.text = roundAp(model.nutrients.carbohydrate)
            binding.foodImage.loadPicture(model.image)
            binding.favoriteButton.isVisible = false
            binding.diaryButton.isChecked = isFoodChoice(model)
            binding.diaryButton.setOnClickListener {
                binding.diaryButton.isChecked = diaryClickHandler(model)
            }
        }
    }
}