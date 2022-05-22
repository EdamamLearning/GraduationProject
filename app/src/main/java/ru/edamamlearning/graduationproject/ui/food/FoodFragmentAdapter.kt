package ru.edamamlearning.graduationproject.ui.food

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.databinding.ItemFsRvBinding
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel

class FoodFragmentAdapter :
    ListAdapter<FoodDomainModel, FoodFragmentAdapter.StartFragmentViewHolder>(ItemFsRvCallback) {

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
        holder.show(domainData[position])
    }

    override fun getItemCount(): Int {
        return domainData.size
    }

    inner class StartFragmentViewHolder(private val vb: ItemFsRvBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun show(model: FoodDomainModel) {
            vb.label.text = model.label
            vb.category.text = model.category
            vb.proteinCount.text = model.nutrients.protein
            vb.fatsCount.text = model.nutrients.fat
            vb.carbohydratesCount.text = model.nutrients.carbohydrate
            loadPicture(model.image, vb)
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

    fun setData(data: List<FoodDomainModel>) {
        domainData = data
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
}