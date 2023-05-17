package com.example.harrypotter.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nazanin.harrypotter.R
import com.nazanin.harrypotter.adapter.CharacterAdapter
import com.nazanin.harrypotter.domin.CharacterModel
import com.nazanin.harrypotter.domin.UserFilter
import com.nazanin.harrypotter.viewmodels.OverviewViewModel
import java.text.SimpleDateFormat
import java.util.Locale


@BindingAdapter("setImageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
        .into(imageView)
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CharacterModel>?) {
    val adapter = recyclerView.adapter as CharacterAdapter
    adapter.submitList(data)
}

@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}

@BindingAdapter("isWizard")
fun isWizard(textView: TextView, isWizard: Boolean){
    if (isWizard){
        View.VISIBLE
        textView.text = textView.context.getString(R.string.wizard)
    }else {
        View.GONE
    }

}

@BindingAdapter("bindHogwarts")
fun bindHogwarts(textView: TextView, characters: CharacterModel){
    if (characters.isStaff){
        textView.text = textView.context.getString(R.string.staff)
    }else if(characters.isStudent){
        textView.text = textView.context.getString(R.string.students)
    } else textView.text = "-"

}

@BindingAdapter("convertDateString")
fun convertDateString(textView: TextView, dateString: String) {
    if (dateString.length>0){
        val originalFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val targetFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val formattedDate = originalFormat.parse(dateString)?.let { targetFormat.format(it) }
        textView.text = formattedDate.toString()
    }else textView.text = "-"
}

@BindingAdapter("bindImageGender")
fun bindImageGender(imageView: ImageView, stringGender: String) {
    if (stringGender.equals("male")){
        imageView.setImageResource(R.drawable.ic_male)
    }else if (stringGender.equals("female")){
        imageView.setImageResource(R.drawable.ic_female)
    }
}

@BindingAdapter("bindAlive")
fun bindAlive(textView: TextView, isAlive: Boolean){
    if (isAlive){
        textView.text = textView.context.getString(R.string.yes)
    } else textView.text = textView.context.getString(R.string.no)

}
