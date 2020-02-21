package id.kalibree.muvu.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import id.kalibree.muvu.model.DataItem
import org.json.JSONObject

class MovieViewModel : ViewModel() {
    private val listMovie = MutableLiveData<ArrayList<DataItem>>()
    private val apiKey = "494db4626a3536c56efdfc7f1dbf8de9"
    private var errorStatus = ""

    fun getMovies(): LiveData<ArrayList<DataItem>> {
        val listMovieItems = ArrayList<DataItem>()

        val url = "https://api.themoviedb.org/3/discover/movie?api_key=${apiKey}&language=en-US"

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
                        val movie = result.getJSONObject(i)
                        val movieItems = DataItem()

                        with(movieItems) {
                            id = movie.getInt("id")
                            title = movie.getString("original_title")
                            desc = movie.getString("overview")
                            poster = movie.getString("poster_path")
                            release_date = movie.getString("release_date")
                            score = movie.getDouble("vote_average")
                        }
                        listMovieItems.add(movieItems)
                    }

                    listMovie.postValue(listMovieItems)
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
                Log.d("Error getMovies() :", errorMessage)
                errorStatus = errorMessage
            }
        })
        return listMovie
    }
}
