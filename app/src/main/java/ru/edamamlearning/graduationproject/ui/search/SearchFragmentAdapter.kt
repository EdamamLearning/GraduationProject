package ru.edamamlearning.graduationproject.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.databinding.ItemFsRvBinding
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel

class SearchAdapter(
) : ListAdapter<FoodDomainModel, SearchAdapter.StartFragmentViewHolder>(ItemFsRvCallback) {

    private var domainData: List<FoodDomainModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartFragmentViewHolder {
        return StartFragmentViewHolder(
            ItemFsRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StartFragmentViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object ItemFsRvCallback : DiffUtil.ItemCallback<FoodDomainModel>() {

        override fun areItemsTheSame(oldItem: FoodDomainModel, newItem: FoodDomainModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FoodDomainModel, newItem: FoodDomainModel): Boolean {
            return oldItem.foodId == newItem.foodId
        }
    }

    inner class StartFragmentViewHolder(private val binding: ItemFsRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: FoodDomainModel) {
            binding.label.text = model.label
            binding.category.text = model.category
            binding.proteinCount.text = model.nutrients.protein
            binding.fatsCount.text = model.nutrients.fat
            binding.carbohydratesCount.text = model.nutrients.carbohydrate
            loadPicture(model.image, binding)
        }
    }

    private fun loadPicture(image: String?, binding: ItemFsRvBinding) {
        if (image != null) {
            Glide.with(binding.root)
                .load(image)
                .error(R.drawable.ic_no_picture)
                .placeholder(R.drawable.food)
                .into(binding.foodImage)
        }
    }
}