package com.drkdagron.commanderdeckmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.drkdagron.commanderdeckmanager.db.Deck

class DeckAdapter(var ctx: Context, var decks: List<Deck>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val vh: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.list_deck_card, parent, false)
            vh = ViewHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }

        vh.name.text = decks.get(position).name

        return view!!
    }

    override fun getItem(position: Int): Any {
        return decks.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return decks.size
    }

    class ViewHolder(val view: View) {
        var name : TextView

        init {
            name = view.findViewById<TextView>(R.id.card_deck_title)
        }
    }

}