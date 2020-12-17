package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller  {

    BufferedWriter output = new BufferedWriter(new FileWriter("credentials.txt",true));
    BufferedWriter saveOutput;
    public ObservableList<Risk> risks = FXCollections.observableArrayList();
    Scanner input = new Scanner(new FileReader("credentials.txt"));
    Scanner loadInput;
    public StringBuilder sb = new StringBuilder();
    public StringBuilder loadSB = new StringBuilder();


    // --------------------------------------------------{ Tabs }-------------------------------------------------------

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
    private Button clearTableButton;

    @FXML
    private Button HomeButtonOkButton;

    @FXML
    private Button backToLoginPage;

    @FXML
    private Button editButtonHome;

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
    private Button createRiskButtonAddToTable;

    @FXML
    private Button editButtonSave;

    @FXML
    private Button editButtonDelete;

    @FXML
    private Button editButtonNew;

    @FXML
    private Button editButtonView;

    @FXML
    private Button viewButtonClose;



    // -------------------------------------------------{Text}----------------------------------------------------------

    @FXML
    private TextField editTextfieldDeleteByPriority;

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

    public Controller() throws IOException {
    }

    @FXML
    public TableColumn<Risk, Integer> tablePriority;

    @FXML
    public TableColumn<Risk, String> tableDescription;

    @FXML
    public TableColumn<Risk, Integer> tableProbability;

    @FXML
    public TableColumn<Risk, Integer> tableConsequence;


    @FXML
    private TableColumn<Risk, String> tableResponseStrategy;

    @FXML
    private TableColumn<Risk, Integer> tableRevisedProbability;

    @FXML
    private TableColumn<Risk, Integer> tableRevisedConsequence;





    // ------------------------------------------------{Functions}------------------------------------------------------


    void checkCreatedUsers(){
        while (input.hasNext()) {
            sb.append(input.next());
        }
    }

    void checkCreatedProjects(){
        while (loadInput.hasNext()) {
            loadSB.append(loadInput.next());
        }
    }

    public void initialize(){

        tabPane.setTabMinWidth(0);
        tabPane.setTabMinHeight(0);
        tabPane.setTabMaxWidth(0);
        tabPane.setTabMaxHeight(0);

        checkCreatedUsers();
        HomeButtonOkButton.setVisible(false);
        homeTextFieldAnalysisName.setVisible(false);

        tablePriority.setCellValueFactory(new PropertyValueFactory<>("Priority"));
        tableDescription.setCellValueFactory(new PropertyValueFactory<>("CaseExplanation"));
        tableProbability.setCellValueFactory(new PropertyValueFactory<>("Probability"));
        tableConsequence.setCellValueFactory(new PropertyValueFactory<>("ConsequenceValue"));

        tableResponseStrategy.setCellValueFactory(new PropertyValueFactory<>("ResponseStrategy"));
        tableRevisedProbability.setCellValueFactory(new PropertyValueFactory<>("RevisedProbability"));
        tableRevisedConsequence.setCellValueFactory(new PropertyValueFactory<>("RevisedConsequenceValue"));


        table.setItems(risks);


        table.setEditable(true);
        tablePriority.setEditable(true);
        tableDescription.setEditable(true);
        tableProbability.setEditable(true);
        tableConsequence.setEditable(true);

        tableResponseStrategy.setEditable(true);
        tableRevisedProbability.setEditable(true);
        tableRevisedConsequence.setEditable(true);

        tableDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        tableProbability.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tableConsequence.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        tableResponseStrategy.setCellFactory(TextFieldTableCell.forTableColumn());
        tableRevisedProbability.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tableRevisedConsequence.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

    }

    static void sortRiskList(){

        risklist.sort(Comparator.comparing(o -> o.sortProbability * o.sortConsequence));
        Collections.reverse(risklist);

    }

    static void setPriority(){

        for (int i = 0; i < risklist.size(); i++) {

            risklist.get(i).priority = i + 1;

        }

    }


    // -------------------------------------------------{variables}------------------------------------------------------

    boolean noFoundLogin = true; // logIn
    int counter = 1; // add risk analysis

    static ArrayList <Risk> risklist = new ArrayList<>();


    // ------------------------------------------------{InAppFunctions}-------------------------------------------------
    @FXML
    void logIn(ActionEvent event) throws FileNotFoundException {
        sb.setLength(0);
        input = new Scanner(new FileReader("credentials.txt"));

        checkCreatedUsers();
        input.close();


        List<String[]> finalListOfUserDetail = new ArrayList<>();

        String allLogins = sb.toString();

        List<String> listOfUserDetail = new ArrayList<>(Arrays.asList(allLogins.split("----------")));

        listOfUserDetail.remove(listOfUserDetail.get(0));

        for (String s : listOfUserDetail) {
            String[] tmplist = s.split(":");
            finalListOfUserDetail.add(tmplist);
        }

        String enteredEmail = logInTextFieldEmail.getText().toLowerCase();
        String enteredPassword = logInTextFieldPassword.getText();


        for (String[] strings : finalListOfUserDetail) {

            if ((strings[1].contains(enteredPassword) && !enteredPassword.equals("")) &&
                    (strings[0].contains(enteredEmail.toLowerCase()) && !enteredEmail.equals(""))) {
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
    void goHome(ActionEvent event) {
        tabPane.getSelectionModel().select(HomeTab);
    }

    @FXML
    void goToLogin(ActionEvent event) {
        tabPane.getSelectionModel().select(StartTab);
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

        risks.clear();

        Risk risk = new Risk(createRiskTextAreaDescription.getText(),
                Integer.parseInt(createRiskTextFieldProbability.getText()),
                        Integer.parseInt(createRiskTextFieldConsequence.getText()));


        risklist.add(risk);

        sortRiskList();
        setPriority();

        for (int i = 0; i < risklist.size(); i++) {
            risks.add(risklist.get(i));
        }

        table.refresh();
        table.setItems(risks);

        tabPane.getSelectionModel().select(editTab);

        createRiskTextAreaDescription.clear();
        createRiskTextFieldProbability.clear();
        createRiskTextFieldConsequence.clear();

    }

    @FXML
    public void changeDescriptionCellEvent(TableColumn.CellEditEvent edittedCell){

        Risk riskSelected = table.getSelectionModel().getSelectedItem();
        riskSelected.setCaseExplanation(edittedCell.getNewValue().toString());


        table.setItems(risks);
        table.refresh();
    }

    @FXML
    public void changeProbabilityCellEvent(TableColumn.CellEditEvent edittedCell){

        Risk riskSelected = table.getSelectionModel().getSelectedItem();
        riskSelected.setProbability(Integer.parseInt(edittedCell.getNewValue().toString()));
        sortRiskList();
        setPriority();



        table.setItems(risks);
        table.refresh();
    }

    @FXML
    public void changeConsequenceCellEvent(TableColumn.CellEditEvent edittedCell){

        Risk riskSelected = table.getSelectionModel().getSelectedItem();
        riskSelected.setConsequenceValue(Integer.parseInt(edittedCell.getNewValue().toString()));
        sortRiskList();
        setPriority();


        table.setItems(risks);
        table.refresh();
    }

    @FXML
    public void changeResponseStrategyCellEvent(TableColumn.CellEditEvent edittedCell){

        Risk riskSelected = table.getSelectionModel().getSelectedItem();
        riskSelected.setResponseStrategy(edittedCell.getNewValue().toString());


        table.setItems(risks);
        table.refresh();
    }

    @FXML
    public void changeRevisedProbabilityCellEvent(TableColumn.CellEditEvent edittedCell){

        Risk riskSelected = table.getSelectionModel().getSelectedItem();
        riskSelected.setRevisedProbability(Integer.parseInt(edittedCell.getNewValue().toString()));
        riskSelected.sortProbability = riskSelected.revisedProbability;
        sortRiskList();
        setPriority();

        table.setItems(risks);
        table.refresh();
    }

    @FXML
    public void changeRevisedConsequenceCellEvent(TableColumn.CellEditEvent edittedCell){

        Risk riskSelected = table.getSelectionModel().getSelectedItem();
        riskSelected.setRevisedConsequenceValue(Integer.parseInt(edittedCell.getNewValue().toString()));
        riskSelected.sortConsequence = riskSelected.revisedConsequenceValue;
        sortRiskList();
        setPriority();


        table.setItems(risks);
        table.refresh();
    }




    void saveMethod() throws IOException {
        saveOutput = new BufferedWriter(new FileWriter("allProjects.txt"));
    }


    @FXML
    void saveProjectChanges(ActionEvent event) throws IOException {

        String projectName = newRiskAnalysis1.getText();

        saveMethod();

        saveOutput.write(projectName + "\n");
        saveOutput.write("----------\n");

        for (int i = 0; i < risklist.size(); i++) {

            saveOutput.write(risklist.get(i).priority + ":");
            saveOutput.write(risklist.get(i).caseExplanation + ":");
            saveOutput.write(risklist.get(i).probability + ":");
            saveOutput.write(risklist.get(i).consequenceValue + ":");
            saveOutput.write(risklist.get(i).responseStrategy + ":");
            saveOutput.write(risklist.get(i).revisedProbability + ":");
            saveOutput.write(risklist.get(i).revisedConsequenceValue + "\n");
            saveOutput.write("----------" + "\n");

        }
        saveOutput.write("<>\n");

        saveOutput.close();

    }



    void loadProjectMethod() throws FileNotFoundException {
        loadInput = new Scanner(new FileReader("allProjects.txt"));
        loadSB.setLength(0);

        checkCreatedProjects();
        loadInput.close();


        int priority;
        String description;
        int probability;
        int consequence;

        List<String[]> finalListOfRiskDetail = new ArrayList<>();
        String allRisks = loadSB.toString();

        List<String> listOfRiskDetail = new ArrayList<>(Arrays.asList(allRisks.split("----------")));

//        listOfRiskDetail.remove(listOfRiskDetail.get(0));


        for (String s : listOfRiskDetail) {
            String[] tmplist = s.split(":");
            finalListOfRiskDetail.add(tmplist);
        }


        newRiskAnalysis1.setText(listOfRiskDetail.get(0));




                System.out.println(listOfRiskDetail.toString());
        System.out.println(finalListOfRiskDetail.toString());

//        for (int i = 0; i < listOfRiskDetail.size(); i++) {
//
//            Risk tmpRisk = new Risk(finalListOfRiskDetail.get(i))
//
//        }

    }

    @FXML
    public void deleteByPriority(ActionEvent actionEvent) {

        int index = Integer.parseInt(editTextfieldDeleteByPriority.getText());
        index = index-1;

        risklist.remove(index);
        risks.remove(index);

        editTextfieldDeleteByPriority.clear();

        sortRiskList();
        setPriority();

        table.refresh();
        table.setItems(risks);

    }

    @FXML
    public void clearTable(ActionEvent actionEvent) {

        risks.clear();
        risklist.clear();

        table.refresh();
        table.setItems(risks);

    }


}
