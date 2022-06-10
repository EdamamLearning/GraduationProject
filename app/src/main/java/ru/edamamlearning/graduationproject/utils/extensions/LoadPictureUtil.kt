package ru.edamamlearning.graduationproject.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.edamamlearning.graduationproject.R

fun ImageView.loadPicture(imageURL: String) {
    Glide.with(this)
        .load(imageURL)
        .error(R.drawable.food_no_photo)
        .placeholder(R.drawable.food)
        .into(this)
}