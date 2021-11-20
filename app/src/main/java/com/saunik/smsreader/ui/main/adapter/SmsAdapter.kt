package com.saunik.smsreader.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saunik.smsreader.R
import com.saunik.smsreader.data.model.Sms
import com.saunik.smsreader.databinding.ItemLayoutBinding

/**
 * Created by Saunik Singh on 20/11/21.
 */
class SmsAdapter(
    private val users: ArrayList<Sms>
) : RecyclerView.Adapter<SmsAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sms: Sms) {
            binding.amount.text = binding.amount.context.getString(R.string.amount, sms.amount)
            sms.time?.apply {
                binding.date.text = toString()
            }
            binding.message.text = sms.message
            binding.textAddress.text = sms.address
        }
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<Sms>) {
        users.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(inflater, parent, false)
        return DataViewHolder(binding)
    }
}