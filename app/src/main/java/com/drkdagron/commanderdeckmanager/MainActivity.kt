package com.drkdagron.commanderdeckmanager

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.drkdagron.commanderdeckmanager.db.DB

class MainActivity : AppCompatActivity() {

    lateinit var pres : MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pres = MainPresenter(this)
        pres.getDeckList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        var id = item!!.itemId
        if (id == R.id.menu_deck_add) {
            Toast.makeText(this, "Hello...", Toast.LENGTH_SHORT).show()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
