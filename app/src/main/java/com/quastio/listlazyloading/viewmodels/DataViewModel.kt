package com.quastio.listlazyloading.viewmodels

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.quastio.listlazyloading.model.DataPagingSource
import com.quastio.listlazyloading.model.ResponseModel
import kotlinx.coroutines.flow.Flow

class DataViewModel:ViewModel() {
    val dataList: Flow<PagingData<ResponseModel>> = Pager(PagingConfig(pageSize = 6)) {
        DataPagingSource()
    }.flow

}