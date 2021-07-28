package com.unsplashapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unsplashapp.R
import com.unsplashapp.extensions.ctx
import com.squareup.picasso.Picasso
import com.unsplashapp.domain.model.PhotosDataResult
import com.unsplashapp.domain.model.PhotosResult
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_photo.*

class PhotosListAdapter(private val photos: PhotosDataResult,
                        private val itemClick: (PhotosResult) -> Unit):
    RecyclerView.Adapter<PhotosListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_photo, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindPhoto(photos[position])
    }

    override fun getItemCount() = photos.size

    class ViewHolder(override val containerView: View, private val itemClick: (PhotosResult) -> Unit)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindPhoto(photo: PhotosResult) {
            with(photo) {
                Picasso.with(itemView.ctx).load(urls.small).into(icon)
                photoAuthorText.text = itemView.ctx.getString(R.string.photoAuthor, user.name)
                photoUserNameText.text = itemView.ctx.getString(R.string.photoUserName, user.username)
                photoLikesText.text = itemView.ctx.getString(R.string.photoLikes, likes.toString())
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}