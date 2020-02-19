package id.kalibree.muvu

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_change_language) {
            val langIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(langIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}
