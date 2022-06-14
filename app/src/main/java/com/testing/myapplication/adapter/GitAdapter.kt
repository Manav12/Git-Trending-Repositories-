package com.testing.myapplication.adapter

import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.testing.myapplication.R
import com.testing.myapplication.apiResponseModal.Item

class GitAdapter(
    private val gitData: List<Item>?
) : RecyclerView.Adapter<GitAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var fullName: TextView
        var details: TextView
        var name: TextView
        var language: TextView
        var count: TextView
        var expandedView: ConstraintLayout
        var cardView: CardView


        init {
            name = itemView.findViewById(R.id.name)
            fullName = itemView.findViewById(R.id.full_name)
            details = itemView.findViewById(R.id.details)
            language = itemView.findViewById(R.id.language)
            count = itemView.findViewById(R.id.count)
            image = itemView.findViewById(R.id.profilePic)
            expandedView = itemView.findViewById(R.id.expandedView)
            cardView = itemView.findViewById(R.id.cardView)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitAdapter.ViewHolder {
        return GitAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GitAdapter.ViewHolder, position: Int) {

//        val imageURL = currentItem.url
        val imageURL =
            "https://as2.ftcdn.net/v2/jpg/03/64/21/11/1000_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg"
        holder.name.text = gitData?.get(position)?.name
        holder.fullName.text = gitData?.get(position)?.full_name
        holder.language.text = gitData?.get(position)?.language
        holder.details.text = gitData?.get(position)?.description
        holder.count.text = gitData?.get(position)?.watchers_count.toString()


        gitData?.get(position)?.name
        val circularProgressDrawable = CircularProgressDrawable(holder.itemView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.setColorFilter(
            ContextCompat.getColor(
                holder.itemView.context,
                R.color.lightBlue
            ), PorterDuff.Mode.SRC_IN
        )
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        Glide.with(holder.itemView)
            .load(gitData?.get(position)?.owner?.avatar_url)
            .transform(FitCenter())
            .placeholder(circularProgressDrawable)
            .into(holder.image)

        if (gitData?.get(position)?.isExapanded == true) {
            holder.expandedView.visibility = View.VISIBLE
        } else {
            holder.expandedView.visibility = View.GONE

        }
        holder.fullName.setOnClickListener {
            gitData?.get(position)?.isExapanded = !gitData?.get(position)?.isExapanded!!

            if (gitData.get(position).isExapanded == true) {
                holder.expandedView.visibility = View.VISIBLE
            } else {
                holder.expandedView.visibility = View.GONE

            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("Response", gitData!!.size.toString())
        return gitData.size
    }
}