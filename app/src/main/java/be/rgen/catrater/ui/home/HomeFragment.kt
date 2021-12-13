package be.rgen.catrater.ui.home

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import be.rgen.catrater.R
import be.rgen.catrater.api.CatApiResponse
import be.rgen.catrater.api.CatApiService
import be.rgen.catrater.databinding.FragmentHomeBinding
import be.rgen.catrater.ui.ImageSwitcherPicasso
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private var catService: CatApiService? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var bLike = view.findViewById<Button>(R.id.bLike);
        var bDislike = view.findViewById<Button>(R.id.bDislike);
        bLike.setOnClickListener{ refreshCatInImageSwitcher() }
        bDislike.setOnClickListener{ refreshCatInImageSwitcher() }

        //var imageSwitcher = binding.imageswitcher
        binding.imageswitcher?.setFactory({
            val imgView = ImageView(context)
            imgView.scaleType = ImageView.ScaleType.CENTER_CROP
            imgView.adjustViewBounds = true
            imgView
        })
        binding.imageswitcher.setInAnimation(context, android.R.anim.slide_in_left);
        binding.imageswitcher.setOutAnimation(context, android.R.anim.slide_out_right);

        homeViewModel.catPost.observe(viewLifecycleOwner, Observer {
            binding.lCatid.text = it.id
            if(it.url != "")
                Picasso.get().load(it.url).into(ImageSwitcherPicasso(requireContext(), binding.imageswitcher))
        })


        refreshCatInImageSwitcher()

    }

    fun refreshCatInImageSwitcher() {
        homeViewModel.getNextCat()
    }
}