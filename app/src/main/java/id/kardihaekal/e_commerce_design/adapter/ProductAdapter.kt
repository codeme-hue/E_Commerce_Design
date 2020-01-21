package id.kardihaekal.e_commerce_design.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.loopj.android.image.SmartImageView
import id.kardihaekal.e_commerce_design.R
import id.kardihaekal.e_commerce_design.model.ProductModel
import java.text.NumberFormat
import java.util.*

class ProductAdapter(
    data: List<ProductModel>,
    ctx: Context
) : RecyclerView.Adapter<ProductAdapter.Holdr>() {
    var data: List<ProductModel> = ArrayList()
    private val mWidth: Float
    private val mHeight: Float
    override fun onCreateViewHolder(
        p1: ViewGroup,
        p2: Int
    ): Holdr {
        return Holdr(
            LayoutInflater.from(p1.context).inflate(
                R.layout.item_product,
                null
            )
        )
    }

    override fun onBindViewHolder(
        holdr: Holdr,
        pos: Int
    ) {
        val cat = data[pos]
        holdr.name.text = cat.name
        holdr.img.setImageUrl(cat.img)
        holdr.store.text = cat.store
        holdr.price.text = _priceFormat("" + cat.price)
        holdr.priceold.text = _priceFormat("" + cat.price_old)
        holdr.priceold.paintFlags = holdr.priceold.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        holdr.ratingbar.rating = cat.rating
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class Holdr(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView
        var price: TextView
        var priceold: TextView
        var discount: TextView
        var store: TextView
        var img: SmartImageView
        var ratingbar: RatingBar

        init {
            view.post {
                val lp = view.layoutParams
                lp.height = mHeight.toInt()
                lp.width = mWidth.toInt()
                view.layoutParams = lp
            }
            name = view.findViewById(R.id.itemproductTextViewName)
            price = view.findViewById(R.id.itemproductTextViewPrice)
            priceold = view.findViewById(R.id.itemproductTextViewPold)
            discount = view.findViewById(R.id.itemproductTextViewDisc)
            store = view.findViewById(R.id.itemproductTextViewStore)
            img = view.findViewById(R.id.itemproductImageView1)
            ratingbar = view.findViewById(R.id.itemproductRatingBar1)
        }
    }

    companion object {
        private fun _priceFormat(s: String): String {
            val parsed = s.toDouble()
            return NumberFormat.getCurrencyInstance().format(parsed)
        }
    }

    init {
        this.data = data
        val dm = ctx.resources.displayMetrics
        mWidth = dm.widthPixels / ctx.resources.getDimension(R.dimen.grid_width)
        mHeight = dm.heightPixels / ctx.resources.getDimension(R.dimen.grid_height)
    }
}