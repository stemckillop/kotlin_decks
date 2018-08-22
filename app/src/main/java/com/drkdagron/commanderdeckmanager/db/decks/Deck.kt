package com.drkdagron.commanderdeckmanager.db.decks

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="deckData")
data class Deck(@PrimaryKey(autoGenerate = true) var id: Long?,
            @ColumnInfo(name="name") var name: String,
            @ColumnInfo(name="identity") var identity: String,
            @ColumnInfo(name="info") var info: String,
            @ColumnInfo(name="type") var type: Int,
            @ColumnInfo(name="active") var active: Int,
            @ColumnInfo(name="link") var link: String,
                @ColumnInfo(name="games") var games: Int,
                @ColumnInfo(name="last") var last: Int,
                @ColumnInfo(name="commander") var commander: String) : Serializable {
    constructor(): this(null, "", "", "", 0, 0, "", 0, 0, "")
}