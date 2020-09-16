package com.sibin.commonlib

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


val DEFAULT_PLACEHOLDER = R.drawable.default_placeholder

val DEFAULT_TRANSITION = DrawableTransitionOptions.withCrossFade()
val DEFAULT_DWON_SAMPLE_STRATEGY: DownsampleStrategy = DownsampleStrategy.DEFAULT
val DEFAULT_DISK_CACHE_STRATEGY: DiskCacheStrategy = DiskCacheStrategy.AUTOMATIC
const val DEFAULT_THUMBNAIL = 0.1f

val DEFAULT_TRANSFORMATION = listOf(
    RoundedCornersTransformation(
        0,
        0,
        RoundedCornersTransformation.CornerType.ALL
    ),
    FitCenter()
)

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

///Available bitmapTransformation
//CropTransformation
//CropCircleTransformation
//CropCircleWithBorderTransformation
//CropSquareTransformation
//RoundedCornersTransformation

fun ImageView.loadFromUrl(
    url: String,
    thumbnail: Float = DEFAULT_THUMBNAIL,
    placeholder: Int = -1,
    error: Int = DEFAULT_PLACEHOLDER,
    transition: DrawableTransitionOptions = DEFAULT_TRANSITION,
    downSampleStrategy: DownsampleStrategy = DEFAULT_DWON_SAMPLE_STRATEGY,
    diskCacheStrategy: DiskCacheStrategy = DEFAULT_DISK_CACHE_STRATEGY,
    transformation: List<Transformation<Bitmap>> = DEFAULT_TRANSFORMATION,
    onLoadingCompleted: (Boolean) -> (Unit) = {}
) {
    val multi = MultiTransformation(
        transformation
    )


    Glide.with(this.context.applicationContext)
        .load(url)
        .apply(RequestOptions.bitmapTransform(multi))
        .thumbnail(thumbnail)
        .placeholder(placeholder)
        .diskCacheStrategy(diskCacheStrategy)
        .error(error)
        .transition(transition)
        .downsample(downSampleStrategy)
        .addListener(requestListener(onLoadingCompleted))
        .into(this)
}


private fun requestListener(onLoadingCompleted: (Boolean) -> Unit): RequestListener<Drawable> {
    return object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadingCompleted(false)
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadingCompleted(true)
            return false
        }
    }
}