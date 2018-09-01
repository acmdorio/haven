package org.havenapp.main.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.havenapp.main.R;
import org.havenapp.main.model.Event;
import org.havenapp.main.resources.IResourceManager;

import java.util.List;

/**
 * Created by n8fr8 on 4/16/17.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventVH> {

    private List<Event> events;
    private IResourceManager resourceManager;

    private OnItemClickListener clickListener;

    public EventAdapter(List<Event> events, @NonNull IResourceManager resourceManager) {
        this.events = events;
        this.resourceManager = resourceManager;
    }


    @Override
    public EventVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventVH holder, int position) {

        Event event = events.get(position);

        String title = event.getMStartTime().toLocaleString();
        String desc = event.getEventTriggers().size() + " " +
                resourceManager.getString(R.string.detection_events);

        holder.title.setText(title);
        holder.note.setText(desc);

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, note;

        public EventVH(View itemView) {
            super(itemView);

           title = itemView.findViewById(R.id.event_item_title);
            note = itemView.findViewById(R.id.event_item_desc);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
