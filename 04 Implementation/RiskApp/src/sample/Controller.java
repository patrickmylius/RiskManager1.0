package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.util.*;

public class Controller  {



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

        String enteredEmail = logInTextFieldEmail.getText();
        String enteredPassword = logInTextFieldPassword.getText();

        List<String[]> finalListOfUserDetail = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        while(input.hasNext()) {
            sb.append(input.next());
        }
        input.close();

        String allLogins = sb.toString();

        System.out.println(allLogins);

        List<String> listOfUserDetail = new ArrayList<>(Arrays.asList(allLogins.split("----------")));

        listOfUserDetail.remove(listOfUserDetail.get(0));

        for (String s : listOfUserDetail) {

            String[] tmplist = s.split(":");
            finalListOfUserDetail.add(tmplist);

        }


        for (int i = 0; i < finalListOfUserDetail.size(); i++) {

//            if(Objects.equals(Arrays.stream(finalListOfUserDetail.get(i)).filter(enteredEmail::equals).findAny().orElse(null), enteredEmail)){
//                System.out.println("fuck");
//                System.out.println(finalListOfUserDetail.indexOf(Arrays.stream(finalListOfUserDetail.get(i)).filter(enteredEmail::equals).findAny().orElse(null)));
//            }
            System.out.println(Arrays.stream(finalListOfUserDetail.get(i)).filter(enteredEmail::equals).findAny().orElse(null));

        }


    }

    @FXML
    void signUp(ActionEvent event) throws IOException {


        SignUpData signUpData = new SignUpData();

        signUpData.Email = signUpTextFieldEmail.getText();
        signUpData.Password = signUpTextFieldPassword.getText();
        signUpData.VerifyPassword = signUpTextFieldVerifyPassword.getText();

        if( (signUpData.Email.equals("") || signUpData.Password.equals("")) || signUpData.VerifyPassword.equals("")){   // kinda sus

            System.out.println("FUCK");

        } else {

            output.write("\n");
            output.write(signUpData.Email + ":");
//            output.write("\n");
            output.write(signUpData.Password + ":");
//            output.write("\n");
            output.write(signUpData.VerifyPassword);
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




    // -----------------------------------------------{Hakuna Matata}---------------------------------------------------





}
