package com.example.pokedexapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokedexapp.backend.RetrofitConfig;
import com.example.pokedexapp.data.model.PokemonDTO;
import com.example.pokedexapp.data.model.UsuarioDTO;
import com.example.pokedexapp.helper.ImageConverter;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Response;

public class CadastroPokemon extends AppCompatActivity {


    private static final int CAMERA  = 100;
    private static final int GALERIA = 200;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    String base64;
    Bitmap bitmap;
    UsuarioDTO usuarioDTO, usuarioDono;
    PokemonDTO pokemonDTO;
    String operacao;

    EditText editTextNome, editTextHabilidade1, editTextHabilidade2, editTextHabilidade3;
    ImageView imageView;
    TextView textViewUsuario;
    Button buttonCadastrar, buttonCamera, buttonGaleria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pokemon);

        Bundle params = getIntent().getExtras();

        if (params != null) {
            operacao = params.getString("operacao");
            pokemonDTO = (PokemonDTO) params.getSerializable("pokemon");
            usuarioDTO = (UsuarioDTO) params.getSerializable("usuario");
        }


        editTextNome = findViewById(R.id.editTextNomePokemon);
        editTextHabilidade1 = findViewById(R.id.editTextHabilidades);
        //imageView           = findViewById(R.id.imageViewFotoMutante);
        buttonCadastrar = findViewById(R.id.btnCadastrar);
        // buttonCamera        = findViewById(R.id.buttonCamera);
        // buttonGaleria       = findViewById(R.id.buttonGaleria);
        textViewUsuario = findViewById(R.id.idUsuario);


        String aux = getIntent().getStringExtra("id");
        System.out.println("AQUI É o FRONT CADASTRO");
        System.out.println(aux);
        textViewUsuario.setText(aux);


        if (!operacao.equals("new")) {
            editTextNome.setText(pokemonDTO.getNome());
            if (pokemonDTO.getHabilidades() != null) {
                if (pokemonDTO.getHabilidades().size() > 0)
                    editTextHabilidade1.setText(pokemonDTO.getHabilidades().get(0).getDescricao());
                if (pokemonDTO.getHabilidades().size() > 1)
                    editTextHabilidade2.setText(pokemonDTO.getHabilidades().get(1).getDescricao());
                if (pokemonDTO.getHabilidades().size() > 2)
                    editTextHabilidade3.setText(pokemonDTO.getHabilidades().get(2).getDescricao());
            }
            base64 = pokemonDTO.getImagem();
            bitmap = ImageConverter.base64ToBitmap(base64);
            imageView.setImageBitmap(bitmap);

            recuperaUsuarioDono(pokemonDTO.getIdUsuario());

            editTextNome.setEnabled(false);
            editTextHabilidade1.setEnabled(false);
            buttonCadastrar.setEnabled(false);
            buttonCamera.setEnabled(false);
            buttonGaleria.setEnabled(false);

        }
    }
        private void recuperaUsuarioDono(Long idUsuario){

            Call<UsuarioDTO> call1 = new RetrofitConfig().getPokedexService().getUsuario(idUsuario);

            call1.enqueue(new Callback<UsuarioDTO>() {
                @Override
                public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
                    if (response.isSuccessful()) {
                        usuarioDono = response.body();
                        textViewUsuario.setText("Usuário:" + usuarioDono.getNome());
                    }
                }

                @Override
                public void onFailure(Call<UsuarioDTO> call, Throwable t) {
                    Toast.makeText(CadastroPokemon.this, "Erro ao recuperar Usuário", Toast.LENGTH_SHORT).show();
                }
            });

        }

       /* @Override
        public boolean onCreateOptionsMenu (Menu menu){

            if (!operacao.equals("new")) {
                getMenuInflater().inflate(R.menu.main_menu, menu);
            }
            return super.onCreateOptionsMenu(menu);
        }

    }*/
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            if (!operacao.equals("new")) {
                getMenuInflater().inflate(R.menu.menu_edicao, menu);
            }
            return super.onCreateOptionsMenu(menu);
        }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {

        if (menuItem.getItemId() == R.id.menu_editar) {
            operacao = "edit";
            editTextNome.setEnabled(true);
            editTextHabilidade1.setEnabled(true);
            buttonCadastrar.setEnabled(true);
            buttonCamera.setEnabled(true);
            buttonGaleria.setEnabled(true);
        } else if (menuItem.getItemId() == R.id.menu_excluir) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(CadastroPokemon.this);
            dialog.setTitle("Confirmar Exclusão");
            dialog.setMessage("Deseja excluir o Mutante: " + pokemonDTO.getNome() + "?");
            dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Call<PokemonDTO> call1 = new RetrofitConfig().getPokedexService().deletePokemon(pokemonDTO.getId());

                    call1.enqueue(new Callback<PokemonDTO>() {
                        @Override
                        public void onResponse(Call<PokemonDTO> call, Response<PokemonDTO> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(CadastroPokemon.this, "Mutante deletado!!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else
                                Toast.makeText(CadastroPokemon.this, "Erro ao deletar Pokemon", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<PokemonDTO> call, Throwable t) {
                            Toast.makeText(CadastroPokemon.this, "Erro de API:" + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                }
            });
            dialog.setNegativeButton("Não", null);

            dialog.create();
            dialog.show();
        }

        return super.onOptionsItemSelected(menuItem);
    }







}

    //public boolean onCreateOptionsMenu(Menu menu) {
    //    getMenuInflater().inflate(R.menu.main_menu, menu);
    //    return true;
   // }



/*
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
*/
}