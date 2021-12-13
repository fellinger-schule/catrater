package be.rgen.catrater.ui
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception
import android.widget.ImageSwitcher




class ImageSwitcherPicasso: Target  {
    private var imageSwitcher: ImageSwitcher? = null
    private var context: Context? = null

    constructor(context: Context, imageSwitcher: ImageSwitcher) {
        this.imageSwitcher = imageSwitcher
        this.context = context
    }


    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        if(bitmap != null && this.imageSwitcher != null) {
            var bitmapDrawable = BitmapDrawable(context!!.resources, bitmap)
            this.imageSwitcher!!.setImageDrawable(bitmapDrawable)
        }
    }

    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        //TODO("Not yet implemented")
    }

    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        if(placeHolderDrawable != null)
            imageSwitcher!!.setImageDrawable(placeHolderDrawable)
    }

}