package ru.edamamlearning.graduationproject.ui.startfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.databinding.ItemFsRvBinding
import ru.edamamlearning.graduationproject.domain.model.DomainModel
import ru.edamamlearning.graduationproject.domain.model.domainmodelinnerclasses.DomainHint

class StartFragmentAdapter :
    ListAdapter<DomainModel, StartFragmentAdapter.StartFragmentViewHolder>(ItemFsRvCallback) {

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

        fun show(model: DomainHint) {
            vb.foodName.text = model.food.label
            vb.call.text = model.food.nutrients.energyKCal
            vb.infoTypeView.text = model.food.foodContentsLabel
            vb.infoType.text = model.food.categoryLabel
            loadPicture(model.food.image, vb)
        }
    }

    private fun loadPicture(image: String?, binding: ItemFsRvBinding) {
        if (image != null) {
            Glide.with(binding.root)
                .load(image)
                .error(R.drawable.ic_no_picture)
                .placeholder(R.drawable.default_picture)
                .into(binding.foodImage)
        }
    }

    fun setData(data: DomainModel) {
        domainData = data.hints
        notifyDataSetChanged()
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