package ru.edamamlearning.graduationproject.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.edamamlearning.graduationproject.R

fun ImageView.loadPicture(imageURL: String) {
    Glide.with(this)
        .load(imageURL)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(28)))
        .error(R.drawable.food_no_photo)
        .placeholder(R.drawable.food)
        .into(this)
}