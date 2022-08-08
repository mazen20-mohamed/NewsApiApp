package com.ntgclarity.authenticator.words
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.example.Articles
import com.ntgclarity.authenticator.R

class NewsAdapter(var articles: Array<Articles>?,private val click:ItemClickListener) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener {
        val tvWord: TextView
        val ivPhoto: ImageView
        init
        {
            tvWord = view.findViewById(R.id.tv_word)
            ivPhoto = view.findViewById(R.id.iv_photo)
            view.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val v = adapterPosition
            if(v!=RecyclerView.NO_POSITION)
            {
                click.itemClick(v)
            }
        }
    }
    interface ItemClickListener {
        fun itemClick(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_word, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val word = articles?.get(position)
        holder.tvWord.text = word?.title

        Glide.with(holder.itemView)
            .load(word?.urlToImage)
            .into(holder.ivPhoto)
    }

    override fun getItemCount(): Int = articles?.size ?: 0
}