/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utils.Constantes;
import dao.ManagerImpl.UtilisateurDAOImpl;
import dao.Manager.UtilisateurDAO;
import dao.Entity.Utilisateur;
import function.navigation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author khatari-pc
 */
public class LoginController implements Initializable {
    
    navigation nav = new navigation();
    
   @FXML private Label labelChampObligatoire;
    
    @FXML private TextField login;
    
    @FXML private PasswordField password;
    
    @FXML private Button button;

    UtilisateurDAO utilisateurDAO =new UtilisateurDAOImpl();
    
    public static Utilisateur utilisateur;
    public String nomUtilisateur;
    @FXML
    private Text fermerlibelle;
  
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
       // Utilisateur utilisateur = new Utilisateur ();
        labelChampObligatoire.setText("");
        
        if(login.getText().equalsIgnoreCase("") || password.getText().equalsIgnoreCase("")){
            labelChampObligatoire.setText(Constantes.CHAMP_OBLIGATOIRE);
        }else {
             utilisateur=utilisateurDAO.findUtilisateurByLoginMotPasse(login.getText(), password.getText());
             
             if(utilisateur==null){
                 labelChampObligatoire.setText("Login/Mot de passe Incorrect");
            
             }else {
                 
                  nomUtilisateur = utilisateur.getNom();
                 
                 nav.setUtilisateur(utilisateur);
                    Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    
                    stage2.hide();
                    

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource(nav.getHome()));
                    
                    try {
                        loader.load();
                    } catch (Exception e) {
                    }
                    
                    Parent p = loader.getRoot();
                    Stage stage = new Stage();
                    Scene pp = new Scene(p);
                   Image image= new Image(getClass().getResourceAsStream("/Styles/img/logo.png"));
                    stage.getIcons().add(image);
                    stage.setTitle("Home");
                    stage.setScene(pp);
                    stage.setMaximized(true);
                    stage.show();

             }
            
            
        }
        
       
        
                    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public TextField getLogin() {
        return login;
    }

    public PasswordField getPassword() {
        return password;
    }

    public void setLogin(TextField login) {
        this.login = login;
    }

    public void setPassword(PasswordField password) {
        this.password = password;
    }
      public Label getLabel() {
        return labelChampObligatoire;
    }

    public void setLabel(Label label) {
        this.labelChampObligatoire = label;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    @FXML
    private void fermerAppClicked(MouseEvent event) {
         System.exit(0);
    }
}
