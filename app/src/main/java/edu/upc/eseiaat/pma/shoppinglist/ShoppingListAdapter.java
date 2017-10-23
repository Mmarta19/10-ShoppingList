package edu.upc.eseiaat.pma.shoppinglist;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Marta on 23/10/17.
 */

public class ShoppingListAdapter extends ArrayAdapter <ShoppingItem> {


    public ShoppingListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // llama el ListView cuando le quiere pedir al adapter una de las pastillitas
        // ViewGroup: La lista  ; convertView: no cal recrear les pastillitas

        View result = convertView;
        if (result == null) {       // no se esta reciclando nada porque, por ejemplo, se esta creando la lista de nuevo
            // crear uno nuevo
            LayoutInflater inflater = LayoutInflater.from(getContext()); // si estiguessim a una Activity, el context sería this.
            result = inflater.inflate(R.layout.shopping_item, null);
        }

        CheckBox shopping_item = (CheckBox) result.findViewById(R.id.shopping_item); // se saca el elemento del checkbox
        ShoppingItem item = getItem(position);  // devuelve el valor (string) de la lista en esa posicion
        shopping_item.setText(item.getText());
        return result;
    }

}
