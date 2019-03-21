package com.example.contacts;

import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Contact>> contactsLiveData;
    private List<Contact> fullContactList;

    public MainViewModel() {
        this.contactsLiveData = new MutableLiveData<>();
        this.fullContactList = new ArrayList<>();

        for(int i=0; i<50; i++)
            fullContactList.add(randomizeContact(i));

        this.sortContactList(fullContactList);
        this.contactsLiveData.setValue(fullContactList);
    }

    public void filterContacts(String input, EditText etInput){
        List<Contact> filteredContactList = new ArrayList<>();

        if(input.equals("")) {
            filteredContactList.clear();
            filteredContactList.addAll(this.fullContactList);
            etInput.setEnabled(true);

        } else{
            for(Contact contact : this.fullContactList)
                if(contact.getName().toLowerCase().startsWith(input.toLowerCase()))
                    filteredContactList.add(contact);
            etInput.setEnabled(false);
        }

        this.setContacts(filteredContactList);
    }


    public void sortContactList(List<Contact> contacts){
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });
    }


    public LiveData<List<Contact>> getContacts(){
        return this.contactsLiveData;
    }

    public void setContacts(List<Contact> contacts){
        this.contactsLiveData.setValue(new ArrayList<>(contacts));
    }

    public void setFullContactList(List<Contact> contacts){
        this.fullContactList.clear();
        this.fullContactList.addAll(contacts);
    }

    public List<Contact> getFullContactList() {
        return fullContactList;
    }


    private Contact randomizeContact(int i){
        ArrayList<String> imena =new ArrayList<>(Arrays.asList("Marko","Petar","Janko","Pavle","Jovan","Luka","Stefan","Milos","Maca","Boban","Mia","Ana","Goran","Mirko"));
        ArrayList<String> prezimena = new ArrayList<>(Arrays.asList("Aksic","Veljo","Markovic","Ilic","Djekic","Antic","Djuric","Jevtic","Tosic","Antonic","Jokic","Lukic"));
        ArrayList<String> email = new ArrayList<>(Arrays.asList("gmail.com","yahoo.com","hotmail.com","raf.rs"));

        String ime = imena.get(i%imena.size());
        String prezime = prezimena.get(i%prezimena.size());
        String mail = ime.toLowerCase().charAt(0)+""+prezime.toLowerCase().subSequence(0,3)+"@"+email.get(i%email.size());
        String broj = "06"+i%10+""+(new Random().nextInt(9000000) + 1000000);

        return new Contact(i,ime+" "+prezime,broj+"\n"+mail);
    }

}
