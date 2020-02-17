package id.kalibree.muvu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.kalibree.muvu.R
import id.kalibree.muvu.model.DataItem
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
}