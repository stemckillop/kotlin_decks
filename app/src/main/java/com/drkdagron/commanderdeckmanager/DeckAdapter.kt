package com.drkdagron.commanderdeckmanager

import android.app.Activity
import android.app.FragmentManager
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.drkdagron.commanderdeckmanager.db.Deck

class DeckAdapter(var ctx: Context, var decks: List<Deck>) : BaseAdapter() {

    var fragmentManager: FragmentManager? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val vh: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.list_deck_card_2, parent, false)
            vh = ViewHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }

        vh.name.text = decks.get(position).name
        vh.info.text = decks.get(position).info
        when (decks.get(position).type) {
            0 -> {
                if (Build.VERSION.SDK_INT < 23) {
                    vh.format.setTextAppearance(this.ctx, R.style.CommanderTag)
                } else {
                    vh.format.setTextAppearance(R.style.CommanderTag)
                }
            }
            1 -> {
                if (Build.VERSION.SDK_INT < 23) {
                    vh.format.setTextAppearance(this.ctx, R.style.LegacyTag)
                } else {
                    vh.format.setTextAppearance(R.style.LegacyTag)
                }
            }
            2 -> {
                if (Build.VERSION.SDK_INT < 23) {
                    vh.format.setTextAppearance(this.ctx, R.style.ModernTag)
                } else {
                    vh.format.setTextAppearance(R.style.ModernTag)
                }
            }
            3 -> {
                if (Build.VERSION.SDK_INT < 23) {
                    vh.format.setTextAppearance(this.ctx, R.style.StandardTag)
                } else {
                    vh.format.setTextAppearance(R.style.StandardTag)
                }
            }
            4 -> {
                if (Build.VERSION.SDK_INT < 23) {
                    vh.format.setTextAppearance(this.ctx, R.style.Vintage)
                } else {
                    vh.format.setTextAppearance(R.style.Vintage)
                }
            }
        }

        if (decks.get(position).commander == "") {
            view!!.findViewById<TextView>(R.id.card_deck_commander_title).visibility = View.GONE
            vh.commander.visibility = View.GONE
        }

        for (t in decks.get(position).identity) {
            when (t) {
                'W' -> vh.tW.visibility = View.VISIBLE
                'U' -> vh.tU.visibility = View.VISIBLE
                'B' -> vh.tB.visibility = View.VISIBLE
                'R' -> vh.tR.visibility = View.VISIBLE
                'G' -> vh.tG.visibility = View.VISIBLE
                'A' -> vh.tA.visibility = View.VISIBLE
            }
        }

        vh.modify.setOnClickListener {
            var d = DeckCreateDialog()
            d.deck = decks.get(position)
            d.show(fragmentManager, "modify")
        }

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
        var info : TextView
        var format: TextView
        var commander: TextView

        var deckList: Button
        var history: Button
        var modify: ImageButton

        var tW: View
        var tU: View
        var tB: View
        var tR: View
        var tG: View
        var tA: View

        init {
            name = view.findViewById(R.id.card_deck_title)
            info = view.findViewById(R.id.card_deck_info)
            format = view.findViewById(R.id.card_type_tag)
            commander = view.findViewById(R.id.card_deck_commander)

            modify = view.findViewById(R.id.card_button_modify)
            history = view.findViewById(R.id.card_button_history)
            deckList = view.findViewById(R.id.card_button_list)

            tW = view.findViewById<View>(R.id.tW)
            tU = view.findViewById<View>(R.id.tU)
            tB = view.findViewById<View>(R.id.tB)
            tR = view.findViewById<View>(R.id.tR)
            tG = view.findViewById<View>(R.id.tG)
            tA = view.findViewById<View>(R.id.tA)
        }
    }

}