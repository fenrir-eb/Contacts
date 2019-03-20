package com.example.contacts;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class ContactDiffUtilCallback extends DiffUtil.Callback {

    private List<Contact> oldContactList;
    private List<Contact> newContactList;

    public ContactDiffUtilCallback(List<Contact> oldContactList,List<Contact> newContactList){
        this.oldContactList = oldContactList;
        this.newContactList = newContactList;
    }

    @Override
    public int getOldListSize() {
        return this.oldContactList.size();
    }

    @Override
    public int getNewListSize() {
        return this.newContactList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldContactList.get(oldItemPosition).getId() == newContactList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return (oldContactList.get(oldItemPosition).getName()).equals(newContactList.get(newItemPosition).getName());
    }
}
