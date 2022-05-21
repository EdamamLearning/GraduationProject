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
import ru.edamamlearning.graduationproject.domain.model.fooddomainmodelinnerclasses.DomainHint

class SearchAdapter(
) : ListAdapter<FoodDomainModel, SearchAdapter.StartFragmentViewHolder>(ItemFsRvCallback) {

    private var domainData: List<DomainHint> = listOf()

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
        holder.bind(domainData[position])
    }

    override fun getItemCount(): Int {
        return domainData.size
    }

    fun setData(data: FoodDomainModel) {
        domainData = data.hints
        notifyDataSetChanged()
    }

    companion object ItemFsRvCallback : DiffUtil.ItemCallback<FoodDomainModel>() {

        override fun areItemsTheSame(oldItem: FoodDomainModel, newItem: FoodDomainModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FoodDomainModel, newItem: FoodDomainModel): Boolean {
            return oldItem == newItem
        }
    }

    inner class StartFragmentViewHolder(private val binding: ItemFsRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: DomainHint) {
            binding.label.text = model.food.label
            binding.category.text = model.food.category
            binding.proteinCount.text = model.food.nutrients.protein
            binding.fatsCount.text = model.food.nutrients.fat
            binding.carbohydratesCount.text = model.food.nutrients.carbohydrate
            loadPicture(model.food.image, binding)
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