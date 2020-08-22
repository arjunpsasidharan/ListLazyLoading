package com.quastio.listlazyloading.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.quastio.listlazyloading.R
import com.quastio.listlazyloading.model.ResponseModel
import com.quastio.listlazyloading.utils.DataDiffUtil
import com.quastio.listlazyloading.utils.DateAndTimeUtils

class DataRecyclerAdapter(private val interaction: Interaction? = null) :
    PagingDataAdapter<ResponseModel,RecyclerView.ViewHolder>(DataDiffUtil.DIFF_CALLBACK) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.response_list_item,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DataViewHolder -> {
                getItem(position)?.let { holder.bind(it) }
            }
        }
    }


    class DataViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {
        private val imageView:ImageView=itemView.findViewById(R.id.image_view)
        private val statusImageView:ImageView=itemView.findViewById(R.id.status_iv)
        private val dateTextView:TextView=itemView.findViewById(R.id.date_tv)
        private val timeTv:TextView=itemView.findViewById(R.id.time_tv);
        private val sizeTv:TextView=itemView.findViewById(R.id.size_tv);

        fun bind(item: ResponseModel) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(layoutPosition, item)
            }
            Glide.with(itemView.context)
                .load(item.thumbnail)
                .into(imageView)

            item.dateTime?.let {
                val date=DateAndTimeUtils.getDate(it)
                dateTextView.text=date
                val time=DateAndTimeUtils.getTime(it)
                timeTv.text="@ $time"
            }
            item.status?.let {
                when(it){
                    STATUS_DOWNLOADED->{
                    statusImageView.setImageResource(R.drawable.ic_status_downloaded)
                    }
                    STATUS_UPLOADED->{
                        statusImageView.setImageResource(R.drawable.ic_status_uploaded)

                    }
                    else->{
                        statusImageView.setImageResource(R.drawable.ic_status_none)
                    }
                }
            }

            item.fileSize?.let {
                sizeTv.text=it
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: ResponseModel)
    }
    companion object{
        private const val STATUS_NONE="STATUS_NONE"
        private const val STATUS_DOWNLOADED="STATUS_DOWNLOADED"
        private const val STATUS_UPLOADED="STATUS_UPLOADED"
    }
}

