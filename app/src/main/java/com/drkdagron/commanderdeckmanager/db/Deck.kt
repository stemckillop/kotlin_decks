package com.drkdagron.commanderdeckmanager.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName="deckData")
data class Deck(@PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name="name") var name: String,
        @ColumnInfo(name="identity") var identity: String,
        @ColumnInfo(name="info") var info: String,
        @ColumnInfo(name="type") var type: Int,
        @ColumnInfo(name="active") var active: Int,
        @ColumnInfo(name="link") var link: String,
                @ColumnInfo(name="commander") var commander: String) {
    constructor(): this(null, "", "", "", 0, 0, "", "")
}