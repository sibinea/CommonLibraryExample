package com.sibin.commonlib

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
class JavaViewHelper {
    companion object {

        @JvmStatic
        fun isViewVisible(view: View): Boolean {
            return view.isVisible()
        }
        @JvmStatic
        fun setVisible(view: View) = view.visible()
        @JvmStatic
        fun setInVisible(view: View) = view.invisible()
        @JvmStatic
        fun setGone(view: View) = view.gone()
        @JvmStatic
        fun loadImageFromUrl(
            imageView: ImageView, urlString: String,
            placeholder: Int?,
            error: Int?,
            thumbnail: Float?,
            transition: DrawableTransitionOptions?,
            downSampleStrategy: DownsampleStrategy?,
            diskCacheStrategy: DiskCacheStrategy?,
            transformation: List<Transformation<Bitmap>>?,
            onLoadingCompleted: (Boolean) -> (Unit) = {}
        ) {
            imageView.loadFromUrl(
                urlString,
                placeholder = placeholder ?: -1,
                error = error ?: DEFAULT_PLACEHOLDER,
                transformation = transformation ?: DEFAULT_TRANSFORMATION,
                thumbnail = thumbnail ?: DEFAULT_THUMBNAIL,
                transition = transition ?: DEFAULT_TRANSITION,
                downSampleStrategy = downSampleStrategy ?: DEFAULT_DWON_SAMPLE_STRATEGY,
                diskCacheStrategy = diskCacheStrategy ?: DEFAULT_DISK_CACHE_STRATEGY,
                onLoadingCompleted = onLoadingCompleted
            )
        }
    }

}