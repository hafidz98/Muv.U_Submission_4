package id.kalibree.muvu.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.kalibree.muvu.R
import id.kalibree.muvu.adapter.CardViewAdapter
import kotlinx.android.synthetic.main.tv_shows_fragment.*

class TvShowsFragment : Fragment() {
    private lateinit var cardViewAdapter: CardViewAdapter
    private lateinit var tvShowsViewModel: TvShowsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tv_shows_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardViewAdapter = CardViewAdapter()
        cardViewAdapter.notifyDataSetChanged()

        tvshows_rv_fragment.layoutManager = LinearLayoutManager(this.context)
        tvshows_rv_fragment.adapter = cardViewAdapter

        tvShowsViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(TvShowsViewModel::class.java)

        showLoading(true)
        tvShowsViewModel.getTvShows().observe(this.viewLifecycleOwner, Observer {
            if (it != null) {
                cardViewAdapter.setData(it)
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            tvshows_progress_bar.visibility = View.VISIBLE
        } else {
            tvshows_progress_bar.visibility = View.GONE
        }
    }
}
