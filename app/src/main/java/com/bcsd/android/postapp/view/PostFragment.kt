package com.bcsd.android.postapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bcsd.android.postapp.mainviewmodel.MainViewModel
import com.bcsd.android.postapp.R
import com.bcsd.android.postapp.databinding.ActivityMainBinding
import com.bcsd.android.postapp.mainmodel.RecyclerViewItem

class PostFragment : Fragment() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val items = MutableLiveData<ArrayList<RecyclerViewItem>>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_fragment_main, null)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val viewModel = MainViewModel()
        binding.mainViewModel = viewModel
        binding.lifecycleOwner = this
        view.setContentView(binding.root)

        val recyclerViewItems: ArrayList<RecyclerViewItem> = ArrayList()


        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = RecyclerViewAdapter(viewModel.itemList)
        binding.recyclerView.adapter = adapter


        val dataObserver: Observer<ArrayList<RecyclerViewItem>> = Observer {
            items.value = it
            val adapter = RecyclerViewAdapter(items)
            binding.recyclerView.adapter = adapter
        }

        viewModel.itemList.observe(this, dataObserver)


    }

}