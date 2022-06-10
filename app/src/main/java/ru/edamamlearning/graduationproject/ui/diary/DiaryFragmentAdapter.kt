package ru.edamamlearning.graduationproject.ui.diary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.databinding.ItemDiaryBinding
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.utils.roundAp

class DiaryFragmentAdapter(
    private val isFoodChoice: (FoodDomainModel) -> Boolean,
    private val diaryClickHandler: (FoodDomainModel) -> Boolean,
) : ListAdapter<FoodDomainModel, DiaryFragmentAdapter.DiaryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        return DiaryViewHolder(
            ItemDiaryBinding.inflate(
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

    inner class DiaryViewHolder(private val binding: ItemDiaryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: FoodDomainModel) {
            binding.label.text = model.label
            binding.protein.text = roundAp(model.nutrients.protein)
            binding.fat.text = roundAp(model.nutrients.fat)
            binding.carbohydrate.text = roundAp(model.nutrients.carbohydrate)
            loadPicture(model.image, binding)
            binding.diaryButton.isChecked = isFoodChoice(model)
            binding.diaryButton.setOnClickListener {
                binding.diaryButton.isChecked = diaryClickHandler(model)
            }
        }
    }

    private fun loadPicture(image: String?, binding: ItemDiaryBinding) {
        if (image != null) {
            Glide.with(binding.root)
                .load(image)
                .error(R.drawable.food_no_photo)
                .placeholder(R.drawable.food)
                .into(binding.foodImage)
        }
    }
}