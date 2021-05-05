package com.aspegrenide.dpoi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aspegrenide.dpoi.resources.PlannedInsats;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NarvaroListAdapter extends RecyclerView.Adapter<NarvaroListAdapter.ViewHolder> {

    private List<PlannedInsats> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    NarvaroListAdapter(Context context, List<PlannedInsats> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_narvaro , parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String kund = mData.get(position).getKund().getName();
        Date when = mData.get(position).getStart();
        String staff = mData.get(position).getStaffMember().getName();
        holder.txtNameKund.setText(kund);
        holder.txtWhen.setText(formatDate(when));
        holder.txtNameStaff.setText(staff);

        Date now = new Date();
        try {
            now = new SimpleDateFormat("HH:mm").parse("14:51");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        // if checked green
        if (mData.get(position).isChecked()) {
            holder.imgStatus.setImageResource(R.drawable.green);
        }else {

            // if not started yet - yellow
            if (when.after(now)) {
                holder.imgStatus.setImageResource(R.drawable.yellow);
            }

            // if starttime passed and not checked - red
            if (when.before(now)) {
                holder.imgStatus.setImageResource(R.drawable.red);
            }
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtNameKund;
        TextView txtWhen;
        TextView txtNameStaff;
        ImageView imgStatus;

        ViewHolder(View itemView) {
            super(itemView);
            txtNameKund = itemView.findViewById(R.id.txtNameKund);
            txtWhen = itemView.findViewById(R.id.txtStartTime);
            txtNameStaff = itemView.findViewById(R.id.txtNameStaff);
            imgStatus = itemView.findViewById(R.id.imgStatus);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    PlannedInsats getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public String formatDate(Date date) {
        String format = "HH:mm"; //24 hours format
        //hh:mm aa for 12 hours format
        DateFormat dateFormat = new SimpleDateFormat(format);
        return(dateFormat.format(date));
    }
}
