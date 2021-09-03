package com.slabcode.agutierrezt.example2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.slabcode.agutierrezt.example2.data.models.Product
import com.slabcode.agutierrezt.example2.databinding.ItemProductBinding
import com.slabcode.agutierrezt.example2.ui.listeners.OnProductListener

class ProductAdapter(var items: List<Product>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    var listener: OnProductListener? = null

    class ViewHolder(val item: ItemProductBinding): RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.item.itemProductName.text = item.name
        holder.item.itemProductPrice.text = item.price
        Glide.with(holder.itemView).load(item.image).into(holder.item.itemProductPhoto)
        holder.item.root.setOnClickListener {
            listener?.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun newDataSet(products: List<Product>) {
        items = products
        notifyDataSetChanged()
    }

}