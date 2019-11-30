package id.kalibree.muvu

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MovieAdapter
    private lateinit var movieTitle: Array<String>
    private lateinit var movieDesc: Array<String>
    private lateinit var moviePoster: TypedArray
    private var movies = arrayListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val listView: ListView = findViewById(R.id.lv_movie)
        val listView: ListView = lv_movie
        adapter = MovieAdapter(this)
        listView.adapter = adapter

        prepare()
        addItem()

        listView.onItemClickListener = AdapterView.OnItemClickListener{_, _, position, _ ->
            val movieDetailIntent = Intent(this@MainActivity, MovieDetail::class.java)
            movieDetailIntent.putExtra(MovieDetail.EXTRA_MOVIE, movies[position])
            startActivity(movieDetailIntent)
        }
    }

    private fun prepare(){
        movieTitle = resources.getStringArray(R.array.movie_tittle)
        movieDesc = resources.getStringArray(R.array.movie_desc)
        moviePoster = resources.obtainTypedArray(R.array.movie_poster)
    }

    private fun addItem(){
        for (positon in movieTitle.indices){
            val movie = Movie(
                moviePoster.getResourceId(positon, -1),
                movieTitle[positon],
                movieDesc[positon]
            )
            movies.add(movie)
        }
        adapter.movies = movies
    }
}
