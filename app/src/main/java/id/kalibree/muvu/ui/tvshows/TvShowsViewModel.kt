package id.kalibree.muvu.ui.tvshows

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import id.kalibree.muvu.model.DataItem
import org.json.JSONObject

class TvShowsViewModel : ViewModel() {
    private val listTvShows = MutableLiveData<ArrayList<DataItem>>()
    private val apiKey = "494db4626a3536c56efdfc7f1dbf8de9"
    private var errorStatus = ""

    fun getTvShows(): LiveData<ArrayList<DataItem>> {
        val listTvShowsItems = ArrayList<DataItem>()

        val url = "https://api.themoviedb.org/3/discover/tv?api_key=${apiKey}&language=en-US"

        val client = AsyncHttpClient()
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val response = String(responseBody)
                    val responseObject = JSONObject(response)
                    val result = responseObject.getJSONArray("results")

                    for (i in 0 until result.length()) {
                        val tvShows = result.getJSONObject(i)
                        val tvShowsItems = DataItem()

                        with(tvShowsItems) {
                            id = tvShows.getInt("id")
                            title = tvShows.getString("original_name")
                            desc = tvShows.getString("overview")
                            poster = tvShows.getString("poster_path")
                            release_date = tvShows.getString("first_air_date")
                            score = tvShows.getDouble("vote_average")
                        }
                        listTvShowsItems.add(tvShowsItems)
                    }

                    listTvShows.postValue(listTvShowsItems)
                } catch (e: Exception) {
                    Log.d("Exception getMovies() :", e.message.toString())
                    errorStatus = e.message.toString()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Log.d("Error getTvShows() ", errorMessage)
                errorStatus = errorMessage
            }
        })
        return listTvShows
    }
}
