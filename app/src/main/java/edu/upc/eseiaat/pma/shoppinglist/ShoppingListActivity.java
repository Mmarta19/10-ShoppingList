package edu.upc.eseiaat.pma.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {

    private ArrayList<String> itemList;
    private ArrayAdapter<String> adapter;


    private ListView list; //son campos
    private Button btn_add;
    private EditText edit_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        // són variables (cmand+alt+f --> campos (private... )

        // Button btn_add = (Button)findViewById(R.id.btn_add);
        // ListView list = (ListView)findViewById(R.id.list);

        list = (ListView)findViewById(R.id.list);
        btn_add = (Button)findViewById(R.id.btn_add);
        edit_item = (EditText) findViewById(R.id.edit_item);

        itemList = new ArrayList<>();   // creamos la Lista
        itemList.add("Patatas");        // Le ponemos los elementos de la lista
        itemList.add("Papel WC");
        itemList.add("Huevos");
        itemList.add("Copas Danone");

        adapter = new ArrayAdapter<String>(     // Creamos el Adapter
                this,
                android.R.layout.simple_list_item_1,
                itemList
        );




        list.setAdapter(adapter);



    }






}
