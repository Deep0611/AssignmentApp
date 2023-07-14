package com.assignment.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.assignment.R
import com.assignment.data.model.DataModel

class MainAdapter(private val dataList: List<DataModel>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(iteView: View) : ViewHolder(iteView) {
        private var tvJoke: TextView = itemView.findViewById(R.id.tv_joke)

        fun bind(dataModel: DataModel) {
            tvJoke.text = dataModel.joke
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_data, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}