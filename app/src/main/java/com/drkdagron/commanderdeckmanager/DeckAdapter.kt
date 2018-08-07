package com.drkdagron.commanderdeckmanager

import android.app.FragmentManager
import android.content.Context
import android.media.Image
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.RenderProcessGoneDetail
import android.widget.*
import com.drkdagron.commanderdeckmanager.db.decks.Deck

class DeckAdapter(var ctx: Context, var decks: List<Deck>) : BaseAdapter() {

    var fragmentManager: FragmentManager? = null
    lateinit var pres: MainPresenter

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

        vh.format_edh.visibility = View.GONE
        vh.format_leg.visibility = View.GONE
        vh.format_mdn.visibility = View.GONE
        vh.format_std.visibility = View.GONE
        vh.format_vin.visibility = View.GONE
        when (decks.get(position).type) {
            0 -> {
                vh.format_edh.visibility = View.VISIBLE
            }
            1 -> {
                vh.format_leg.visibility = View.VISIBLE
            }
            2 -> {
                vh.format_mdn.visibility = View.VISIBLE
            }
            3 -> {
                vh.format_std.visibility = View.VISIBLE
            }
            4 -> {
                vh.format_vin.visibility = View.VISIBLE
            }
        }

        if (decks.get(position).commander == "") {
            view!!.findViewById<TextView>(R.id.card_deck_commander_title).visibility = View.GONE
            vh.commander.visibility = View.GONE
        } else {
            vh.commander.setText(decks.get(position).commander)
            view!!.findViewById<TextView>(R.id.card_deck_commander_title).visibility = View.VISIBLE
            vh.commander.visibility = View.VISIBLE
        }

        if (decks.get(position).info == "") {
            view!!.findViewById<TextView>(R.id.card_deck_info_title).visibility = View.GONE
            vh.info.visibility = View.GONE
        } else {
            vh.info.text = decks.get(position).info
            view!!.findViewById<TextView>(R.id.card_deck_info_title).visibility = View.VISIBLE
            vh.info.visibility = View.VISIBLE
        }

        vh.prevResult.visibility = View.VISIBLE
        when (decks.get(position).last) {
            0 -> vh.prevResult.visibility = View.GONE
            1 -> vh.prevResult.setImageResource(R.drawable.first_place)
            2 -> vh.prevResult.setImageResource(R.drawable.second_place)
            3 -> vh.prevResult.setImageResource(R.drawable.third_place)
            4 -> vh.prevResult.setImageResource(R.drawable.bad_place)
        }

        vh.gameCounter.text = decks.get(position).games.toString()

        vh.tW.visibility = View.GONE
        vh.tU.visibility = View.GONE
        vh.tB.visibility = View.GONE
        vh.tR.visibility = View.GONE
        vh.tG.visibility = View.GONE
        vh.tA.visibility = View.GONE
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
            pres.modifyDeckDialog(decks.get(position))
        }
        vh.historyAdd.setOnClickListener {
            pres.displayGameHistoryDialog(decks.get(position))
        }
        vh.history.setOnClickListener {
            pres.displayGameHistory()
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
        var format_edh: TextView
        var format_std: TextView
        var format_vin: TextView
        var format_mdn: TextView
        var format_leg: TextView
        var commander: TextView
        var prevResult: ImageView
        var gameCounter: TextView

        var deckList: Button
        var history: Button
        var historyAdd: ImageButton
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
            format_edh = view.findViewById(R.id.card_commander_tag)
            format_std = view.findViewById(R.id.card_standard_tag)
            format_vin = view.findViewById(R.id.card_vintage_tag)
            format_mdn = view.findViewById(R.id.card_modern_tag)
            format_leg = view.findViewById(R.id.card_legacy_tag)
            commander = view.findViewById(R.id.card_deck_commander)
            prevResult = view.findViewById(R.id.card_prev_result)
            gameCounter = view.findViewById(R.id.card_game_counter)

            modify = view.findViewById(R.id.card_button_modify)
            history = view.findViewById(R.id.card_button_history)
            historyAdd = view.findViewById(R.id.card_button_add_history)
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