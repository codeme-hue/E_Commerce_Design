package id.kardihaekal.e_commerce_design.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.kardihaekal.e_commerce_design.R
import id.kardihaekal.e_commerce_design.model.CategoryModel

class ItemGridAdapter(var data: List<CategoryModel>) :
    RecyclerView.Adapter<ItemGridAdapter.Holdr>() {
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        p1: ViewGroup,
        p2: Int
    ): Holdr {
        return Holdr(
            LayoutInflater.from(p1.context).inflate(
                R.layout.item_grid,
                null
            )
        )
    }

    override fun onBindViewHolder(
        holdr: Holdr,
        pos: Int
    ) {
        val cat = data[pos]
        holdr.title.text = cat.title
        holdr.ic_cat.setImageResource(cat.icres!!)
        if (pos % 2 == 1) {
            holdr.divider.visibility = View.GONE
        } else holdr.divider.visibility = View.VISIBLE
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class Holdr(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var ic_cat: ImageView
        var divider: View

        init {
            title = view.findViewById(R.id.itemcardTextView1)
            ic_cat = view.findViewById(R.id.itemcardImageView1)
            divider = view.findViewById(R.id.itemcardView1)
        }
    }

}