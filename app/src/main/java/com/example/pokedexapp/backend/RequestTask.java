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

public class RequestTask extends AsyncTask<Login, Void, Integer> {
    private static final String IP = "10.0.2.2";
    private static final int port = 12345;
    private final int auth;
    private int achou;
    private Context context;
    private int id;


    public RequestTask(int auth, Context context){
        this.context = context;
        this.auth = auth;
    }


    public static final String SHARED_PREFERENCES_NAME = "user_session";
    private static final String SESSION_KEY = "is_logged_in";


    @Override
    protected void onPostExecute(Integer auth){
        super.onPostExecute(auth);
        if(auth != 0){
            SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(SESSION_KEY, true);
            editor.putInt("id",id);
            editor.apply();
            System.out.println("entrou no if");
            System.out.println(auth);
            Intent it = new Intent(context, DashboardActivity.class);
            it.putExtra("id", auth);
            context.startActivity(it);


        }else{
            Toast.makeText(context, "Login inválido", Toast.LENGTH_SHORT).show();
            System.out.println(auth);
            System.out.println("entrou no else");
        }

    }



    @Override
    protected Integer doInBackground(Login... logins){

        try{
            Socket socket = new Socket(IP, port);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            Login login = logins[0];
            output.writeUTF(login.getUsuario());
            output.writeUTF(login.getSenha());
            output.flush();
            id = input.readInt();
            output.close();
            input.close();
            socket.close();
            return achou;


            return id;

        } catch (IOException e){
            e.printStackTrace();
        }
        return id;
    }



}
