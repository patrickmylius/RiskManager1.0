package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.*;
import java.util.*;

public class Controller  {

    // saves sign up details
    BufferedWriter output = new BufferedWriter(new FileWriter("credentials.txt",true));
    Scanner input = new Scanner(new FileReader("credentials.txt"));
    public StringBuilder sb = new StringBuilder();



    @FXML
    private TabPane tabPane;

    @FXML
    private Tab StartTab;

    @FXML
    private Tab SignUpTab;


    @FXML
    private Tab editTab;


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
    private Text newRiskAnalysis1;

    @FXML
    private Text newRiskAnalysis2;

    @FXML
    private Text newRiskAnalysis3;

    @FXML
    private Text newRiskAnalysis4;

    @FXML
    private Text newRiskAnalysis5;


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

    void checkCreatedUsers(){
        while (input.hasNext()) {
            sb.append(input.next());
        }
    }

    public void initialize(){
        checkCreatedUsers();
    }

    // -------------------------------------------------{Booleans}------------------------------------------------------

    boolean noFoundLogin = true;

    // ------------------------------------------------{InAppFunctions}-------------------------------------------------
    @FXML
    void logIn(ActionEvent event) {

        input.close();

        List<String[]> finalListOfUserDetail = new ArrayList<>();

        String allLogins = sb.toString();

        List<String> listOfUserDetail = new ArrayList<>(Arrays.asList(allLogins.split("----------")));

        listOfUserDetail.remove(listOfUserDetail.get(0));

        for (String s : listOfUserDetail) {
            String[] tmplist = s.split(":");
            finalListOfUserDetail.add(tmplist);
        }

        System.out.println("button clicked");

        String enteredEmail = logInTextFieldEmail.getText().toLowerCase();
        String enteredPassword = logInTextFieldPassword.getText();


        for (int i = 0; i < finalListOfUserDetail.size(); i++) {

            if ((finalListOfUserDetail.get(i)[1].contains(enteredPassword)) && (finalListOfUserDetail.get(i)[0].contains(enteredEmail.toLowerCase()))) {
                tabPane.getSelectionModel().select(HomeTab);
                noFoundLogin = false;
                break;

            }

        }

        if(noFoundLogin){
            startTextWarning.setText("Wrong email or password");
        }




    }

    @FXML
    void signUp(ActionEvent event) throws IOException {

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

        checkCreatedUsers();
        input.close();

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

        newRiskAnalysis1.setText("analyse1");

    }

    @FXML
    void goToEdit(MouseEvent event) {

        tabPane.getSelectionModel().select(editTab);

    }




    // -----------------------------------------------{Hakuna Matata}---------------------------------------------------





}
