package com.example.socceralmanac.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.socceralmanac.R
import com.example.socceralmanac.models.search.EventItem
import com.example.socceralmanac.utility.getStringDate
import kotlinx.android.synthetic.main.item_match.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class SearchMatchAdapter(private val data: List<EventItem?>?, private val clicked: OnClickItem) :
    RecyclerView.Adapter<SearchMatchAdapter.SearchMatchHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMatchHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return SearchMatchHolder(view)
    }


    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: SearchMatchHolder, position: Int) {
        val item = data?.get(position)

        val formatDateEvent: String = item?.dateEvent?.let { getStringDate(it) } ?: "-"

        holder.dateTime.text = formatDateEvent
        holder.homeTeam.text = item?.strHomeTeam
        holder.awayTeam.text = item?.strAwayTeam
        holder.scoreHomeTeam.text = item?.intHomeScore as CharSequence? ?: "?"
        holder.scoreAwayTeam.text = item?.intAwayScore as CharSequence? ?: "?"

        holder.itemView.onClick {
            clicked.searchMatchClick(item)
        }
    }

    inner class SearchMatchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTime = itemView.tv_date_match
        val homeTeam = itemView.tv_home_team
        val awayTeam = itemView.tv_away_team
        val scoreHomeTeam = itemView.tv_teamHome_score
        val scoreAwayTeam = itemView.tv_teamAway_score

    }

    interface OnClickItem {
        fun searchMatchClick(item: EventItem?)
    }
}