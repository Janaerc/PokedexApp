package com.example.pokedexapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pokedexapp.backend.RequestTask;
import com.example.pokedexapp.backend.RetrofitConfig;
import com.example.pokedexapp.data.model.Login;
import com.example.pokedexapp.data.model.Usuario;
import com.example.pokedexapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convert (View view) {
        EditText usuario = findViewById(R.id.editTextUsuario);
        EditText senha = findViewById(R.id.editTextSenha);
        Login login = new Login();
        login.setUsuario(usuario.getText().toString());
        login.setSenha(senha.getText().toString());

        Call<Usuario> call = new RetrofitConfig().getPokedexService().login(login);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(retrofit2.Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Usuario usuario = response.body();

                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                    intent.putExtra("usuario", usuario);
                    startActivity(intent);

                    finish();
                } else if (response.code() == 401)
                    Toast.makeText(MainActivity.this, "Login ou Senha incorretos", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Erro de login", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(retrofit2.Call<Usuario> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro na API", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
    /*public void convert (View view) {
        EditText usuario = findViewById(R.id.editTextUsuario);
        EditText senha = findViewById(R.id.editTextSenha);

        String usuarioaux = usuario.getText().toString();
        String senhaaux = senha.getText().toString();
        int auth = 0;



        Usuario login = new Usuario(usuarioaux, senhaaux);
        RequestTask task = new RequestTask(0, this);

        task.execute(login);*/


