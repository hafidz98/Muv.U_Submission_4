package id.kalibree.muvu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.kalibree.muvu.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayoutAdapter = ViewPagerAdapter(this, supportFragmentManager)
        main_view_pager.adapter = tabLayoutAdapter
        main_tab_layout.setupWithViewPager(main_view_pager)

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


}
