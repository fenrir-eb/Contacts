package com.example.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContanctHolder> {

    private List<Contact> currentContactList;

    public class ContanctHolder extends RecyclerView.ViewHolder {

        private ImageView   contactImage;
        private TextView    contactName;
        private TextView    contactData;

        public ContanctHolder(@NonNull View itemView) {
            super(itemView);

            contactImage = itemView.findViewById(R.id.iv_grid_item);
            contactName = itemView.findViewById(R.id.tv_grid_item);
            contactData = itemView.findViewById(R.id.tv_data_grid_item);
        }
    }

    public ContactAdapter(){
        currentContactList = new ArrayList<>();
    }

    public void setData(List<Contact> contacts){
        ContactDiffUtilCallback callback = new ContactDiffUtilCallback(currentContactList, contacts);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        currentContactList.clear();
        currentContactList.addAll(contacts);
        result.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public ContanctHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item,parent,false);
        return new ContanctHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContanctHolder holder, int position) {
        Contact contact = currentContactList.get(position);
        Picasso.get().load(contact.getImgUrl()).into(holder.contactImage);
        holder.contactName.setText(contact.getName());
        holder.contactData.setText(contact.getData());
    }

    @Override
    public int getItemCount() {
        return currentContactList.size();
    }

}
