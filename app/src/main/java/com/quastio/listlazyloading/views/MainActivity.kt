package com.quastio.listlazyloading.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quastio.listlazyloading.R
import com.quastio.listlazyloading.adapters.DataRecyclerAdapter
import com.quastio.listlazyloading.model.ResponseModel
import com.quastio.listlazyloading.viewmodels.DataViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), DataRecyclerAdapter.Interaction {
    private lateinit var dataAdapter: DataRecyclerAdapter
    private lateinit var dataViewModel: DataViewModel
    private lateinit var gridLayoutManager: GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        data_recycler.layoutManager = gridLayoutManager
        dataAdapter = DataRecyclerAdapter(this)
        data_recycler.adapter = dataAdapter
        data_recycler.autoFitColumns(150)

        dataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        lifecycleScope.launch {
            dataViewModel.dataList.collect {
                if (this@MainActivity::dataAdapter.isInitialized) {
                    dataAdapter.submitData(it)
                }
            }
        }
    }

    override fun onItemSelected(position: Int, item: ResponseModel) {
        item.id?.let { Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show() }
    }

    fun RecyclerView.autoFitColumns(columnWidth: Int) {
        val displayMetrics = this.context.resources.displayMetrics
        val noOfColumns =
            ((displayMetrics.widthPixels / displayMetrics.density) / columnWidth).toInt()
        this.layoutManager = GridLayoutManager(this.context, noOfColumns)
    }
}