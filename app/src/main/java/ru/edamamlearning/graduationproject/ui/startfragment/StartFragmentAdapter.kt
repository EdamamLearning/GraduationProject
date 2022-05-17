package ru.edamamlearning.graduationproject.ui.startfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.databinding.ItemFsRvBinding
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel

class StartFragmentAdapter :
    ListAdapter<FoodDomainModel, StartFragmentAdapter.StartFragmentViewHolder>(ItemFsRvCallback) {

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
        holder.show(domainData[position])
    }

    override fun getItemCount(): Int {
        return domainData.size
    }

    inner class StartFragmentViewHolder(private val vb: ItemFsRvBinding) :
        RecyclerView.ViewHolder(vb.root) {


        fun show(model: FoodDomainModel) {
            vb.foodName.text = model.hints.first().food.label
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