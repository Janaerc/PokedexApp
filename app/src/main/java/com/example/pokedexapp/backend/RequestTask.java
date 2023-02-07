package com.example.pokedexapp.backend;

import android.os.AsyncTask;

import com.example.pokedexapp.data.model.Login;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RequestTask extends AsyncTask<Login, Void, Boolean> {
    private static final String IP = "10.0.2.2";
    private static final int port = 12345;
    private final Boolean auth;

    public RequestTask(Boolean auth){
        this.auth = auth;
    }

    @Override
    protected void onPostExecute(Boolean auth){
        super.onPostExecute(auth);
        //aviso se logou ou não e mandar para a próxima pagina;
    }

    @Override
    protected Boolean doInBackground(Login... logins){
        Boolean achou = false;


        try{
            Socket socket = new Socket(IP, port);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            String nome = "rafael";
            output.writeUTF(nome);
            String senha = "123";
            output.writeUTF(senha);
            output.flush();
            achou = input.readBoolean();
            System.out.println(achou);
            output.close();
            input.close();
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return achou;
    }




}
