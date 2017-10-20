package edu.upc.eseiaat.pma.shoppinglist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
        builder.setMessage(String.format(fmt, itemList.get(pos)));  // missatge traduible

        builder.setPositiveButton(R.string.remove, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                itemList.remove(pos); // lo borra

            }
        });
        builder.setNegativeButton(android.R.string.cancel, null); // el cancel és de Android i es tradueix automàticament
        builder.create().show();
        adapter.notifyDataSetChanged(); //avisamos al adapter

    }

    private void addItem() {
        String item_text = edit_item.getText().toString();
       //  if (!item_text.equals(""))
        if (!item_text.isEmpty()){          // si no esta vacia el editText lo añado a la Lista
            itemList.add(item_text);
            adapter.notifyDataSetChanged();
            edit_item.setText(""); // així cada vegada que afageixo un element a la llista es borra
        }
    }


}
