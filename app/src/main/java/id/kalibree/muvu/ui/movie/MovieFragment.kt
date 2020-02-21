package id.kalibree.muvu.ui.movie

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
import kotlinx.android.synthetic.main.movie_fragment.*

class MovieFragment : Fragment() {
    private lateinit var cardViewAdapter: CardViewAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardViewAdapter = CardViewAdapter()
        cardViewAdapter.notifyDataSetChanged()

        movie_rv_fragment.layoutManager = LinearLayoutManager(this.context)
        movie_rv_fragment.adapter = cardViewAdapter

        movieViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MovieViewModel::class.java)

        showLoading(true)
        movieViewModel.getMovies().observe(this.viewLifecycleOwner, Observer {
            if (it != null) {
                cardViewAdapter.setData(it)
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            movie_progress_bar.visibility = View.VISIBLE
        } else {
            movie_progress_bar.visibility = View.GONE
        }
    }
}
