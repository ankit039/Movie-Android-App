package com.example.practo_movie.helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.practo_movie.R
import com.example.practo_movie.models.MovieList_Model

class MovieListAdapter(private val moviesList: List<MovieList_Model> = emptyList()) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    //todo: use Kotlin HOC also
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    //creating ViewHolder Class
    class ViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        val movieimage = itemView.findViewById<ImageView>(R.id.movieimage)
        var movietitle = itemView.findViewById<TextView>(R.id.movietitle)
        var moviedate = itemView.findViewById<TextView>(R.id.moviedate)
        var moviedesc = itemView.findViewById<TextView>(R.id.moviedesc)

        fun bind(movielistModel: MovieList_Model) {
            Picasso.get()
                .load("https://www.themoviedb.org/t/p/w300_and_h450_bestv2" + movielistModel.poster_path)
                .into(movieimage)
            movietitle.text = movielistModel.original_title
            moviedate.text = movielistModel.release_date
            moviedesc.text = movielistModel.overview
        }

        // implementing onClickListner for perticular view
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Log.d("Response","List Movies :${moviesList.size}")
        //Log.d("Item", moviesList[position].toString())
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

}