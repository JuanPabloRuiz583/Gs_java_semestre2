package org.example.models;


public class Cliente {
    private String nome;

    private String email;

    private String senha;

  //  private int id;



    public Cliente(String nome,String email,String senha) {

        this.nome = nome;
        this.email = email;
        this.senha= senha;
    }


    //metodos
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    //private
    public void setEmail(String email) {
        this.email = email;
    }



    public String getSenha() {
        return senha;
    }

    //private
    public void setSenha(String senha) {
        this.senha = senha;
    }









    //TODO
    public boolean login(String email, String senha){

        // Verifica se os parâmetros de entrada são nulos
        if (email == null || senha == null) {
            return false; // Retorna falso se algum dos parâmetros for nulo
        }

        // Usa equals para comparação de conteúdo de strings
        if (email.equals(this.email) && senha.equals(this.senha)) {
            return true; // Login bem-sucedido
        }

        return false; // Login falhou
    }


    //public int getId() {
     //   return id;
   // }
}


