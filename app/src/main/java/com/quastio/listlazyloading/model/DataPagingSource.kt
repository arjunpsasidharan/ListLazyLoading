package com.quastio.listlazyloading.model

import androidx.paging.PagingSource
import com.quastio.listlazyloading.restclient.RestClient

class DataPagingSource : PagingSource<Int, ResponseModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseModel> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPage = params.key ?: 0
            val response = RestClient.dataApiService.getDataList(page = nextPage)

            return LoadResult.Page(
                data = response,
                prevKey = if (nextPage == 0) null else nextPage - 6,
                nextKey = nextPage + 6
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}