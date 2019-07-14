package com.vitovalov.kino.data.local.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ShowLo(
    @PrimaryKey
    var id: Int = 0,
    var page: Int = 0,
    var backdropPath: String = "",
    var name: String = "",
    var voteAverage: Double = 0.0
) : RealmObject()