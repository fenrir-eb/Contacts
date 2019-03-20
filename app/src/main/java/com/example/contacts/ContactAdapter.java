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
import androidx.recyclerview.widget.RecyclerView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContanctHolder> {

    private List<Contact> currentContactList;
    private List<Contact> fullContactList;

    public class ContanctHolder extends RecyclerView.ViewHolder {

        private ImageView   contactImage;
        private TextView    contactName;
//        private TextView    contactData;

        public ContanctHolder(@NonNull View itemView) {
            super(itemView);

            contactName = itemView.findViewById(R.id.tv_grid_item);
            contactImage = itemView.findViewById(R.id.iv_grid_item);
        }
    }

    public ContactAdapter(){
        currentContactList = new ArrayList<>();
        fullContactList = new MainViewModel().getContacts().getValue();
    }

    public void setData(List<Contact> contacts){
        currentContactList.clear();
        currentContactList.addAll(contacts);
        notifyDataSetChanged();
    }

    public List<Contact> getAllContacts(){
        return this.fullContactList;
    }

    public void addContact(Contact contact){
        this.fullContactList.add(contact);
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
    }

    @Override
    public int getItemCount() {
        return currentContactList.size();
    }

}
