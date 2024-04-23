package PetShop.model.domain;

public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String rua;
    private String bairro;
    private String numeroCasa;
    private String numeroTelefone;
    private String cpf;

    public Cliente() {
    
    }
    
    public Cliente (String nome, String email, String rua, String bairro, String numeroCasa, String numeroTelefone, String cpf){
        this.nome = nome;
        this.email = email;
        this.rua = rua;
        this.bairro = bairro;
        this.numeroCasa = numeroCasa;
        this.numeroTelefone = numeroTelefone;
        this.cpf = cpf;
    }

    public Cliente (Integer id, String nome, String email, String rua, String bairro, String numeroCasa, String numeroTelefone, String cpf){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.rua = rua;
        this.bairro = bairro;
        this.numeroCasa = numeroCasa;
        this.numeroTelefone = numeroTelefone;
        this.cpf = cpf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}