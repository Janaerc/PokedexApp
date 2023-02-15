package com.example.pokedexapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import com.example.pokedexapp.backend.ImageConverter;
import com.example.pokedexapp.backend.RetrofitConfig;
import com.example.pokedexapp.data.model.PokemonDTO;
import com.example.pokedexapp.data.model.UsuarioDTO;

import java.io.IOException;

public class CadastroPokemon extends AppCompatActivity {

    private static final int CAMERA  = 100;
    private static final int GALERIA = 200;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    String base64;
    Bitmap bitmap;

    UsuarioDTO usuarioDTO;
    EditText nomePokemon, tipoPokemon, habilidadePokemon;
    Button buttonSalvar, buttonCamera, buttonGaleria;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pokemon);

        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");
        nomePokemon = findViewById(R.id.editTextNomePokemon);
        tipoPokemon = findViewById(R.id.editTextTipoPokemon);
        habilidadePokemon = findViewById(R.id.editTextHabilidades);
        imageView = findViewById(R.id.fotoPokemon);

    }

    public void cadastrar(View view) {
        PokemonDTO pokemon = new PokemonDTO();
        pokemon.setId_usuario(usuarioDTO.getId());
        pokemon.setNome_pokemon(nomePokemon.getText().toString());
        pokemon.setTipo_pokemon(tipoPokemon.getText().toString());
        pokemon.setHabilidade(habilidadePokemon.getText().toString());
        pokemon.setFoto_pokemon(base64);

        Call<PokemonDTO> call1 = new RetrofitConfig().getPokedexService().cadastrarPokemon(pokemon);
        System.out.println(pokemon.getId_usuario());
        System.out.println(pokemon.getNome_pokemon());
        System.out.println(pokemon.getTipo_pokemon());
        System.out.println(pokemon.getHabilidade());



        call1.enqueue(new Callback<PokemonDTO>()    {

            @Override
            public void onResponse(Call<PokemonDTO> call, Response<PokemonDTO> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CadastroPokemon.this, "Mutante salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
                } else {
                    if (response.code() == 409)
                        Toast.makeText(CadastroPokemon.this, "Mutante já cadastrado", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(CadastroPokemon.this, "Erro ao salvar Mutante", Toast.LENGTH_SHORT).show();
                }}

            @Override
            public void onFailure(Call<PokemonDTO> call, Throwable t) {


            }
        });



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }




    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {



            case R.id.CadastroPokemon:
                Intent it = new Intent( this, CadastroPokemon.class);
                startActivity(it);
                return true;

            case R.id.ListarTodos:
                it = new Intent( this, ListarTodos.class);
                startActivity(it);
                return true;

            case R.id.PesquisarTipo:
                it = new Intent( this, PesquisarTipo.class);
                startActivity(it);
                return true;

            case R.id.PesquisarHabilidade:
                it = new Intent( this, PesquisarHabilidade.class);
                startActivity(it);
                return true;

            case R.id.Sair:
                Logout logout = new Logout(this);
                logout.logout();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }
    /*
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void camera(View view) {

        if (this.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        }
        else
        {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA);
        }
    }*/


    public void galeria(View view) {
        Intent it = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(it, GALERIA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == CAMERA) {
                bitmap = (Bitmap) data.getExtras().get("data");
                Log.i("INFO", String.format("Camera: %dx%d\n", bitmap.getWidth(), bitmap.getHeight()));
                imageView.setImageBitmap(bitmap);
                base64 = ImageConverter.bitmapToBase64(bitmap);
            }

            if (requestCode == GALERIA) {
                Uri imageUri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    Log.i("INFO", String.format("Galeria: %dx%d\n", bitmap.getWidth(), bitmap.getHeight()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                imageView.setImageBitmap(bitmap);
                base64 = ImageConverter.bitmapToBase64(bitmap);
            }
        }
    }

}