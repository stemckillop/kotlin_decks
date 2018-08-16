package com.drkdagron.commanderdeckmanager.db.games

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName="gameData")
data class Game(@PrimaryKey(autoGenerate = true) var id: Long?,
                @ColumnInfo(name="timestamp") var time: Long,
                @ColumnInfo(name="place") var place: Int,
                @ColumnInfo(name="notes") var notes: String,
                @ColumnInfo(name="deckID") var deckID: Long) {
    constructor() : this(null, 0, 0, "", 0)
}