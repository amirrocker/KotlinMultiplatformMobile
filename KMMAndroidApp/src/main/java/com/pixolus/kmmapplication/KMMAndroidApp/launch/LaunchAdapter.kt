package com.pixolus.kmmapplication.KMMAndroidApp.launch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pixolus.kmmapplication.KMMAndroidApp.R
import com.pixolus.kmmapplication.shared.entity.RocketLaunch

class LaunchAdapter(
    var launches : List<RocketLaunch>
) : RecyclerView.Adapter<LaunchAdapter.LaunchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_launch, parent, false)
            .run(::LaunchViewHolder)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bindData(launches[position])
    }

    override fun getItemCount(): Int = launches.count()

    inner class LaunchViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

        private val missionNameTextField = itemView.findViewById<TextView>(R.id.missionTv)

        fun bindData(launch:RocketLaunch) {
            val ctx = itemView.context
            missionNameTextField.text = ctx.getString(R.string.mission_name_field, launch.missionName )

            launch.launchSuccess?.let {
                if(it) {
                    println("Success launch .... change style accordingly")
                } else {
                    println("Failed launch .... change style accordingly")
                }
            }
        }
    }


}