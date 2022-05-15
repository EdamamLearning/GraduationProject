package ru.edamamlearning.graduationproject.ui.startfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.databinding.ItemFsRvBinding
import ru.edamamlearning.graduationproject.domain.model.DomainModel

class StartFragmentAdapter :
    ListAdapter<DomainModel, StartFragmentAdapter.StartFragmentViewHolder>(ItemFsRvCallback) {

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
        holder.show(currentList[position])
    }

    inner class StartFragmentViewHolder(private val vb: ItemFsRvBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun show(model: DomainModel) {
            vb.infoType.text = model.text
            vb.foodImage.setImageResource(R.drawable.food)
        }
    }

    companion object ItemFsRvCallback : DiffUtil.ItemCallback<DomainModel>() {
        override fun areItemsTheSame(oldItem: DomainModel, newItem: DomainModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DomainModel, newItem: DomainModel): Boolean {
            return oldItem == newItem
        }
    }
}