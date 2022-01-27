package com.bcsd.android.postapp.mainviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bcsd.android.postapp.mainmodel.RecyclerViewItem

class MainViewModel: ViewModel() {

    private val _itemList = MutableLiveData<ArrayList<RecyclerViewItem>>()

    val itemList: LiveData<ArrayList<RecyclerViewItem>> get() = _itemList

    init {
        val recyclerViewItems: ArrayList<RecyclerViewItem> = ArrayList()
        //여기에 ArrayList(RecyclerViewitem)+ 버튼 커스텀 로직 추가
        recyclerViewItems.add(RecyclerViewItem(1, "title1", "subtitle1","",))
        recyclerViewItems.add(RecyclerViewItem(2, "title2", "subtitle2",""))
        recyclerViewItems.add(RecyclerViewItem(3, "title3", "subtitle3",""))
        recyclerViewItems.add(RecyclerViewItem(4, "title4", "subtitle4",""))
        recyclerViewItems.add(RecyclerViewItem(5, "title5", "subtitle5",""))

        _itemList.value = recyclerViewItems
    }

    fun setItemList(recyclerViewItems: ArrayList<RecyclerViewItem>) {
        _itemList.value = recyclerViewItems
    }

}