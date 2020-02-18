package id.kalibree.muvu.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.kalibree.muvu.R
import id.kalibree.muvu.adapter.CardViewAdapter
import id.kalibree.muvu.model.DataItem
import kotlinx.android.synthetic.main.tv_shows_fragment.*

class TvShowsFragment : Fragment() {
    private val listTvShows = ArrayList<DataItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tv_shows_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listTvShows.addAll(getListTvShows())
        displayRecyclerCardView()
    }

    private fun getListTvShows(): ArrayList<DataItem> {
        val tvShowsTitle = resources.getStringArray(R.array.tvshow_tittle)
        val tvShowsPoster = resources.obtainTypedArray(R.array.tvshows_poster)
        val tvShowsDesc = resources.getStringArray(R.array.tvshows_desc)

        val listTvShows = ArrayList<DataItem>()
        for (position in tvShowsTitle.indices) {
            val tvShows = DataItem(
                tvShowsPoster.getResourceId(position, -1),
                tvShowsTitle[position],
                tvShowsDesc[position]
            )
            listTvShows.add(tvShows)
        }
        tvShowsPoster.recycle()
        return listTvShows
    }

    private fun displayRecyclerCardView() {
        tvshows_rv_fragment.layoutManager = LinearLayoutManager(activity)
        tvshows_rv_fragment.adapter = CardViewAdapter(listTvShows)
        tvshows_rv_fragment.setHasFixedSize(true)
    }

}
