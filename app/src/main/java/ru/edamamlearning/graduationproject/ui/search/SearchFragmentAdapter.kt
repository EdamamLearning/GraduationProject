package ru.edamamlearning.graduationproject.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.edamamlearning.graduationproject.databinding.ItemSearchBinding
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.utils.extensions.loadPicture
import ru.edamamlearning.graduationproject.utils.roundAp

class SearchAdapter(
    private val onFavouriteItemClicked: (FoodDomainModel) -> Unit,
    private val diaryClickHandler: (FoodDomainModel) -> Boolean,
    private val isFavorite: (FoodDomainModel) -> Boolean,
    private val isFoodChoice: (FoodDomainModel) -> Boolean,
    private val favouriteClickHandler: (FoodDomainModel) -> Boolean,
) : ListAdapter<FoodDomainModel, SearchAdapter.SearchViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onFavouriteItemClicked(item)
        }
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

    inner class SearchViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: FoodDomainModel) {
            binding.label.text = model.label
            binding.category.text = model.category
            binding.proteinCount.text = roundAp(model.nutrients.protein)
            binding.fatsCount.text = roundAp(model.nutrients.fat)
            binding.carbohydratesCount.text = roundAp(model.nutrients.carbohydrate)
            binding.foodImage.loadPicture(model.image)
            binding.favoriteButton.isChecked = isFavorite(model)
            binding.favoriteButton.setOnClickListener {
                binding.favoriteButton.isChecked = favouriteClickHandler(model)
            }
            binding.diaryButton.isChecked = isFoodChoice(model)
            binding.diaryButton.setOnClickListener {
                binding.diaryButton.isChecked = diaryClickHandler(model)
            }
        }
    }
}