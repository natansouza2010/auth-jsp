package com.example.autenticacao;

import com.example.autenticacao.model.User;
import com.example.autenticacao.repository.SqliteDAOImpl;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/autenticar")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("id") != null){
            session.invalidate();
        }else{
            session.setAttribute("erro","Acesso indevido");
        }

        response.sendRedirect(".");
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        HttpSession session = request.getSession();

        if(request.getParameter("email") != null && request.getParameter("senha") != null) {
            SqliteDAOImpl repository = new SqliteDAOImpl();
            String usuario = request.getParameter("email");
            String senha = request.getParameter("senha");
            User user = repository.findOne(usuario);
            try {

                if(user != null){
                    if(usuario.equals(user.getUsername()) && senha.equals(new String (descriptografar(user.getSenha())))){
                        session.setAttribute("id", String.valueOf(user.getId()));
                        session.setAttribute("nome", String.valueOf(user.getNome()));
                    }else{
                        session.setAttribute("erro","Senha Inválida");
                    }
                }else{
                    session.setAttribute("erro","Usuário não existe");
                }
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            }


        }

        response.sendRedirect(".");










    }


    public byte[] descriptografar(String senha) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
        SecretKey chaveDES = keygenerator.generateKey();

        Cipher cifraDES;

        cifraDES = Cipher.getInstance("DES/ECB/PKCS5Padding");

        cifraDES.init(Cipher.DECRYPT_MODE, chaveDES);
        System.out.println(senha);
        byte[] textoDecriptografado = cifraDES.doFinal(senha.getBytes());
        System.out.println(new String (textoDecriptografado));
        return textoDecriptografado;
    }
    public void destroy() {
    }
}