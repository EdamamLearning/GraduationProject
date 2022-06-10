package ru.edamamlearning.graduationproject.ui.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.databinding.FragmentInfoBinding
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.utils.roundAp

/**
 * @author Borisov Andrey on 05.06.2022
 **/
class InfoFragmentAdapter(
    private val onFavouriteItemClicked: (FoodDomainModel) -> Unit,
    private val isAFoodChoise: (FoodDomainModel) -> Boolean,
    private val infoClickHandler: (FoodDomainModel) -> Boolean,
) : ListAdapter<FoodDomainModel, InfoFragmentAdapter.InfoViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        return InfoViewHolder(
            FragmentInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
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
            newItem: FoodDomainModel,
        ): Boolean {
            return oldItem.foodId == newItem.foodId
        }
    }

    inner class InfoViewHolder(private val binding: FragmentInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: FoodDomainModel) {
            binding.categoryLabel.text = model.categoryLabel
            binding.category.text = model.category
            binding.proteinCount.text = roundAp(model.nutrients.protein)
            binding.fatsCount.text = roundAp(model.nutrients.fat)
            binding.carbohydrateCount.text = roundAp(model.nutrients.carbohydrate)
            loadPicture(model.image, binding)
            binding.infoButton.isChecked = isAFoodChoise(model)
            binding.infoButton.setOnClickListener {
                binding.infoButton.isChecked = infoClickHandler(model)
            }
        }
    }

    private fun loadPicture(image: String?, binding: FragmentInfoBinding) {
        if (image != null) {
            Glide.with(binding.root)
                .load(image)
                .error(R.drawable.food_no_photo)
                .placeholder(R.drawable.food)
                .into(binding.foodImage)
        }
    }
}