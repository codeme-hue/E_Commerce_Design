package id.kardihaekal.e_commerce_design.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import id.kardihaekal.e_commerce_design.R
import id.kardihaekal.e_commerce_design.adapter.SliderImageAdapter.SliderAdapterVH


class SliderImageAdapter(private val context: Context) :
    SliderViewAdapter<SliderAdapterVH>() {
    private var mCount = 0
    fun setCount(count: Int) {
        mCount = count
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.image_slider_myshop, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        viewHolder.itemView.setOnClickListener {
            when (position) {
                0 -> {
                    val browserIntent_1 = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/AzharRivaldi")
                    )
                    context.startActivity(browserIntent_1)
                }
                1 -> {
                    val browserIntent_2 = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/AzharRivaldi")
                    )
                    context.startActivity(browserIntent_2)
                }
                2 -> {
                    val browserIntent_3 = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/AzharRivaldi")
                    )
                    context.startActivity(browserIntent_3)
                }
                3 -> {
                    val browserIntent_4 = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/AzharRivaldi")
                    )
                    context.startActivity(browserIntent_4)
                }
            }
        }
        when (position) {
            0 -> Glide.with(viewHolder.itemView)
                .load("https://laz-img-cdn.alicdn.com/images/ims-web/TB1pHADjBr0gK0jSZFnXXbRRXXa.jpg_1200x1200.jpg")
                .fitCenter()
                .into(viewHolder.imageViewBackground)
            1 -> Glide.with(viewHolder.itemView)
                .load("https://laz-img-cdn.alicdn.com/images/ims-web/TB1m8REjrj1gK0jSZFuXXcrHpXa.jpg_1200x1200Q100.jpg")
                .fitCenter()
                .into(viewHolder.imageViewBackground)
            2 -> Glide.with(viewHolder.itemView)
                .load("https://laz-img-cdn.alicdn.com/images/ims-web/TB1Hhz4jBr0gK0jSZFnXXbRRXXa.jpg_1200x1200Q100.jpg")
                .fitCenter()
                .into(viewHolder.imageViewBackground)
            3 -> Glide.with(viewHolder.itemView)
                .load("https://laz-img-cdn.alicdn.com/images/ims-web/TB1m8REjrj1gK0jSZFuXXcrHpXa.jpg_1200x1200Q100.jpg")
                .fitCenter()
                .into(viewHolder.imageViewBackground)
        }
    }

    override fun getCount(): Int { //slider view count could be dynamic size
        return mCount
    }

    inner class SliderAdapterVH(itemView: View) :
        ViewHolder(itemView) {
        var itemView: View
        var imageViewBackground: ImageView
        var imageGifContainer: ImageView
        var textViewDescription: TextView

        init {
            imageViewBackground =
                itemView.findViewById(R.id.iv_auto_image_slider)
            imageGifContainer =
                itemView.findViewById(R.id.iv_gif_container)
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider)
            this.itemView = itemView
        }
    }

}