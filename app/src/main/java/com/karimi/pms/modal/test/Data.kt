package com.karimi.pms.modal.test

import com.google.gson.annotations.SerializedName

class Data {
    var ok: Boolean? = null
    @SerializedName("result")
    var result: ArrayList<Post>? = null
}