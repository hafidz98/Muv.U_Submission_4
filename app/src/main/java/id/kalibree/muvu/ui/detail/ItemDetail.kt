package id.kalibree.muvu.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.kalibree.muvu.R
import id.kalibree.muvu.model.DataItem
import kotlinx.android.synthetic.main.item_detail.*

class ItemDetail : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_detail)

        val getData = intent.getParcelableExtra(EXTRA_DATA) as DataItem

        val ivPoster: ImageView = findViewById(R.id.iv_poster_detail)
        val tvTittle: TextView = findViewById(R.id.tv_tittle_detail)
        val tvDesc: TextView = findViewById(R.id.tv_desc_detail)

        ivPoster.setImageResource(getData.poster)
        tvTittle.text = getData.title
        tvDesc.text = getData.desc

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
