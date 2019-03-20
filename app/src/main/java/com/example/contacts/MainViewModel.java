package com.example.contacts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Contact>> contactsLiveData;

    public MainViewModel() {
        this.contactsLiveData = new MutableLiveData<>();
        List<Contact> contacts = new ArrayList<>();
        ArrayList<String> imena =new ArrayList<>(Arrays.asList("Marko","Petar","Janko","Pavle","Jovan","Luka","Stefan"));
        for(int i=0; i<50; i++){
            contacts.add(new Contact(i,imena.get(i%7)+" "+i));
        }
        this.contactsLiveData.setValue(contacts);
    }

    public LiveData<List<Contact>> getContacts(){
        return this.contactsLiveData;
    }

    public void setContacts(List<Contact> contacts){
        this.contactsLiveData.setValue(new ArrayList<>(contacts));
    }

}
