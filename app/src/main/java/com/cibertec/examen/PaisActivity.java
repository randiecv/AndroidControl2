package com.cibertec.examen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cibertec.examen.entidad.Pais;
import com.cibertec.examen.servicio.ServicioRest;
import com.cibertec.examen.util.ConnectionRest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaisActivity extends AppCompatActivity {

    ServicioRest servicio;
    EditText edtUId, edtNombre,edtIso;
    Button btnSave;
    Button btnDel;
    TextView txtUId;
    final String metodo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais);

        setTitle("CRUD de Rol");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = (TextView) findViewById(R.id.txtIdPais);
        edtUId = (EditText) findViewById(R.id.edtIdPais);
        edtNombre = (EditText) findViewById(R.id.edtPaisNombre);
        edtIso = (EditText) findViewById(R.id.edtPaisIso);
        btnSave = (Button) findViewById(R.id.btnPaisSave);
        btnDel = (Button) findViewById(R.id.btnPaisDel);

        servicio = ConnectionRest.getConnection().create(ServicioRest.class);
        Bundle extras = getIntent().getExtras();
        final String metodo = extras.getString("var_metodo");
        final String var_id = extras.getString("var_id");

        if (metodo.equals("VER")) {
            String var_nombre = extras.getString("var_nombre");
            String var_iso = extras.getString("var_iso");

            edtUId.setText(var_id);
            edtIso.setText(var_iso);
            edtNombre.setText(var_nombre);
            edtUId.setFocusable(false);
        }else if (metodo.equals("REGISTRAR")) {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pais u = new Pais();
                u.setIso(edtIso.getText().toString());
                u.setNombre(edtNombre.getText().toString());
                if (metodo.equals("VER")) {
                    u.setIdpais(Integer.parseInt(var_id));
                    mensaje("Se pulsó  actualizar");
                    update(u);
                } else if (metodo.equals("REGISTRAR")) {
                    mensaje("Se pulsó agregar");
                    add(u);
                }

                Intent intent = new Intent(PaisActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje("Se pulsó eliminar");
                delete(Integer.parseInt(var_id));
                Intent intent = new Intent(PaisActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void add(Pais u) {
        Call<Pais> call = servicio.agregaPais(u);
        call.enqueue(new Callback<Pais>() {
            @Override
            public void onResponse(Call<Pais> call, Response<Pais> response) {
                if (response.isSuccessful()) {
                    mensaje("Registro exitoso");
                }
            }
         @Override
            public void onFailure(Call<Pais> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void update(Pais u) {
        Call<Pais> call = servicio.actualizaPais(u);
        call.enqueue(new Callback<Pais>() {
            @Override
            public void onResponse(Call<Pais> call, Response<Pais> response) {
                if (response.isSuccessful()) {
                    mensaje("Actualización exitosa");
                }
            }
         @Override
            public void onFailure(Call<Pais> call, Throwable t) {
                 Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void delete(int id) {
        Call<Pais> call = servicio.eliminaPais(id);
        call.enqueue(new Callback<Pais>() {
            @Override
            public void onResponse(Call<Pais> call, Response<Pais> response) {
                if (response.isSuccessful()) {
                    mensaje("Eliminación exitosa");
                }
            }
            @Override
            public void onFailure(Call<Pais> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void mensaje(String msg) {
        Toast toast1 = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
        toast1.show();
    }

//    private void selectValue(Spinner spinner, Object value) {
//        for (int i = 0; i < spinner.getCount(); i++) {
//            if (spinner.getItemAtPosition(i).equals(value)) {
//                spinner.setSelection(i);
//                break;
//            }
//        }
//    }

}
