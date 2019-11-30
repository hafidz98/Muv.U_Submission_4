package id.kalibree.muvu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MovieAdapter internal constructor(private val context: Context) : BaseAdapter(){
    internal var movies = arrayListOf<Movie>()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView = convertView
        if (itemView == null){
            itemView = LayoutInflater.from(context).inflate(R.layout.activity_movie_item, parent, false)
        }

        val viewHolder = ViewHolder(itemView as View)
        val movie = getItem(position) as Movie
        viewHolder.bind(movie)

        return itemView
    }

    private inner class ViewHolder internal constructor(view: View){
        private val movieTittle: TextView = view.findViewById(R.id.tv_movie_tittle)
        private val movieDesc: TextView = view.findViewById(R.id.tv_movie_desc)
        private val moviePoster: ImageView = view.findViewById(R.id.iv_movie_poster)

        internal fun bind(movie: Movie){
            movieTittle.text = movie.title
            movieDesc.text = movie.desc
            moviePoster.setImageResource(movie.poster)
        }
    }

    override fun getItem(position: Int): Any {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return movies.size
    }
}