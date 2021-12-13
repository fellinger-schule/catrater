package be.rgen.catrater.ui.home

import android.widget.ImageSwitcher
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.rgen.catrater.R
import be.rgen.catrater.api.CatApiResponse
import be.rgen.catrater.api.CatApiService
import be.rgen.catrater.models.CatPost
import be.rgen.catrater.ui.ImageSwitcherPicasso
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel : ViewModel() {
    private val _catService = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CatApiService::class.java)

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    private val _catPost = MutableLiveData<CatPost>().apply {
        value = CatPost("", "", 0,0)
    }

    private val _favorites = MutableLiveData<MutableList<CatPost>>().apply {
        value = mutableListOf<CatPost>()
    }

    fun addToFavorites(post: CatPost): Boolean? {
        return _favorites.value?.add(post)
    }

    fun getNextCat() {
        var call = _catService!!.getRandomCat().enqueue(object : Callback<List<CatApiResponse>> {
            override fun onResponse(
                call: Call<List<CatApiResponse>>,
                response: Response<List<CatApiResponse>>
            ) {
                if (response.code() == 200) {
                    val catResponse = response.body()!!

                    if(!catResponse.isEmpty()) {
                        var id = catResponse.get(0).id
                        var url = catResponse.get(0).url
                        var width = catResponse.get(0).width
                        var height = catResponse.get(0).height

                        var newPost: CatPost = CatPost(id, url, width, height)
                        _catPost.value = newPost

                        //var imageSwitcher = view.findViewById<ImageSwitcher>(R.id.imageswitcher)
                        //Picasso.get().load(url).into(ImageSwitcherPicasso(context!!, imageSwitcher))
                    }
                }
            }

            override fun onFailure(call: Call<List<CatApiResponse>>, t: Throwable) {
                //TODO("Not yet implemented")
            }
        })
    }

    val text: LiveData<String> = _text
    val catPost: LiveData<CatPost> = _catPost
}