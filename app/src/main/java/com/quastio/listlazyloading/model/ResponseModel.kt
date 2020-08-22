package com.quastio.listlazyloading.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class ResponseModel(
    @SerializedName("dateTime")
    @Expose
    var dateTime: String? = null,

    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null,

    @SerializedName("fileSize")
    @Expose
    var fileSize: String? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null
)
