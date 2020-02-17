package id.kalibree.muvu

import android.content.res.TypedArray
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.kalibree.muvu.adapter.TabLayoutAdapter
import id.kalibree.muvu.model.DataItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieTitle: Array<String>
    private lateinit var movieDesc: Array<String>
    private lateinit var moviePoster: TypedArray
    private var movies = arrayListOf<DataItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayoutAdapter = TabLayoutAdapter(
            this,
            supportFragmentManager
        )
        main_view_pager.adapter = tabLayoutAdapter
        main_tab_lyt.setupWithViewPager(main_view_pager)

        supportActionBar?.elevation = 0F

        //val listView: ListView = findViewById(R.id.lv_movie)
        /*
        val listView: ListView = lv_movie
        adapter = MovieAdapter(this)
        listView.adapter = adapter

        prepare()
        addItem()

        listView.onItemClickListener = AdapterView.OnItemClickListener{_, _, position, _ ->
            val movieDetailIntent = Intent(this@MainActivity, MovieDetail::class.java)
            movieDetailIntent.putExtra(MovieDetail.EXTRA_MOVIE, movies[position])
            startActivity(movieDetailIntent)
        }*/
    }

    private fun prepare(){
        movieTitle = resources.getStringArray(R.array.movie_tittle)
        movieDesc = resources.getStringArray(R.array.movie_desc)
        moviePoster = resources.obtainTypedArray(R.array.movie_poster)
    }
/*
    private fun addItem(){
        for (positon in movieTitle.indices){
            val movie = DataItem(
                moviePoster.getResourceId(positon, -1),
                movieTitle[positon],
                movieDesc[positon]
            )
            movies.add(movie)
        }
        adapter.movies = movies
    }

 */
}
