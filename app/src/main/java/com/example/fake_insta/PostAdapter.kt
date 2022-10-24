package com.example.fake_insta

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class PostAdapter(val context: Context, val posts: ArrayList<Post>)
    : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        // Specify the layout file to use for this item

        val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        val post = posts.get(position)
        holder.bind(post)
    }
    override fun getItemCount(): Int {
        return posts.size
    }

    fun clear() {
        posts.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(postList: List<Post>) {
        posts.addAll(postList)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUsername: TextView
        val ivImage: ImageView
        val ivPic: ImageView
        val ivPic2: ImageView
        val tvDescription: TextView
        val tvPosted: TextView

        init {
            tvUsername = itemView.findViewById(R.id.tvUsername)
            ivImage = itemView.findViewById(R.id.ivImage)
            tvPosted = itemView.findViewById(R.id.posted)
            ivPic = itemView.findViewById(R.id.ivProfileImage)
            ivPic2 = itemView.findViewById(R.id.ivProfileImage2)
            tvDescription = itemView.findViewById(R.id.tvDescription)
        }

        fun bind(post: Post) {
            tvDescription.text = post.getDescription()
            tvUsername.text = post.getUser()?.username
            tvPosted.text = post.getPosted()
            Glide.with(itemView.context).load(post.getpImage()?.url).into(ivPic)
            Glide.with(itemView.context).load(post.getpImage()?.url).into(ivPic2)

            Glide.with(itemView.context).load(post.getImage()?.url).into(ivImage)

            // Populate image
        }
    }
}