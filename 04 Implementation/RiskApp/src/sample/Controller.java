package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;

public class Controller {



    BufferedWriter output = new BufferedWriter(new FileWriter("credentials.txt",true));


    @FXML
    private TabPane tabPane;

    @FXML
    private Tab StartTab;

    @FXML
    private Tab SignUpTab;

    @FXML
    private Tab HomeTab;
    // --------------------------------------------------{Buttons}------------------------------------------------------

    @FXML
    private Button loginButtonLogIn;

    @FXML
    private Button loginButtonSignUp;

    @FXML
    private Button signUpButtonSignUp;

    @FXML
    private Button homeButtonNew;

    @FXML
    private Button homeButtonLogOut;

    @FXML
    private Button homeButtonEdit;

    @FXML
    private Button editButtonEdit;

    @FXML
    private Button editButtonSave;

    @FXML
    private Button editButtonDelete;

    @FXML
    private Button editButtonNew;

    @FXML
    private Button editButtonExport;

    @FXML
    private Button editButtonView;

    @FXML
    private Button viewButtonClose;

    @FXML
    private Button createRiskButtonAddToTable;

    // -------------------------------------------------{Text}----------------------------------------------------------

    @FXML
    private TextField logInTextFieldPassword;

    @FXML
    private TextField logInTextFieldEmail;

    @FXML
    private TextField signUpTextFieldEmail;


    @FXML
    private TextField signUpTextFieldVerifyPassword;


    @FXML
    private TextField signUpTextFieldPassword;


    @FXML
    private TextField createRiskTextFieldProbability;

    @FXML
    private TextField createRiskTextFieldConsequence;

    @FXML
    private TextArea createRiskTextAreaDescription;


    // --------------------------------------------------{Tables}-------------------------------------------------------

    @FXML
    private TableView<?> viewTableView;

    public Controller() throws IOException {
    }


    // ------------------------------------------------{Functions}------------------------------------------------------


    void createRisk(){

        String description = createRiskTextAreaDescription.getText();
        int probability = Integer.parseInt(createRiskTextFieldProbability.getText());
        int consequence = Integer.parseInt(createRiskTextFieldConsequence.getText());

        Risk risk = new Risk(description,probability,consequence);

    }

    // ------------------------------------------------{InAppFunctions}------------------------------------------------------
    @FXML
    void logIn(ActionEvent event) {

    }

    @FXML
    void signUp(ActionEvent event) throws IOException {

        SignUpData signUpData = new SignUpData();
        signUpData.Email = signUpTextFieldEmail.getText();
        signUpData.Password = signUpTextFieldPassword.getText();
        signUpData.VerifyPassword = signUpTextFieldVerifyPassword.getText();

        output.write("\n");
        output.write(signUpData.Email);
        output.write("\n");
        output.write(signUpData.Password);
        output.write("\n");
        output.write(signUpData.VerifyPassword);
        output.write("\n");
        output.write("----------");

        output.close();

        tabPane.getSelectionModel().select(StartTab);
    }

    @FXML
    void GoToSignUp(ActionEvent event) {
        tabPane.getSelectionModel().select(SignUpTab);

    }




    // -----------------------------------------------{Hakuna Matata}---------------------------------------------------





}
