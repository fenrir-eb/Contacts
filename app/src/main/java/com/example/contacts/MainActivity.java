package com.example.contacts;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contactList;
    private ContactAdapter adapter;
    private MainViewModel mainViewModel;

    private RecyclerView recycler;
    private GridLayoutManager gridLayout;
    private LinearLayoutManager linearLayout;
    private RecyclerView.LayoutManager layoutManager;

    private EditText    etFilter;
    private EditText    etInput;
    private ImageButton btnAdd;
    private ImageButton btnSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initModel();
        initView();
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

    private void initView(){
        adapter = new ContactAdapter();
        recycler = findViewById(R.id.rv_main);
        gridLayout = new GridLayoutManager(this,2);
        linearLayout = new LinearLayoutManager(this);
        recycler.setLayoutManager(gridLayout);
        recycler.setAdapter(adapter);

        etInput = findViewById(R.id.et_input_main);
        etFilter = findViewById(R.id.et_filter_main);
        btnAdd = findViewById(R.id.btn_add_main);
        btnSwitch = findViewById(R.id.btn_layout_main);

        initFilter();
        initAddButton();
        initSwitchButton();
    }

    private void initFilter(){
        etFilter.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mainViewModel.filterContacts(charSequence.toString(),etInput);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void initAddButton(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = new Contact(mainViewModel.getFullContactList().get(mainViewModel.getFullContactList()
                        .size()-1).getId()+1,etInput.getText().toString());
                if(validateContact(contact)) {
                    contactList.add(contact);
                    mainViewModel.sortContactList(contactList);
                    mainViewModel.setFullContactList(contactList);
                    mainViewModel.setContacts(contactList);
                } else
                    Toast.makeText(MainActivity.this,"Contact exists",Toast.LENGTH_SHORT).show();

                etInput.setText(null);
            }
        });
    }

    private void initSwitchButton(){
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recycler.getLayoutManager() instanceof GridLayoutManager)
                    recycler.setLayoutManager(linearLayout);
                else
                    recycler.setLayoutManager(gridLayout);
            }
        });
    }

    private boolean validateContact(Contact contact){
        for(Contact cont : mainViewModel.getFullContactList()){
            if(cont.getName().toLowerCase().equals(contact.getName().toLowerCase().trim()))
                return false;
        }
        return true;
    }
}
