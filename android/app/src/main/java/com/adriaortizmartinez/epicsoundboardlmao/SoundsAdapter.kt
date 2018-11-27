package com.adriaortizmartinez.epicsoundboardlmao

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.row_sound.view.*

class SoundsAdapter(var soundList: ArrayList<SoundModel>) : RecyclerView.Adapter<SoundsAdapter.SoundViewHolder>() {

    public var onSoundClickListener: OnItemClickListener? = null

    override fun getItemCount(): Int {
        return soundList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_sound, parent, false)
        return SoundViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: SoundViewHolder, position: Int) {
        viewHolder.soundTitle.text = soundList[position].title

        viewHolder.soundButton.setOnClickListener{
            onSoundClickListener?.onItemClick(soundList[position], position)
        }
    }

    class SoundViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var soundButton: Button = itemView.soundButton
        var soundTitle: TextView = itemView.soundTitle
    }
}