package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller extends sample.Risk {

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

    // -------------------------------------------------{TextFields}----------------------------------------------------

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

    // --------------------------------------------------{Tables}-------------------------------------------------------

    @FXML
    private TableView<?> viewTableView;


    // ------------------------------------------------{Functions}------------------------------------------------------



    // -----------------------------------------------{Hakuna Matata}---------------------------------------------------

    Controller(String caseExp, int prob, int consVal) {
        super(caseExp, prob, consVal);
    }

}
