package ru.edamamlearning.graduationproject.ui.diary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.edamamlearning.graduationproject.databinding.ItemRecyclerBinding
import ru.edamamlearning.graduationproject.domain.model.DiaryFoodDomainModel
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.utils.extensions.loadPicture
import ru.edamamlearning.graduationproject.utils.extensions.toFoodDomainModel
import ru.edamamlearning.graduationproject.utils.roundAp

class DiaryFragmentAdapter(
    private val onItemClicked: (DiaryFoodDomainModel) -> Unit,
    private val isFavorite: (FoodDomainModel) -> Boolean,
    private val favouriteClickHandler: (FoodDomainModel) -> Boolean,
    private val deleteDiaryFood: (DiaryFoodDomainModel) -> Unit,
) : ListAdapter<DiaryFoodDomainModel, DiaryFragmentAdapter.DiaryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        return DiaryViewHolder(
            ItemRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(item)
        }
        holder.bind(item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<DiaryFoodDomainModel>() {

        override fun areItemsTheSame(oldItem: DiaryFoodDomainModel, newItem: DiaryFoodDomainModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: DiaryFoodDomainModel,
            newItem: DiaryFoodDomainModel
        ): Boolean {
            return oldItem.foodId == newItem.foodId
        }
    }

    inner class DiaryViewHolder(private val binding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: DiaryFoodDomainModel) {
            binding.label.text = model.label
            binding.category.text = model.category
            binding.proteinCount.text = roundAp(model.protein)
            binding.fatsCount.text = roundAp(model.fat)
            binding.carbohydratesCount.text = roundAp(model.carbohydrate)
            binding.foodImage.loadPicture(model.image)
            binding.favoriteButton.isChecked = isFavorite(model.toFoodDomainModel())
            binding.favoriteButton.setOnClickListener {
                binding.favoriteButton.isChecked = favouriteClickHandler(model.toFoodDomainModel())
            }
            binding.diaryButton.setOnClickListener {
                deleteDiaryFood(model)
            }
        }
    }
}