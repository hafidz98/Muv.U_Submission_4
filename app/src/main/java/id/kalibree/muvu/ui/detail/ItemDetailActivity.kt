package id.kalibree.muvu.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.kalibree.muvu.R
import id.kalibree.muvu.model.DataItem
import kotlinx.android.synthetic.main.activity_item_detail.*

class ItemDetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val getData = intent.getParcelableExtra(EXTRA_DATA) as DataItem

        val imgSize = "w185"
        val imgUrl = "https://image.tmdb.org/t/p/"

        Glide.with(this).load(imgUrl + imgSize + getData.poster).into(iv_poster_detail)
        tv_title_detail.text = getData.title
        tv_desc_detail.text = getData.desc
        tv_date_detail.text = getData.release_date
        tv_score_detail.text = getData.score.toString()


        supportActionBar?.title = "Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_item_favorite.setOnClickListener {
            Toast.makeText(this, "Favorited", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
