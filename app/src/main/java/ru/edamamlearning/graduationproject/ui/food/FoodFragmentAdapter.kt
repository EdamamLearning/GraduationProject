package ru.edamamlearning.graduationproject.ui.food

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.databinding.ItemHistoryBinding
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel

class FoodFragmentAdapter(
    private val onHistoryItemClicked: (FoodDomainModel) -> Unit
) :
    ListAdapter<FoodDomainModel, FoodFragmentAdapter.StartFragmentViewHolder>(ItemFsRvCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartFragmentViewHolder {
        return StartFragmentViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StartFragmentViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onHistoryItemClicked(item)
        }
        holder.show(item)
    }

    inner class StartFragmentViewHolder(private val vb: ItemHistoryBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun show(model: FoodDomainModel) {
            vb.label.text = model.label
            vb.category.text = model.category
//            vb.proteinCount.text = model.nutrients.protein
//            vb.fatsCount.text = model.nutrients.fat
//            vb.carbohydratesCount.text = model.nutrients.carbohydrate
            loadPicture(model.image, vb)
        }
    }

    private fun loadPicture(image: String?, binding: ItemHistoryBinding) {
        if (image != null) {
            Glide.with(binding.root)
                .load(image)
                .error(R.drawable.food_no_photo)
                .placeholder(R.drawable.food)
                .into(binding.foodImage)
        }
    }

    companion object ItemFsRvCallback : DiffUtil.ItemCallback<FoodDomainModel>() {

        override fun areItemsTheSame(oldItem: FoodDomainModel, newItem: FoodDomainModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FoodDomainModel, newItem: FoodDomainModel): Boolean {
            return oldItem == newItem
        }
    }
}