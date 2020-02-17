package id.kalibree.muvu

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import id.kalibree.muvu.model.DataItem

class MovieDetail : AppCompatActivity() {

    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie = intent.getParcelableExtra(EXTRA_MOVIE) as DataItem

        val ivPoster: ImageView = findViewById(R.id.iv_movie_poster_detail)
        val tvTittle: TextView = findViewById(R.id.tv_movie_tittle_detail)
        val tvDesc: TextView = findViewById(R.id.tv_movie_desc_detail)

        //ivPoster.setImageResource(movie.poster.toInt())
        tvTittle.text = movie.title
        tvDesc.text = movie.desc

        supportActionBar?.title = "Movie Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
