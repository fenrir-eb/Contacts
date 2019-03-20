package com.example.contacts;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contactList;
    private ContactAdapter adapter;
    private MainViewModel mainViewModel;

    private EditText    etFilter;
    private EditText    etInput;
    private Button      btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initModel();
    }

    private void initView(){
        adapter = new ContactAdapter();
        RecyclerView recycler = findViewById(R.id.rv_main);
        recycler.setLayoutManager(new GridLayoutManager(this,2));
        recycler.setAdapter(adapter);

        etInput = findViewById(R.id.et_input_main);
        etFilter = findViewById(R.id.et_filter_main);
        initFilter();

        btnAdd = findViewById(R.id.btn_add_main);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = new Contact(adapter.getAllContacts().get(adapter.getAllContacts().size()-1).getId()+1,etInput.getText().toString());
                adapter.addContact(contact);
                contactList.add(contact);
                mainViewModel.setContacts(contactList);
            }
        });
    }

    private void initModel(){
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                contactList = new ArrayList<>(contacts);
                adapter.setData(contactList);
            }
        });
    }

    private void initFilter(){
        etFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                String input = charSequence.toString();
//                List<Contact> fullList = adapter.getAllContacts();
//                if(input.equals(""))
//                    mainViewModel.setContacts(adapter.getAllContacts());
//                else{
//
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
