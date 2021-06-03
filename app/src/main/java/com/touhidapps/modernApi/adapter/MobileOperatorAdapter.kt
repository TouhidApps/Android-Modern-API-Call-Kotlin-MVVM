package com.touhidapps.modernApi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.touhidapps.modernApi.databinding.RowFlowerBinding
import com.touhidapps.modernApi.model.ItemsModel

class MobileOperatorAdapter(private var items: List<ItemsModel>) : RecyclerView.Adapter<MobileOperatorAdapter.MyViewHolder>() {

    var onItemClick: ((ItemsModel) -> Unit)? = null

    fun setItemClick(action: (ItemsModel) -> Unit) {
        this.onItemClick = action
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowFlowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = items[position]

        holder.itemBinding.tvTitle.text = item.details

        holder.itemBinding.ivThumb.load("${item.baseUrl}/${item.fileName}")

    } // onBindViewHolder

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyViewHolder(@NonNull val itemBinding: RowFlowerBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        init {

            onItemClick?.let {
                itemBinding.root.setOnClickListener {
                    it(items[adapterPosition])
                }
            }

//        itemBinding.imageView.setOnClickListener {  }
//        itemBinding.root.setOnClickListener {
//            println("Clicked item: $adapterPosition")
//        }

        }
    }

}
