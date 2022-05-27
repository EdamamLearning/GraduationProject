package ru.edamamlearning.graduationproject.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.databinding.ItemSearchBinding
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.utils.roundAp

class FavoriteFragmentAdapter(
    private val isFavorite: (FoodDomainModel) -> Boolean,
    private val favouriteClickHandler: (FoodDomainModel) -> Boolean,
) : ListAdapter<FoodDomainModel, FavoriteFragmentAdapter.FavoriteViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
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

    inner class FavoriteViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: FoodDomainModel) {
            binding.label.text = model.label
            binding.category.text = model.category
            binding.proteinCount.text = roundAp(model.nutrients.protein)
            binding.fatsCount.text = roundAp(model.nutrients.fat)
            binding.carbohydratesCount.text = roundAp(model.nutrients.carbohydrate)
            loadPicture(model.image, binding)
            binding.favoriteButton.isChecked = isFavorite(model)
            binding.favoriteButton.setOnClickListener {
                binding.favoriteButton.isChecked = favouriteClickHandler(model)
            }
        }
    }

    private fun loadPicture(image: String?, binding: ItemSearchBinding) {
        if (image != null) {
            Glide.with(binding.root)
                .load(image)
                .error(R.drawable.food_no_photo)
                .placeholder(R.drawable.food)
                .into(binding.foodImage)
        }
    }
}