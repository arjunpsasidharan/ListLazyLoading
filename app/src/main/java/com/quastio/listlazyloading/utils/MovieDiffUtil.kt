package com.quastio.listlazyloading.utils

import androidx.recyclerview.widget.DiffUtil
import com.quastio.listlazyloading.model.ResponseModel

object DataDiffUtil {
    val DIFF_CALLBACK: DiffUtil.ItemCallback<ResponseModel> by lazy {
        object : DiffUtil.ItemCallback<ResponseModel>() {
            override fun areItemsTheSame(oldItem: ResponseModel, newItem: ResponseModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ResponseModel, newItem: ResponseModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}