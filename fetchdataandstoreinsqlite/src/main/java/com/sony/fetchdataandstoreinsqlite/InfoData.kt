package com.sony.fetchdataandstoreinsqlite

import com.google.gson.annotations.SerializedName

data class InfoData (
    var id:Int=0,
    var name: String="",
    var username: String="",
    var email: String=""
)