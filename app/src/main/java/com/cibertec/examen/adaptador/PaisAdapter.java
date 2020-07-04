package com.cibertec.examen.adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cibertec.examen.R;
import com.cibertec.examen.PaisActivity;
import com.cibertec.examen.entidad.Pais;

import java.util.List;

public class PaisAdapter extends ArrayAdapter<Pais> {

    private Context context;
    private List<Pais> paises;

    public PaisAdapter(Context context, int resource, List<Pais> paises) {
        super(context, resource, paises);
        this.context = context;
        this.paises = paises;
    }

    @Override
    public View getView(final int pos, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_list, parent, false);


        TextView txtId = (TextView) rowView.findViewById(R.id.txtListViewID);
        TextView txtNombre = (TextView) rowView.findViewById(R.id.txtListViewName);

        txtId.setText(String.format("#ID: %d", paises.get(pos).getIdpais()));
        txtNombre.setText(String.format("NOMBRE: %s", paises.get(pos).getNombre()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PaisActivity.class);
                intent.putExtra("var_id", String.valueOf(paises.get(pos).getIdpais()));
                intent.putExtra("var_iso", paises.get(pos).getIso());
                intent.putExtra("var_nombre", paises.get(pos).getNombre());
                intent.putExtra("var_metodo", "VER");
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
