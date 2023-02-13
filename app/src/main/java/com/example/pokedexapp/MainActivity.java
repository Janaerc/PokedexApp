package com.example.pokedexapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.pokedexapp.backend.RetrofitConfig;
import com.example.pokedexapp.data.model.LoginDTO;
import com.example.pokedexapp.data.model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    UsuarioDTO usuarioDTO;
    EditText usuario, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.editTextUsuario);
        senha = findViewById(R.id.editTextSenha);

    }

    public void convert (View view) {

        LoginDTO login = new LoginDTO();
        login.setLogin(usuario.getText().toString());
        login.setSenha(senha.getText().toString());
        System.out.println(login.getLogin());

        Call<UsuarioDTO> call1 = new RetrofitConfig().getPokedexService().login(login);

        call1.enqueue(new Callback<UsuarioDTO>() {
            @Override
            public void onResponse(Call<UsuarioDTO> call1, Response<UsuarioDTO> response) {
                if (response.isSuccessful()) {
                    usuarioDTO = response.body();
                    System.out.println("entrou");
                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                    intent.putExtra("usuario", usuarioDTO);
                    startActivity(intent);

                    finish();
                } else
                if (response.code() == 401)
                    Toast.makeText(MainActivity.this, "Login ou Senha incorretos", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Erro de login", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UsuarioDTO> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro de API", Toast.LENGTH_SHORT).show();
            System.out.println(t.getMessage());
                System.out.println(t.getStackTrace());

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


