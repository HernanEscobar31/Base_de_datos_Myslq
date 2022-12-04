package com.example.basededatosmyslq;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText clave, nombre, carrera, direccion;
    Button btnaltas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clave = findViewById(R.id.clave);
        nombre = findViewById(R.id.nombre);
        carrera = findViewById(R.id.carrera);
        direccion = findViewById(R.id.direccion);
        btnaltas = findViewById(R.id.button);
        btnaltas.setOnClickListener(new View.OnClickListener() {
           //Crear una intranet donde se conecten el celular y la compu a la misma red, porque cambia cada que nos conectamos
            @Override
            public void onClick(View view) {
               ejecutarServicio("http://192.168.1.10/prueba/alta.php");
            }
        });
    }
    private void ejecutarServicio(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("clave",clave.getText().toString());
                parametros.put("nombre",nombre.getText().toString());
                parametros.put("carrera",carrera.getText().toString());
                parametros.put("direccion",direccion.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}