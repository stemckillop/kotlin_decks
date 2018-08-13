package com.drkdagron.commanderdeckmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.drkdagron.commanderdeckmanager.db.games.Game

class HistoryAdapter(val ctx: Context, val games: List<Game>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val vh: HistoryAdapter.ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.list_game_history, parent, false)
            vh = HistoryAdapter.ViewHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as HistoryAdapter.ViewHolder
        }

        vh.dateLabel.text = games[position].time.toString()

        vh.placeImage.visibility = View.GONE
        when (games[position].place) {
            1 -> {
                vh.placeImage.setImageResource(R.drawable.first_place)
                vh.placeImage.visibility = View.VISIBLE
            }
            2 -> {
                vh.placeImage.setImageResource(R.drawable.second_place)
                vh.placeImage.visibility = View.VISIBLE
            }
            3 -> {
                vh.placeImage.setImageResource(R.drawable.third_place)
                vh.placeImage.visibility = View.VISIBLE
            }
            4 -> {
                vh.placeImage.setImageResource(R.drawable.bad_place)
                vh.placeImage.visibility = View.VISIBLE
            }
        }

        return view!!
    }

    override fun getItem(p0: Int): Any {
        return games[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return games.size
    }

    class ViewHolder(val view: View) {

        var dateLabel: TextView
        var placeImage: ImageView

        init {
            dateLabel = view.findViewById(R.id.item_game_date)
            placeImage = view.findViewById(R.id.item_place_image)
        }
    }

}