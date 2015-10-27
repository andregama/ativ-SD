
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.sd;

import java.io.*;
import java.net.*;

public class Client {

    Socket client;

    Client(String host, int porta) throws IOException {
        //Socket client;
            // Criar novo cliente
            client = new Socket(host, porta);
            client.setKeepAlive(true); //deixa o cliente ativo
    }
    
    public Resposta sendReceive(Requisicao req) throws IOException, ClassNotFoundException 
    {
        ObjectOutputStream send = new ObjectOutputStream(client.getOutputStream());
        send.writeObject(req);

        //Aguardo de resposta
        ObjectInputStream recv = new ObjectInputStream(client.getInputStream());
        Resposta rep = (Resposta) recv.readObject();
        
        // Fechar a conexao
                send.close();
                recv.close();
                close();
        return rep;   
    }

    public Resposta getUser(Player player) throws IOException, ClassNotFoundException 
    {
        Requisicao req = new Requisicao();
        req.setMessageType(Requisicao.GET_PLAYER);
        req.setPlayerContent(player);
        return sendReceive(req);
    }
    
    public Resposta newUser(Player player) throws IOException, ClassNotFoundException
    {
        Requisicao req = new Requisicao();
        req.setMessageType(Requisicao.NEW_PLAYER);
        req.setPlayerContent(player);
        return sendReceive(req);
    }
    
    public Resposta allUsers() throws IOException, ClassNotFoundException 
    {
        Requisicao req = new Requisicao();
        req.setMessageType(Requisicao.ALL_PLAYERS);
        return sendReceive(req);
    }

    public void close() throws IOException
    {
        client.close();
    }
}
