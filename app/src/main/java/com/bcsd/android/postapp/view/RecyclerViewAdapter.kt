package com.bcsd.android.postapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bcsd.android.postapp.mainmodel.RecyclerViewItem
import com.bcsd.android.postapp.databinding.ItemRecyclerviewBinding

class RecyclerViewAdapter(private var items: LiveData<ArrayList<RecyclerViewItem>>): RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder>() {

    var recyclerViewItems = ArrayList<RecyclerViewItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        items.value?.get(position)?.let {
            holder.bind(it)
            println(it.title)
        }
    }

    override fun getItemCount(): Int {
        return items.value?.size!!
    }

    fun setData(newRecyclerViewItems: ArrayList<RecyclerViewItem>){
        val diffCallback = DiffCallback(recyclerViewItems, newRecyclerViewItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        recyclerViewItems.clear()
        recyclerViewItems.addAll(newRecyclerViewItems)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class RecyclerViewViewHolder(private val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(recyclerViewItem: RecyclerViewItem) = with(binding) {
            item = recyclerViewItem
        }

    }

    inner class DiffCallback(
        private var oldList: ArrayList<RecyclerViewItem>,
        private var newList: ArrayList<RecyclerViewItem>
    ): DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

    }


}