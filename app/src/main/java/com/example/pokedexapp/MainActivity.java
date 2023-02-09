package com.example.pokedexapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pokedexapp.backend.RequestTask;
import com.example.pokedexapp.data.model.Login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convert (View view) {
        EditText usuario = findViewById(R.id.editTextUsuario);
        EditText senha = findViewById(R.id.editTextSenha);

        String usuarioaux = usuario.getText().toString();
        String senhaaux = senha.getText().toString();
        int auth = 0;



        Login login = new Login(usuarioaux, senhaaux);
        RequestTask task = new RequestTask(0, this);
        task.execute(login);

    }

}