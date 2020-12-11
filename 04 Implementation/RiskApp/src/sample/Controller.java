package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.*;
import java.util.*;

public class Controller  {

    // saves sign up details
    BufferedWriter output = new BufferedWriter(new FileWriter("credentials.txt",true));
    Scanner input = new Scanner(new FileReader("credentials.txt"));


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
    private Text startTextWarning;


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

    void verifyPassword(){


    }

    // ------------------------------------------------{InAppFunctions}------------------------------------------------------
    @FXML
    void logIn(ActionEvent event) {

        // retry loop in case of wrong credentials

        String enteredEmail = logInTextFieldEmail.getText().toLowerCase();
        String enteredPassword = logInTextFieldPassword.getText();

        List<String[]> finalListOfUserDetail = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        while(input.hasNext()) {
            sb.append(input.next());
        }
        input.close();

        String allLogins = sb.toString();

        List<String> listOfUserDetail = new ArrayList<>(Arrays.asList(allLogins.split("----------")));

        listOfUserDetail.remove(listOfUserDetail.get(0));

        for (String s : listOfUserDetail) {

            String[] tmplist = s.split(":");
            finalListOfUserDetail.add(tmplist);

        }


        for (int i = 0; i < finalListOfUserDetail.size(); i++) {

            if (finalListOfUserDetail.get(i)[0].contains(enteredEmail.toLowerCase())){

                if(finalListOfUserDetail.get(i)[1].contains(enteredPassword)){

                    tabPane.getSelectionModel().select(HomeTab);

                } else {
                    startTextWarning.setText("Wrong Password");
                }

            } else {
                startTextWarning.setText("Wrong Email");

            }

        }

    }

    @FXML
    void signUp(ActionEvent event) throws IOException {

        SignUpData signUpData = new SignUpData();

        String Email = signUpTextFieldEmail.getText().toLowerCase();
        String Password = signUpTextFieldPassword.getText();
        String VerifyPassword = signUpTextFieldVerifyPassword.getText();

        if( (Email.equals("") || Password.equals("")) || VerifyPassword.equals("")){

            System.out.println("FUCK");

        } else {

            output.write("\n");
            output.write(Email + ":");
            output.write(Password);
            output.write("\n");
            output.write("----------");

            output.close();

            tabPane.getSelectionModel().select(StartTab);

        }
    }

    @FXML
    void GoToSignUp(ActionEvent event) {
        tabPane.getSelectionModel().select(SignUpTab);

    }

    @FXML
    void HomeEditButton(ActionEvent event) {

    }

    @FXML
    void HomeLogOut(ActionEvent event) {

    }

    @FXML
    void HomeNewAnalysisButton(ActionEvent event) {

    }




    // -----------------------------------------------{Hakuna Matata}---------------------------------------------------





}
