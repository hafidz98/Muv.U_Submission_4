package id.kalibree.muvu.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.kalibree.muvu.R
import id.kalibree.muvu.adapter.CardViewAdapter
import id.kalibree.muvu.model.DataItem
import kotlinx.android.synthetic.main.movie_fragment.*

class MovieFragment : Fragment() {
    private val listMovies = ArrayList<DataItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listMovies.addAll(getListMovies())
        displayRecyclerCardView()
    }

    private fun getListMovies(): ArrayList<DataItem> {
        val movieTitle = resources.getStringArray(R.array.movie_tittle)
        val moviePoster = resources.obtainTypedArray(R.array.movie_poster)
        val movieDesc = resources.getStringArray(R.array.movie_desc)

        val listMovies = ArrayList<DataItem>()
        for (position in movieTitle.indices) {
            val movies = DataItem(
                moviePoster.getResourceId(position, -1),
                movieTitle[position],
                movieDesc[position]
            )
            listMovies.add(movies)
        }
        moviePoster.recycle()
        return listMovies
    }

    private fun displayRecyclerCardView() {
        movie_rv_fragment.layoutManager = LinearLayoutManager(activity)
        movie_rv_fragment.adapter = CardViewAdapter(listMovies)
        movie_rv_fragment.setHasFixedSize(true)
    }
}
