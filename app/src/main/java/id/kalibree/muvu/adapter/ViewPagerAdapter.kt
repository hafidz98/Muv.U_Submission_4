package id.kalibree.muvu.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.kalibree.muvu.R
import id.kalibree.muvu.ui.movie.MovieFragment
import id.kalibree.muvu.ui.tvshows.TvShowsFragment

class ViewPagerAdapter(private val mContext: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TAB_LAYOUT_TITLES = intArrayOf(
        R.string.main_tab_tittle_movies,
        R.string.main_tab_tittle_tvshows
    )

    private val mainTabPages = listOf(
        MovieFragment(),
        TvShowsFragment()
    )

    override fun getItem(position: Int): Fragment {
        return mainTabPages[position]
    }

    override fun getCount(): Int {
        return mainTabPages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_LAYOUT_TITLES[position])
    }

}