package PetShop.controller;

import PetShop.model.dao.ClienteDAO;
import PetShop.model.database.Database;
import PetShop.model.database.DatabaseFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import PetShop.model.domain.Cliente;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class FXMLCadClienteController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private TextField fieldNome;
    
    @FXML
    private TextField fieldEmail;
    
    @FXML
    private TextField fieldRua;
    
    @FXML
    private TextField fieldBairro;
    
    @FXML
    private TextField fieldCasa;
    
    @FXML
    private TextField fieldTelefone;
     
    @FXML
    private TextField fieldCPF;
    
    @FXML
    private Button btnSalvar;
    
    private boolean editar;
    private int idEditar;
    
    private boolean boolNome = false;
    private boolean boolEmail = false;
    private boolean boolRua = false;
    private boolean boolBairro = false;
    private boolean boolCasa = false;
    private boolean boolTelefone = false;
    private boolean boolCPF = false;
    
    private final Database database = DatabaseFactory.getDatabase("jdbc");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        
        btnSalvar.setDisable(true);
    }
    
    public void setEditar(boolean editar) {
        this.editar = editar;
    }
    
    public void setIdEditar(int id) {
        this.idEditar = id;
    }
    
    public void setFieldNome(String nome) {
        fieldNome.setText(nome);
    }
    
    public void setFieldEmail(String email) {
        fieldEmail.setText(email);
    }
    
    public void setFieldRua(String rua) {
        fieldRua.setText(rua);
    }
    
    public void setFieldBairro(String bairro) {
        fieldBairro.setText(bairro);
    }
    
    public void setFieldCasa(String casa) {
        fieldCasa.setText(casa);
    }
    
    public void setFieldTelefone(String telefone) {
        fieldTelefone.setText(telefone);
    }
    
    public void setFieldCPF(String cpf) {
        fieldCPF.setText(cpf);
    }
    
    @FXML
    public void handleSalvar() {
        Cliente cliente = new Cliente(
            acharId(),
            fieldNome.getText(),
            fieldEmail.getText(),
            fieldRua.getText(),
            fieldBairro.getText(),
            fieldCasa.getText(),
            fieldTelefone.getText(),
            fieldCPF.getText()
        );
        if(editar) {
            cliente.setId(idEditar);
            clienteDAO.alterar(cliente);
        } else {
            clienteDAO.inserir(cliente);
        }
        try {
            voltarTela();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void handleVoltar() {
        try {
            voltarTela();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    @FXML
    public void releasedNome() {
        String nome = fieldNome.getText();
        if(!nome.isEmpty() || !nome.trim().isEmpty()) {
            boolNome = true;
        }
        if(boolNome && boolEmail && boolRua && boolBairro && boolCasa && boolTelefone && boolCPF) {
            btnSalvar.setDisable(false);
        }
    }
    
    @FXML
    public void releasedEmail() {
        String email = fieldEmail.getText();
        if(!email.isEmpty() || !email.trim().isEmpty()) {
            boolEmail = true;
        }
        if(boolNome && boolEmail && boolRua && boolBairro && boolCasa && boolTelefone && boolCPF) {
            btnSalvar.setDisable(false);
        }
    }
    
    @FXML
    public void releasedRua() {
        String rua = fieldRua.getText();
        if(!rua.isEmpty() || !rua.trim().isEmpty()) {
            boolRua = true;
        }
        if(boolNome && boolEmail && boolRua && boolBairro && boolCasa && boolTelefone && boolCPF) {
            btnSalvar.setDisable(false);
        }
    }
    
    @FXML
    public void releasedBairro() {
        String bairro = fieldBairro.getText();
        if(!bairro.isEmpty() || !bairro.trim().isEmpty()) {
            boolBairro = true;
        }
        if(boolNome && boolEmail && boolRua && boolBairro && boolCasa && boolTelefone && boolCPF) {
            btnSalvar.setDisable(false);
        }
    }
    
    @FXML
    public void releasedCasa() {
        String casa = fieldCasa.getText();
        if(!casa.isEmpty() || !casa.trim().isEmpty()) {
            boolCasa = true;
        }
        if(boolNome && boolEmail && boolRua && boolBairro && boolCasa && boolTelefone && boolCPF) {
            btnSalvar.setDisable(false);
        }
    }
    
    @FXML
    public void releasedTelefone() {
        String telefone = fieldTelefone.getText();
        if(!telefone.isEmpty() || !telefone.trim().isEmpty()) {
            boolTelefone = true;
        }
        if(boolNome && boolEmail && boolRua && boolBairro && boolCasa && boolTelefone && boolCPF) {
            btnSalvar.setDisable(false);
        }
    }
    
    @FXML
    public void releasedCPF() {
        String cpf = fieldCPF.getText();
        if(!cpf.isEmpty() || !cpf.trim().isEmpty()) {
            boolCPF = true;
        }
        if(boolNome && boolEmail && boolRua && boolBairro && boolCasa && boolTelefone && boolCPF) {
            btnSalvar.setDisable(false);
        }
    }
    
    public int acharId() {
        int tamanho = clienteDAO.listar().size();
        for(int i = 1; i <= tamanho; i++) {
            if(i != clienteDAO.listar().get(i - 1).getId()) {
                return i;
            }
        }
        return tamanho + 1;
    }
    
    public void voltarTela() throws IOException {
        AnchorPane anchor;
        if(editar) {
            anchor = FXMLLoader.load(getClass().getResource("/PetShop/view/FXMLListCliente.fxml"));
        } else {
            anchor = FXMLLoader.load(getClass().getResource("/PetShop/view/FXMLMain.fxml"));
        }
        anchorPane.getChildren().setAll(anchor);
    }
}
