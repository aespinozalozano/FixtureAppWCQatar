package com.ademirespinoza.fixturewcqatar2022.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ademirespinoza.fixturewcqatar2022.R
import com.ademirespinoza.fixturewcqatar2022.models.FixtureData
import com.squareup.picasso.Picasso
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class FixtureAdapter(val context: Context, val list: List<FixtureData>) : RecyclerView.Adapter<FixtureViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureViewHolder {
        return FixtureViewHolder(LayoutInflater.from(context).inflate(R.layout.list_fixture, parent, false))
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onBindViewHolder(holder: FixtureViewHolder, position: Int) {
        val data = list[position]

        Picasso.get().load("flag" + toLowerCase()).into(holder.imagename_fa)
        Picasso.get().load("flag" + toLowerCase()).into(holder.imageView_away)

        holder.textView_home.text = data.homeName
        holder.textView_away.text = data.awayName as CharSequence?

        holder.textView_match.text = "${data.homeName} vs ${data.awayName}"

        val dateFormat = SimpleDateFormat("EEEE, d, MMM")
        val timeFormat = SimpleDateFormat("hh:mm a")

        val givenFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        try {
            val date = givenFormat.parse(data.date)
            holder.textView_time.text = "${dateFormat.format(date as Date)}\n${timeFormat.format(date)}"
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    override fun getItemCount() = list.size
}

private fun toLowerCase() {

}

class FixtureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imagename_fa: ImageView = itemView.findViewById(R.id.imagename_fa)
    val imageView_away: ImageView = itemView.findViewById(R.id.imageView_away)
    val textView_home: TextView = itemView.findViewById(R.id.textView_home)
    val textView_away: TextView = itemView.findViewById(R.id.textView_away)
    val textView_time: TextView = itemView.findViewById(R.id.textView_time)
    val textView_match: TextView = itemView.findViewById(R.id.textView_match)
}
