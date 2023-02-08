package com.example.pokedexapp.backend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.pokedexapp.DashboardActivity;
import com.example.pokedexapp.data.model.Login;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import com.example.pokedexapp.data.model.Login;

public class RequestTask extends AsyncTask<Login, Void, Boolean> {
    private static final String IP = "10.0.2.2";
    private static final int port = 12345;
    private final Boolean auth;
    private Boolean achou;
    private Context context;

    public RequestTask(Boolean auth, Context context){
        this.context = context;
        this.auth = auth;
    }

    @Override
    protected void onPostExecute(Boolean auth){
        super.onPostExecute(auth);
        if(auth){
            Intent it = new Intent(context, DashboardActivity.class);
            context.startActivity(it);
        }else{
            Toast.makeText(context, "Login inválido", Toast.LENGTH_SHORT).show();
        }
        
    }










    @Override
    protected Boolean doInBackground(Login... logins){
        achou = false;

        try{
            Socket socket = new Socket(IP, port);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            Login login = logins[0];
            output.writeUTF(login.getUsuario());
            output.writeUTF(login.getSenha());
            output.flush();
            achou = input.readBoolean();
            output.close();
            input.close();
            socket.close();



        } catch (IOException e){
            e.printStackTrace();
        }
        return achou;
    }




}
