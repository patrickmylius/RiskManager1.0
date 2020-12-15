package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML
    private Tab createRiskTab;

    // --------------------------------------------------{Buttons}------------------------------------------------------

    @FXML
    private Button HomeButtonOkButton;

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
    private TextField homeTextFieldAnalysisName;

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
    public TableView<Risk> table;
    public ObservableList<Risk> data;

    public Controller() throws IOException {
    }

    @FXML
    private TableColumn<?, ?> tablePriority;

    @FXML
    private TableColumn<?, ?> tableDescription;

    @FXML
    private TableColumn<?, ?> tableProbability;

    @FXML
    private TableColumn<?, ?> tableConsequence;



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
        HomeButtonOkButton.setVisible(false);
        homeTextFieldAnalysisName.setVisible(false);

    }

    static void sortRiskList(ObservableList<Risk> x){

        x.sort(Comparator.comparing(o -> o.probability * o.consequenceValue));
        Collections.reverse(x);

    }

    static void setPriority(ObservableList<Risk> x){

        for (int i = 0; i < x.size(); i++) {

            x.get(i).priority = i + 1;

        }

    }

    // -------------------------------------------------{variables}------------------------------------------------------

    boolean noFoundLogin = true; // logIn
    int counter = 1; // add risk analysis


    // ------------------------------------------------{InAppFunctions}-------------------------------------------------
    @FXML
    void logIn(ActionEvent event) throws FileNotFoundException {
        sb.setLength(0);
        input = new Scanner(new FileReader("credentials.txt"));

        checkCreatedUsers();
        input.close();


        String enteredEmail;
        String enteredPassword;

        List<String[]> finalListOfUserDetail = new ArrayList<>();

        String allLogins = sb.toString();

        List<String> listOfUserDetail = new ArrayList<>(Arrays.asList(allLogins.split("----------")));

        listOfUserDetail.remove(listOfUserDetail.get(0));

        for (String s : listOfUserDetail) {
            String[] tmplist = s.split(":");
            finalListOfUserDetail.add(tmplist);
        }

        enteredEmail = logInTextFieldEmail.getText().toLowerCase();
        enteredPassword = logInTextFieldPassword.getText();




        for (String[] strings : finalListOfUserDetail) {

            if ((strings[1].contains(enteredPassword)) && (strings[0].contains(enteredEmail.toLowerCase()))) {
                tabPane.getSelectionModel().select(HomeTab);
                noFoundLogin = false;
                startTextWarning.setText(null);
                break;

            }

        }

        if(noFoundLogin){
            startTextWarning.setText("Wrong email or password");
        }


    }

    @FXML
    void signUp(ActionEvent event) throws IOException {

        input = new Scanner(new FileReader("credentials.txt"));

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
    void goToCreateRisk(ActionEvent event) {
        tabPane.getSelectionModel().select(createRiskTab);
    }


    @FXML
    void HomeEditButton(ActionEvent event) {

    }

    @FXML
    void HomeLogOut(ActionEvent event) {
        logInTextFieldEmail.clear();
        logInTextFieldPassword.clear();
        tabPane.getSelectionModel().select(StartTab);
        noFoundLogin = true;

    }

    @FXML
    void HomeNewAnalysisButton(ActionEvent event) {

        homeTextFieldAnalysisName.setVisible(true);
        HomeButtonOkButton.setVisible(true);
        HomeButtonOkButton.setDisable(false);
        homeTextFieldAnalysisName.setDisable(false);

    }

    @FXML
    void goToEdit(MouseEvent event) {

        tabPane.getSelectionModel().select(editTab);

    }

    @FXML
    void createProject(ActionEvent event) {

        switch (counter) {

            case 1:
                newRiskAnalysis1.setText(homeTextFieldAnalysisName.getText());
                break;
            case 2:
                newRiskAnalysis2.setText(homeTextFieldAnalysisName.getText());
                break;
            case 3:
                newRiskAnalysis3.setText(homeTextFieldAnalysisName.getText());
                break;
            case 4:
                newRiskAnalysis4.setText(homeTextFieldAnalysisName.getText());
                break;
            case 5:
                newRiskAnalysis5.setText(homeTextFieldAnalysisName.getText());
                break;

        }

        HomeButtonOkButton.setVisible(false);
        homeTextFieldAnalysisName.setVisible(false);
        HomeButtonOkButton.setDisable(true);
        homeTextFieldAnalysisName.setDisable(true);
        homeTextFieldAnalysisName.clear();

        counter += 1;

    }


    @FXML
    void createRiskAddRiskToTable(ActionEvent event){

        createRiskTextAreaDescription.getText();
        createRiskTextFieldProbability.getText();
        createRiskTextFieldConsequence.getText();

    }





    // -----------------------------------------------{Hakuna Matata}---------------------------------------------------





}
