package be.rgen.catrater.api

import com.google.gson.annotations.SerializedName


class CatApiResponse {
    // example [{"breeds":[],"id":"Xwx3ARapc","url":"https://cdn2.thecatapi.com/images/Xwx3ARapc.jpg","width":3072,"height":2048}]
    @SerializedName("id")
    var id: String = ""
    @SerializedName("url")
    var url: String = ""
    @SerializedName("width")
    var width: Int = 0
    @SerializedName("height")
    var height: Int = 0


}
