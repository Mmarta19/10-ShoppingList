package edu.upc.eseiaat.pma.shoppinglist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {

    private ArrayList<ShoppingItem> itemList;
    private ShoppingListAdapter adapter;


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
        itemList.add(new ShoppingItem ("Patatas"));        // Le ponemos los elementos de la lista
        itemList.add(new ShoppingItem ("Papel WC"));
        itemList.add(new ShoppingItem("Huevos"));
        itemList.add(new ShoppingItem("Copas Danone"));

        adapter = new ShoppingListAdapter(     // Creamos el Adapter
                this,
                android.R.layout.simple_list_item_1,
                itemList
        );
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });

        edit_item.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                addItem();
                return true;
            }
        });

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Log.i("marta","onItemClick");
               itemList.get(pos).toggleleChecked(); // method creado, para poner a desmarcado el que esta marcado.
                adapter.notifyDataSetChanged();


                /* ShoppingItem item = itemList.get(pos);  // item de la lista
                boolean check = item.isChecked(); // mirar como esta
                itemList.get(pos).setChecked(!check);*/

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> list, View item, int pos, long id) {
                maybeRemoveItem(pos); // cuál


                return true;
            }
        });
    }

    private void maybeRemoveItem(final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this); // creem un builder
        builder.setTitle(R.string.confirm); // títol del builder

        String fmt = getResources().getString(R.string.confirm_message);
        builder.setMessage(String.format(fmt, itemList.get(pos).getText()));  // missatge traduible

        builder.setPositiveButton(R.string.remove, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                itemList.remove(pos); // lo borra
                adapter.notifyDataSetChanged();

            }
        });
        builder.setNegativeButton(android.R.string.cancel, null); // el cancel és de Android i es tradueix automàticament
        builder.create().show();


    }

    private void addItem() {
        String item_text = edit_item.getText().toString();
       //  if (!item_text.equals(""))
        if (!item_text.isEmpty()){          // si no esta vacia el editText lo añado a la Lista
            itemList.add(new ShoppingItem(item_text)); // creo un object nuevo
            adapter.notifyDataSetChanged();
            edit_item.setText(""); // així cada vegada que afageixo un element a la llista es borra
            list.smoothScrollToPosition(itemList.size()-1); // muevete hasta el ultimo elemento
        }
    }



}
