/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcf4opdracht3;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;



/**
 * FXML Controller class
 *
 * @author Nick van der Mullen
 */
public class GuiController implements Initializable {

    @FXML
    private TableColumn tableTypeName;
    @FXML
    private TableColumn TableDescription;
    @FXML
    private TreeView<GreaterTypes> TreeViewTypes;
    @FXML
    private Label lblSubType;
    @FXML
    private TextField txtSubTypeName;
    @FXML
    private TextField txtFieldDescription;
    @FXML
    private Button btnAdd;
    @FXML
    private Label lblType;
    @FXML
    private Button btnCreate;

    private TreeItem<GreaterTypes> mainType;
    private ObservableList<TreeItem<GreaterTypes>> observableGreaterTypes;
    @FXML
    private TableView TableViewTypes;
    @FXML
    private TextField txtGreaterTypeName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GreaterTypes root = new GreaterTypes("Root");
        mainType = new TreeItem<>(root);
        mainType.setExpanded(true);
        TreeViewTypes.setRoot(mainType);

        tableTypeName.setCellValueFactory(new PropertyValueFactory<Subtype, String>("subTypeName"));
        TableDescription.setCellValueFactory(new PropertyValueFactory<Subtype, String>("description"));

        ArrayList<TreeItem<GreaterTypes>> greaterTypes = new ArrayList<>();
        observableGreaterTypes = FXCollections.observableArrayList(greaterTypes);
        observableGreaterTypes.addListener((ListChangeListener.Change<? extends TreeItem<GreaterTypes>> c) -> {
            if (TreeViewTypes.getSelectionModel().getSelectedItem() == null) {
                mainType.getChildren().setAll(observableGreaterTypes);
            } else {
                TreeItem<GreaterTypes> selectedItem = (TreeItem<GreaterTypes>) TreeViewTypes.getSelectionModel().getSelectedItem();
                selectedItem.getChildren().add(c.getList().get(c.getList().size() - 1));
            }
        });
    }

    @FXML
    public void updateTableView() {
        TreeItem<GreaterTypes> selectedGType = (TreeItem<GreaterTypes>) TreeViewTypes.getSelectionModel().getSelectedItem();
        if(selectedGType != null)
        {
            TableViewTypes.setItems((ObservableList) selectedGType.getValue().getSubtypes());
        }
    }

    @FXML
    public void addSubtype() {
        if (txtSubTypeName.getText().isEmpty() || txtFieldDescription.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a name and/or a description");
            alert.showAndWait();
            return;
        }

        if (TreeViewTypes.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("No greatertype was selected.");
            alert.showAndWait();
            return;
        }

        Subtype newSub = new Subtype(txtSubTypeName.getText(), txtFieldDescription.getText());
        TreeItem<GreaterTypes> selectedGType = (TreeItem<GreaterTypes>) TreeViewTypes.getSelectionModel().getSelectedItem();
        selectedGType.getValue().addSubtype(newSub);
        updateTableView();

    }

    @FXML
    public void CreateGreaterType() {
        if (txtGreaterTypeName.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Typename was empty.");
            alert.showAndWait();
            return;
        }

        TreeItem<GreaterTypes> newGreaterType = new TreeItem<>(new GreaterTypes(txtGreaterTypeName.getText()));
        newGreaterType.setExpanded(true);
        observableGreaterTypes.add(newGreaterType);
        updateTableView();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Greatertype added.");
        alert.showAndWait();

    }

}
