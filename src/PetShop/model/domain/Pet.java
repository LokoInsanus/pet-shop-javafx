package PetShop.model.domain;

public class Pet {
    private int id;
    private String nome;
    private String animal;
    private Cliente dono;
    private String raca;
    private String rga;

    public Pet (){

    }

    public Pet (String nome, String animal, Cliente dono, String raca, String rga){
        this.nome = nome;
        this.animal = animal;
        this.dono = dono;
        this.raca = raca;
        this.rga = rga;
    }

    public Pet (Integer id, String nome, String animal, Cliente dono, String raca, String rga){
        this.id = id;
        this.nome = nome;
        this.animal = animal;
        this.dono = dono;
        this.raca = raca;
        this.rga = rga;
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

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public Cliente getDono() {
        return dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getRga() {
        return rga;
    }

    public void setRga(String rga) {
        this.rga = rga;
    }
}