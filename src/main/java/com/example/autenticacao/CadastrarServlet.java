package com.example.autenticacao;

import com.example.autenticacao.model.User;
import com.example.autenticacao.repository.SqliteDAOImpl;

import javax.crypto.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@WebServlet(name = "verificarServlet", value = "/verificar")
public class CadastrarServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {



        HttpSession session = request.getSession();

        if(request.getParameter("email") != null && request.getParameter("senha") != null) {
            SqliteDAOImpl impl = new SqliteDAOImpl();
            User user = impl.findOne(request.getParameter("email"));
            if(user == null) {
                String username = request.getParameter("email");
                String nome = request.getParameter("nome");
                String password = request.getParameter("senha");
                try {
                    byte[] senhaCriptografada = encriptografar(password);
                    impl.insert(new User(username,nome,String.valueOf(senhaCriptografada)));
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                }

            }else{
                session.setAttribute("erro", "Usuário já cadastrado");
            }

        }

        response.sendRedirect(".");










    }

    public byte[] encriptografar(String senha) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
        SecretKey chaveDES = keygenerator.generateKey();

        Cipher cifraDES;

        // Cria a cifra
        cifraDES = Cipher.getInstance("DES/ECB/PKCS5Padding");

        // Inicializa a cifra para o processo de encriptação
        cifraDES.init(Cipher.ENCRYPT_MODE, chaveDES);

        byte[] senhaCriptografada = cifraDES.doFinal(senha.getBytes());

        cifraDES.init(Cipher.DECRYPT_MODE, chaveDES);

        byte[] textoDecriptografado = cifraDES.doFinal(senhaCriptografada);
        System.out.println(new String (textoDecriptografado));
        return senhaCriptografada;
    }

    public void destroy() {
    }

}
