package id.kardihaekal.e_commerce_design

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController.ClickListener
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import id.kardihaekal.e_commerce_design.adapter.ItemGridAdapter
import id.kardihaekal.e_commerce_design.adapter.ProductAdapter
import id.kardihaekal.e_commerce_design.adapter.SliderImageAdapter
import id.kardihaekal.e_commerce_design.model.CategoryModel
import id.kardihaekal.e_commerce_design.model.ProductModel
import id.kardihaekal.e_commerce_design.util.Constant
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    /**
     *Lateinit digunakan ketika kita ingin membuat non-null type tapi
     *kita menginisiasikannya melalui dependency injection atau disebuah method setup dlm unit test
     * */
     lateinit var toolbar: Toolbar
    lateinit var sliderMyshop: SliderView
    var greetText: TextView? = null
    private lateinit var llroot: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        sliderMyshop = findViewById(R.id.imageSlider)
        greetText = findViewById(R.id.greeting_text)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        llroot = findViewById(R.id.mainLinearLayout1)
        loadjson(llroot, "Terlaris", 0, 25)
        for (cardtitle in Constant.cards.keys) {
            val card = layoutInflater.inflate(R.layout.item_card, null)
            val rv: RecyclerView = card.findViewById(R.id.cardListView1)
            rv.isNestedScrollingEnabled = false
            val tv = card.findViewById<TextView>(R.id.cardTextView1)
            tv.text = cardtitle
            val cats =
                Constant.cards[cardtitle]!!
            val datacat: MutableList<CategoryModel> =
                ArrayList()
            for (ic in cats.keys) {
                datacat.add(CategoryModel(ic, ic, cats[ic], false))
            }
            rv.addItemDecoration(SimpleDividerItemDecoration(this))
            rv.adapter = ItemGridAdapter(datacat)
            rv.layoutManager = GridLayoutManager(this, 2)
            llroot.addView(card)
        }
        loadjson(llroot, "Produk Terbaru", 26, 0)
        val sliderImageAdapter = SliderImageAdapter(this)
        sliderImageAdapter.count = 4
        sliderMyshop.setSliderAdapter(sliderImageAdapter)
        sliderMyshop.setIndicatorAnimation(IndicatorAnimations.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderMyshop.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderMyshop.setIndicatorSelectedColor(Color.WHITE)
        sliderMyshop.setIndicatorUnselectedColor(Color.GRAY)
        sliderMyshop.startAutoCycle()
        sliderMyshop.setOnIndicatorClickListener(ClickListener { position ->
            sliderMyshop.setCurrentPagePosition(
                position
            )
        })
        greeting()
    }

    private fun greeting() {
        val calendar = Calendar.getInstance()
        val timeOfDay = calendar[Calendar.HOUR_OF_DAY]
        if (timeOfDay >= 0 && timeOfDay < 12) {
            greetText!!.text = getString(R.string.salam_pagi)
        } else if (timeOfDay >= 12 && timeOfDay < 15) {
            greetText!!.text = getString(R.string.salam_siang)
        } else if (timeOfDay >= 15 && timeOfDay < 18) {
            greetText!!.text = getString(R.string.salam_sore)
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
            greetText!!.text = getString(R.string.salam_malam)
        }
    }

    private fun loadjson(
        root: LinearLayout?,
        title: String,
        startpos: Int,
        endpos: Int
    ) {
        try {
            val res = resources
            val in_s = res.openRawResource(R.raw.bldata)
            val b = ByteArray(in_s.available())
            in_s.read(b)
            val v =
                layoutInflater.inflate(R.layout.product_horizontal, null)
            val rv: RecyclerView = v.findViewById(R.id.producthorizontalRecyclerView1)
            val tv = v.findViewById<TextView>(R.id.producthorizontalTextView1)
            tv.text = title
            val pdata: MutableList<ProductModel> =
                ArrayList()
            val jdata = JSONObject(String(b)).getJSONArray("products")
            for (i in startpos until if (endpos == 0) jdata.length() else endpos) {
                val p = jdata.getJSONObject(i)
                val name = p.getString("name")
                val price = p.getLong("price")
                val oprice = price - 1000
                val store = p.getString("seller_name")
                val img = p.getJSONArray("images").getString(0)
                val rating =
                    (p.getJSONObject("rating").getString("average_rate") + "f").toFloat()
                pdata.add(ProductModel(name, store, price, oprice, img, rating, 10))
            }
            rv.adapter = ProductAdapter(pdata, this)
            rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
            rv.isNestedScrollingEnabled = false
            rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
            rv.scrollToPosition(rv.adapter!!.itemCount - 1)
            root!!.addView(v)
        } catch (e: Exception) {
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    inner class SimpleDividerItemDecoration(context: Context) :
        ItemDecoration() {
        private val mDivider: Drawable
        override fun onDrawOver(
            c: Canvas,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight
            val childCount = parent.childCount
            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)
                val params =
                    child.layoutParams as RecyclerView.LayoutParams
                val top = child.bottom + params.bottomMargin
                val bottom = top + mDivider.intrinsicHeight
                mDivider.setBounds(left, top, right, bottom)
                mDivider.draw(c)
            }
        }

        init {
            mDivider = context.resources.getDrawable(R.drawable.line_divider)
        }
    }

    companion object {
        fun setTint(d: Drawable?, color: Int): Drawable {
            val wrappedDrawable = DrawableCompat.wrap(d!!)
            DrawableCompat.setTint(wrappedDrawable, color)
            return wrappedDrawable
        }
    }
}