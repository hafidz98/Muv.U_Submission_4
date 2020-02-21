package id.kalibree.muvu.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.kalibree.muvu.R
import id.kalibree.muvu.model.DataItem
import id.kalibree.muvu.ui.detail.ItemDetailActivity
import kotlinx.android.synthetic.main.item_row_rv.view.*

class CardViewAdapter :
    RecyclerView.Adapter<CardViewAdapter.CardViewViewHolder>() {

    private val mData = ArrayList<DataItem>()

    fun setData(items: ArrayList<DataItem>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataItem: DataItem) {
            with(itemView) {
                val imgSize = "w185"
                val imgUrl = "https://image.tmdb.org/t/p/"

                Glide.with(context)
                    .load(imgUrl + imgSize + dataItem.poster)
                    .into(iv_item_poster)

                tv_item_tittle.text = dataItem.title
                tv_item_desc.text = dataItem.desc
                tv_item_date.text = dataItem.release_date
                tv_score_detail.text = dataItem.score.toString()

                setOnClickListener {
                    showToast(dataItem.title, context)
                    val intentDetail = Intent(context, ItemDetailActivity::class.java)
                    intentDetail.putExtra(ItemDetailActivity.EXTRA_DATA, dataItem)
                    context.startActivity(intentDetail)
                }

                btn_item_fav.setOnClickListener {
                    showToast("Favorited!", context)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val viewInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_rv, parent, false)
        return CardViewViewHolder(viewInflater)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun showToast(msg: String?, context: Context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}