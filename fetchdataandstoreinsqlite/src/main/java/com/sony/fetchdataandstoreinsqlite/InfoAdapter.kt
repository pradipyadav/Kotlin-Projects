package com.sony.fetchdataandstoreinsqlite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.lo_info.view.*

class InfoAdapter(mCtx: Context, val infos: ArrayList<InfoData>) :
    RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    val mCtx = mCtx


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameInfo = itemView.name
        var userNameInfo = itemView.userName

        val emailInfo = itemView.email
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.lo_info, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return infos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info: InfoData = infos[position]
        holder.nameInfo.text = info.name
        holder.userNameInfo.text = info.username
        holder.emailInfo.text = info.email


    }
}