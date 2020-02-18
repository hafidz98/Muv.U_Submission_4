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
import id.kalibree.muvu.ui.detail.ItemDetail
import kotlinx.android.synthetic.main.item_row_rv.view.*

class CardViewAdapter(private val listDataItem: ArrayList<DataItem>) :
    RecyclerView.Adapter<CardViewAdapter.CardViewViewHolder>() {

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataItem: DataItem) {
            with(itemView) {
                Glide.with(context)
                    .load(dataItem.poster)
                    .into(iv_item_poster)

                tv_item_tittle.text = dataItem.title
                tv_item_desc.text = dataItem.desc
                tv_item_date.text = resources.getString(R.string.dummy_date)
                tv_item_score.text = resources.getString(R.string.dumm_score)

                setOnClickListener {
                    showToast(dataItem.title, context)
                    val intentDetail = Intent(context, ItemDetail::class.java)
                    intentDetail.putExtra(ItemDetail.EXTRA_DATA, dataItem)
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
        return listDataItem.size
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listDataItem[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun showToast(msg: String?, context: Context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}