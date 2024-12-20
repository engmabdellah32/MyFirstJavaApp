package myfirstapp;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.StageStyle;
import java.util.Date;
import java.util.List;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Hyperlink already_hyper;

    @FXML
    private ImageView background1;

    @FXML
    private ImageView background_login;

    @FXML
    private ImageView blackscreen;

    @FXML
    private ImageView blackscreen_login;

    @FXML
    private Label create_lbl;

    @FXML
    private Hyperlink dont_hyper;

    @FXML
    private Label email_lbl;

    @FXML
    private TextField email_signup_txtfld;

    @FXML
    private Button login_btn;

    @FXML
    private AnchorPane login_form;

    @FXML
    private Label login_lbl;

    @FXML
    private Label password_lbl;

    @FXML
    private Label password_login_lbl;

    @FXML
    private TextField password_login_txtfld;

    @FXML
    private TextField password_signup_txtfld;

    @FXML
    private Label phone_lbl;

    @FXML
    private TextField phone_signup_txtfld;

    @FXML
    private Button signup_btn;

    @FXML
    private Button signout_btn;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private Label username_lbl;

    @FXML
    private Label username_login_lbl;

    @FXML
    private TextField username_login_txtfld;

    @FXML
    private TextField username_signup_txtfld;

    @FXML
    private Label welcome_login_lbl;

    @FXML
    private Label welcome_signup_lbl;

    @FXML
    private Button minimize_login_btn;

    @FXML
    private Button minimize_signup_btn;

    @FXML
    private Button close_signup_btn;

    @FXML
    private Button close_login_btn;

    @FXML
    private Button addProducts_ImportBtn;

    @FXML
    private Button addProducts_btn;

    @FXML
    private TableColumn<ProductData, String> addProducts_col_productid;

    @FXML
    private TableColumn<ProductData, String> addProducts_col_productname;

    @FXML
    private TableColumn<ProductData, String> addProducts_col_productprice;

    @FXML
    private TableColumn<ProductData, String> addProducts_col_productsize;

    @FXML
    private TableColumn<ProductData, String> addProducts_col_productstatus;

    @FXML
    private TableColumn<ProductData, String> addProducts_col_producttype;

    @FXML
    private AnchorPane addProducts_form;

    @FXML
    private ImageView addProducts_imageView;

    @FXML
    private Button addProducts_productAddBtn;

    @FXML
    private Button addProducts_productDeleteBtn;

    @FXML
    private TextField addProducts_productID;

    @FXML
    private TextField addProducts_productName;

    @FXML
    private TextField addProducts_productPrice;

    @FXML
    private Button addProducts_productResetBtn;

    @FXML
    private ComboBox<?> addProducts_productSize;

    @FXML
    private ComboBox<?> addProducts_productStatus;

    @FXML
    private ComboBox<?> addProducts_productType;

    @FXML
    private Button addProducts_productUpdateBtn;

    @FXML
    private TextField addProducts_search;

    @FXML
    private TableView<ProductData> addProducts_tableView;

    @FXML
    private Button close;

    @FXML
    private Label home_availableProducts;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private AreaChart<?, ?> home_incomeChart;

    @FXML
    private Label home_numberOrder;

    @FXML
    private BarChart<?, ?> home_orderChart;

    @FXML
    private Label home_totalIncome;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private FontAwesomeIcon minimize;

    @FXML
    private TextField orders_amount;

    @FXML
    private Label orders_balance;

    @FXML
    private Button orders_btn;

    @FXML
    private TableColumn<customerData, String> orders_col_price;

    @FXML
    private TableColumn<customerData, String> orders_col_productname;

    @FXML
    private TableColumn<customerData, String> orders_col_producttype;

    @FXML
    private TableColumn<customerData, String> orders_col_quantity;

    @FXML
    private TableColumn<customerData, String> orders_col_size;

    @FXML
    private AnchorPane orders_form;

    @FXML
    private Button orders_payBtn;

    @FXML
    private ComboBox<?> orders_productName;

    @FXML
    private ComboBox<?> orders_productType;

    @FXML
    private Spinner<Integer> orders_quantity;

    @FXML
    private Button orders_receiptBtn;

    @FXML
    private Button orders_resetBtn;

    @FXML
    private ComboBox<?> orders_size;

    @FXML
    private TableView<customerData> orders_tableView;

    @FXML
    private Label orders_total;

    @FXML
    private Label username;

    @FXML
    private Button orders_addBtn;

    private Connection con;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private Image image;

    private double amountP;
    private double balanceP;

    
    public void homeIncomeChart(){
        
        home_incomeChart.getData().clear();
        
        String sql = "SELECT date, SUM(total) FROM customer_receipt GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 6";
        
        con = DatabaseConnection.getCon();
        
        try{
            XYChart.Series chart = new XYChart.Series();
            
            prepare = con.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while (result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }
            home_incomeChart.getData().add(chart);
            
        }catch (Exception e){ e.printStackTrace(); }
    }
    
      public void homeOrdersChart(){
        
        home_orderChart.getData().clear();
        
        String sql = "SELECT date, COUNT(id) FROM customer GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";
        
        con = DatabaseConnection.getCon();
        
        try{
            XYChart.Series chart = new XYChart.Series();
            
            prepare = con.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while (result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }
            home_orderChart.getData().add(chart);
            
        }catch (Exception e){ e.printStackTrace(); }
    }
    
    public void homeDisplayTotalOrders(){
        
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        
        String sql = "SELECT COUNT(id) FROM customer WHERE date ='"+sqlDate+"'";
        
        con = DatabaseConnection.getCon();
        
        int countOrders = 0;
        
        try{
            prepare = con.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while (result.next()){
                countOrders = result.getInt("COUNT(id)");
            }
            
            home_numberOrder.setText(String.valueOf(countOrders));
        }catch (Exception e){ e.printStackTrace(); }
        
    }
    
    public void homeTotalIncome(){
        String sql = "SELECT SUM(total) FROM customer_receipt";
        
        con = DatabaseConnection.getCon();
        
        double totalIncome = 0;
        
        try{
            prepare = con.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while (result.next()){
                totalIncome = result.getDouble("SUM(total)");
            }
            
            home_totalIncome.setText("EGP"+String.valueOf(totalIncome));
            
        }catch (Exception e){ e.printStackTrace(); }
    }
    
    public void homeAvailableProducts(){
        
        String sql = "SELECT COUNT(id) FROM product WHERE status = 'Available' ";
        
        con = DatabaseConnection.getCon();
        
        int countAP = 0;
        
        try{
            prepare = con.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while (result.next()){
                countAP = result.getInt("COUNT(id)");
            }
            
            home_availableProducts.setText(String.valueOf(countAP));
        }catch (Exception e){e.printStackTrace();}
    }
    
    public void ordersReset(){
        customerId();
        String sql = "DELETE FROM customer WHERE customerID = '"+customerid+"'";
        
        con = DatabaseConnection.getCon();
        try{
            if(!orders_tableView.getItems().isEmpty()){
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to reset?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if (option.get().equals(ButtonType.OK)){
                    statement = con.createStatement();
                    statement.executeUpdate(sql);
                    
                    ordersShowListData();
                    
                    totalP = 0;
                    balanceP = 0;
                    amountP = 0;
                    
                    orders_balance.setText("EGP0.0");
                    orders_total.setText("EGP0.0");
                    orders_amount.setText("");
                }
            }
        }catch (Exception e){ e.printStackTrace(); }
        
        
    }
    
    public void ordersAmount() {

        Alert alert;

        if (!orders_amount.getText().isEmpty()) {

            amountP = Double.parseDouble(orders_amount.getText());

            if (totalP > 0) {
                if (amountP >= totalP) {
                    balanceP = (amountP - totalP);

                    orders_balance.setText("EGP" + String.valueOf(balanceP));

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid :3");
                    alert.showAndWait();

                    orders_amount.setText("");
                }
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Invalid :3");
                alert.showAndWait();
            }
        }else {
                alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Invalid :3");
                alert.showAndWait();
            }
    }

    public void orderPay() {
        customerId();
        //orderTotal();

        String sql = "INSERT INTO customer_receipt (customerID, total, amount, balance, date)"
                + "VALUES (?,?,?,?,?)";

        con = DatabaseConnection.getCon();

        try {

            Alert alert;

            if (totalP > 0 || orders_amount.getText().isEmpty() || amountP == 0) {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = con.prepareStatement(sql);
                    prepare.setString(1, String.valueOf(customerid));
                    prepare.setString(2, String.valueOf(totalP));
                    prepare.setString(3, String.valueOf(amountP));
                    prepare.setString(4, String.valueOf(balanceP));

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(5, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Succesfully paid!");
                    alert.showAndWait();

                    ordersShowListData();

                    totalP = 0;
                    balanceP = 0;
                    amountP = 0;

                    orders_balance.setText("EGP0.0");
                    orders_amount.setText("");

                } else {
                    return;
                }
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid :3");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double totalP;

    public void ordersDisplayTotal() {
        customerId();
        String sql = "SELECT SUM(productPrice) FROM customer WHERE customerID = '" + customerid + "'";

        con = DatabaseConnection.getCon();

        try {
            prepare = con.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                totalP = result.getDouble("SUM(productPrice)");
            }

            orders_total.setText("EGP" + String.valueOf(totalP));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ordersAdd() {
        customerId();
        String sql = "INSERT INTO customer (customerID, productType, productName, productSize, productQuantity, productPrice, date)"
                + " VALUES(?,?,?,?,?,?,?)";

        con = DatabaseConnection.getCon();

        try {

            String checkData = "SELECT * FROM product WHERE productName ='"
                    + orders_productName.getSelectionModel().getSelectedItem() + "'";

            double priceData = 0;

            statement = con.createStatement();
            result = statement.executeQuery(checkData);

            if (result.next()) {
                priceData = result.getDouble("price");
            }

            double totalPData = (priceData * qty);

            Alert alert;
            if ((String) orders_productType.getSelectionModel().getSelectedItem() == null
                    || (String) orders_productName.getSelectionModel().getSelectedItem() == null
                    || (String) orders_size.getSelectionModel().getSelectedItem() == null
                    || totalPData == 0) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please choose product first");
                alert.showAndWait();
            } else {

                prepare = con.prepareStatement(sql);
                prepare.setString(1, String.valueOf(customerid));
                prepare.setString(2, (String) orders_productType.getSelectionModel().getSelectedItem());
                prepare.setString(3, (String) orders_productName.getSelectionModel().getSelectedItem());
                prepare.setString(4, (String) orders_size.getSelectionModel().getSelectedItem());
                prepare.setString(5, String.valueOf(qty));

                prepare.setString(6, String.valueOf(totalPData));

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                prepare.setString(7, String.valueOf(sqlDate));

                prepare.executeUpdate();

                ordersShowListData();
                ordersDisplayTotal();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private SpinnerValueFactory<Integer> spinner;

    public void ordersSpinner() {
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);

        orders_quantity.setValueFactory(spinner);
    }

    private int qty;

    public void ordersShowSpinnerValue() {
        qty = orders_quantity.getValue();
    }

    public void ordersListName() {
        ordersListSize();
        String sql = "SELECT productName FROM product WHERE productType = '"
                + orders_productType.getSelectionModel().getSelectedItem()
                + "' and status = 'Available'";

        con = DatabaseConnection.getCon();

        try {
            prepare = con.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("productName"));
            }
            orders_productName.setItems(listData);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ordersListSize() {

        String sql = "SELECT availableSize FROM product WHERE productType = '"
                + orders_productType.getSelectionModel().getSelectedItem()
                + "' and status = 'Available'";
        //+ "and productName= '"+orders_productName.getSelectionModel().getSelectedItem()+'"';

        con = DatabaseConnection.getCon();

        try {
            prepare = con.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("availableSize"));
            }
            orders_size.setItems(listData);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String[] orderListType = {"T-Shirt", "Sports shoes", "Backpack", "Others"};

    public void ordersListType() {
        ordersListName();
        List<String> listT = new ArrayList<>();

        for (String data : orderListType) {
            listT.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listT);
        orders_productType.setItems(listData);
    }

    public ObservableList<customerData> ordersListData() {

        customerId();
        ObservableList<customerData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customer WHERE customerID = '" + customerid + "'";

        con = DatabaseConnection.getCon();

        try {
            customerData customerD;
            prepare = con.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                customerD = new customerData(result.getInt("customerID"),
                        result.getString("productType"),
                        result.getString("productName"),
                        result.getString("productSize"),
                        result.getInt("productQuantity"), result.getDouble("productPrice"),
                        result.getDate("date"));
                listData.add(customerD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<customerData> ordersList;

    public void ordersShowListData() {
        ordersList = ordersListData();

        orders_col_producttype.setCellValueFactory(new PropertyValueFactory<>("productType"));
        orders_col_productname.setCellValueFactory(new PropertyValueFactory<>("productName"));
        orders_col_size.setCellValueFactory(new PropertyValueFactory<>("productSize"));
        orders_col_quantity.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        orders_col_price.setCellValueFactory(new PropertyValueFactory<>("productPrice"));

        orders_tableView.setItems(ordersList);
        ordersDisplayTotal();
    }

    private int customerid;

    public void customerId() {
        String customId = "SELECT * FROM customer";

        con = DatabaseConnection.getCon();

        try {
            prepare = con.prepareStatement(customId);
            result = prepare.executeQuery();

            int checkId = 0;
            while (result.next()) {
                // GET LAST CUSTOMER ID
                customerid = result.getInt("customerID");
            }

            String checkData = "SELECT * FROM customer_receipt";

            statement = con.createStatement();
            result = statement.executeQuery(checkData);

            while (result.next()) {
                checkId = result.getInt("customerID");
            }

            if (customerid == 0) {
                customerid += 1;
            } else if (checkId == customerid) {
                customerid += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductsUpdate() {
               

        String uri = getData.path;
        if(!(uri == null || uri == "") )
       {
            uri = uri.replace("\\", "\\\\");
       }

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "Update product SET productName = '" + addProducts_productName.getText()
                + "', productType = '" + addProducts_productType.getSelectionModel().getSelectedItem()
                + "', availableSize = '" + addProducts_productSize.getSelectionModel().getSelectedItem()
                + "', status = '" + addProducts_productStatus.getSelectionModel().getSelectedItem()
                + "', price = '" + addProducts_productPrice.getText()
                + "', image = '" + uri 
                + "', date = '" + sqlDate + "' WHERE productID = '"
                + addProducts_productID.getText() + "'";

                con = DatabaseConnection.getCon();


        try {
            Alert alert;

            if (addProducts_productID.getText().isEmpty()
                    || addProducts_productType.getSelectionModel().getSelectedItem() == null
                    || addProducts_productName.getText().isEmpty()
                    || addProducts_productSize.getSelectionModel().getSelectedItem() == null
                    || addProducts_productStatus.getSelectionModel().getSelectedItem() == null
                    || addProducts_productPrice.getText().isEmpty()
                    || getData.path.isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you wan to UPDATE Product with ID: " + addProducts_productID.getText() + " ?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = con.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Updated successfully!");
                    alert.showAndWait();

                    addProductsShowListData();
                    addProductsReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductsDelete() {
        String sql = "DELETE FROM product WHERE productID = '" + addProducts_productID.getText() + "'";
        con = DatabaseConnection.getCon();

        try {
            Alert alert;

            if (addProducts_productID.getText().isEmpty()
                    || addProducts_productType.getSelectionModel().getSelectedItem() == null
                    || addProducts_productName.getText().isEmpty()
                    || addProducts_productSize.getSelectionModel().getSelectedItem() == null
                    || addProducts_productStatus.getSelectionModel().getSelectedItem() == null
                    || addProducts_productPrice.getText().isEmpty()
                    || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you wan to DELETE Product with ID: " + addProducts_productID.getText() + " ?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                    statement = con.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Deleted successfully!");
                    alert.showAndWait();

                    addProductsShowListData();
                    addProductsReset();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addProductsAdd() {
        String sql = "INSERT INTO product(productID,productName,productType,availableSize,status,price,image,date)"
                + "VALUES(?,?,?,?,?,?,?,?)";
        con = DatabaseConnection.getCon();

        try {

            Alert alert;

            if (addProducts_productID.getText().isEmpty()
                    || addProducts_productType.getSelectionModel().getSelectedItem() == null
                    || addProducts_productName.getText().isEmpty()
                    || addProducts_productSize.getSelectionModel().getSelectedItem() == null
                    || addProducts_productStatus.getSelectionModel().getSelectedItem() == null
                    || addProducts_productPrice.getText().isEmpty()
                    || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {
                //CHECK IF THE PRODUCT ID ALREADY EXISTS
                String checkData = "SELECT productID from product WHERE productID = '"
                        + addProducts_productID.getText() + "'";

                statement = con.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Product ID: " + addProducts_productID.getText() + " already exists!");
                    alert.showAndWait();
                } else {

                    prepare = con.prepareStatement(sql);
                    prepare.setString(1, addProducts_productID.getText());
                    prepare.setString(2, addProducts_productName.getText());
                    prepare.setString(3, (String) addProducts_productType.getSelectionModel().getSelectedItem());
                    prepare.setString(4, (String) addProducts_productSize.getSelectionModel().getSelectedItem());
                    prepare.setString(5, (String) addProducts_productStatus.getSelectionModel().getSelectedItem());
                    prepare.setString(6, addProducts_productPrice.getText());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");
                    prepare.setString(7, uri);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(8, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    //To update table view
                    addProductsShowListData();

                    //Clear fields
                    addProductsReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductsReset() {
        addProducts_productID.setText("");
        addProducts_productName.setText("");
        addProducts_productType.getSelectionModel().clearSelection();
        addProducts_productSize.getSelectionModel().clearSelection();
        addProducts_productStatus.getSelectionModel().clearSelection();
        addProducts_productPrice.setText("");
        addProducts_imageView.setImage(null);
        getData.path = "";
    }

    public void addProductsImportImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image File", "*jpg", "*png"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 115, 128, false, true);
            addProducts_imageView.setImage(image);
        }
    }

    private String[] listType = {"T-Shirt", "Sports shoes", "Backpack", "Others"};

    public void addProductsListType() {
        List<String> listT = new ArrayList<>();

        for (String data : listType) {
            listT.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listT);
        addProducts_productType.setItems(listData);
    }

    private String[] listStatus = {"Available", "Not available"};

    public void addProductsListStatus() {
        List<String> listS = new ArrayList<>();

        for (String data : listStatus) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        addProducts_productStatus.setItems(listData);
    }

    private String[] listSize = {"All sizes", "Only non-plus sizes", "One size"};

    public void addProductsListSize() {
        List<String> listZ = new ArrayList<>();

        for (String data : listSize) {
            listZ.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listZ);
        addProducts_productSize.setItems(listData);
    }

    public void addProductsSearch() {
        FilteredList<ProductData> filter = new FilteredList<>(addProductsList, e -> true);
        addProducts_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicatProductData -> {
                if (newValue.isEmpty()) {
                    return true; // Return all items if the search field is empty
                }
                String key = newValue.toLowerCase();
                return String.valueOf(predicatProductData.getProductID()).contains(key)
                        || predicatProductData.getProductName().toLowerCase().contains(key)
                        || predicatProductData.getProductType().toLowerCase().contains(key)
                        || predicatProductData.getStatus().toLowerCase().contains(key)
                        || predicatProductData.getAvailableSize().toLowerCase().contains(key)
                        || String.valueOf(predicatProductData.getProductID()).contains(key);

            });
        });

        SortedList<ProductData> sortData = new SortedList<>(filter);
        sortData.comparatorProperty().bind(addProducts_tableView.comparatorProperty());
        addProducts_tableView.setItems(sortData);

    }

    public void loginAdmin() {
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";

        con = DatabaseConnection.getCon();

        try {
            prepare = con.prepareStatement(sql);
            prepare.setString(1, username_login_txtfld.getText());
            prepare.setString(2, password_login_txtfld.getText());

            result = prepare.executeQuery();
            Alert alert;
            if (username_login_txtfld.getText().isEmpty() || password_login_txtfld.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields!");
                alert.showAndWait();
            } else {
                if (result.next()) {
                    // if correct
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Logged in successfully!");
                    alert.showAndWait();

                    int x = 1;
                    if (x == 1) {
                        login_form.setVisible(false);
                        main_form.setVisible(true);
                        home_btn.setStyle("-fx-background-color:#8E2527");
                        orders_btn.setStyle("-fx-backround-color:transparent");
                        addProducts_btn.setStyle("-fx-background-color:transparent");

                    }

                } else {
                    // if wrong
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username or password!");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchForm(ActionEvent ev) {
        if (ev.getSource() == already_hyper) {
            login_form.setVisible(true);
            signup_form.setVisible(false);
        } else if (ev.getSource() == dont_hyper) {
            login_form.setVisible(false);
            signup_form.setVisible(true);
        }
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close() {
        System.exit(0);
    }
    
    public void signup() {
        String sql = "insert into admin (username,email, phone_number,password) values (?,?,?,?)";

        con = DatabaseConnection.getCon();
        try {
            prepare = con.prepareStatement(sql);
            prepare.setString(1, username_signup_txtfld.getText());
            prepare.setString(2, email_signup_txtfld.getText());
            prepare.setString(3, phone_signup_txtfld.getText());
            prepare.setString(4, password_signup_txtfld.getText());


            Alert alert;
            if (username_signup_txtfld.getText().isEmpty() || email_signup_txtfld.getText().isEmpty() || phone_signup_txtfld.getText().isEmpty() || password_signup_txtfld.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else if (password_signup_txtfld.getText().length() < 5) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Password must be at least 5 characters long. Please enter a valid password.");
                alert.showAndWait();

            } else {

                prepare.execute();
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully created a new account!");
                alert.showAndWait();

                username_signup_txtfld.setText("");
                email_signup_txtfld.setText("");
                phone_signup_txtfld.setText("");
                password_signup_txtfld.setText("");
                login_form.setVisible(true);
                signup_form.setVisible(false);
            }

        } catch (Exception e) {
            System.out.println(e);

        }
    }

    public void switchDashForms(ActionEvent ev) {
        if (ev.getSource() == home_btn) {
            home_form.setVisible(true);
            orders_form.setVisible(false);
            addProducts_form.setVisible(false);
            home_btn.setStyle("-fx-background-color:#8E2527");
            orders_btn.setStyle("-fx-backround-color:transparent");
            addProducts_btn.setStyle("-fx-background-color:transparent");
                 
            homeDisplayTotalOrders();
            homeTotalIncome();
            homeAvailableProducts();
            homeIncomeChart();
            homeOrdersChart();
            
        } else if (ev.getSource() == orders_btn) {
            home_form.setVisible(false);
            orders_form.setVisible(true);
            addProducts_form.setVisible(false);
            orders_btn.setStyle("-fx-background-color:#8E2527");
            home_btn.setStyle("-fx-backround-color:transparent");
            addProducts_btn.setStyle("-fx-background-color:transparent");

            ordersShowListData();
            ordersListType();
            ordersListSize();
            ordersListName();
            ordersSpinner();

        } else if (ev.getSource() == addProducts_btn) {
            home_form.setVisible(false);
            orders_form.setVisible(false);
            addProducts_form.setVisible(true);

            addProducts_btn.setStyle("-fx-background-color:#8E2527");
            orders_btn.setStyle("-fx-backround-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");

            addProductsShowListData();
            addProductsListStatus();
            addProductsListType();
            addProductsListSize();
            addProductsSearch();
        }
    }

    public void logout(ActionEvent ev) {
        if (ev.getSource() == signout_btn) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to signout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                login_form.setVisible(true);
                main_form.setVisible(false);
            }

        }
    }

    private ObservableList<ProductData> addProductsList;

    public void addProductsShowListData() {
        addProductsList = addProductsListData();

        addProducts_col_productid.setCellValueFactory(new PropertyValueFactory<>("productID"));
        addProducts_col_productname.setCellValueFactory(new PropertyValueFactory<>("productName"));
        addProducts_col_producttype.setCellValueFactory(new PropertyValueFactory<>("productType"));
        addProducts_col_productsize.setCellValueFactory(new PropertyValueFactory<>("availableSize"));
        addProducts_col_productstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        addProducts_col_productprice.setCellValueFactory(new PropertyValueFactory<>("price"));

        addProducts_tableView.setItems(addProductsList);
    }

    public ObservableList<ProductData> addProductsListData() {

        ObservableList<ProductData> productList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM product";
        con = DatabaseConnection.getCon();

        try {
            prepare = con.prepareStatement(sql);
            result = prepare.executeQuery();
            ProductData prodD;
            while (result.next()) {
                prodD = new ProductData(result.getInt("productId"),
                        result.getString("productName"),
                        result.getString("productType"),
                        result.getString("availableSize"),
                        result.getString("status"),
                        result.getDouble("price"), result.getString("image"), result.getDate("date"));

                productList.add(prodD);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public void addProductsSelect() {
        ProductData prodD = addProducts_tableView.getSelectionModel().getSelectedItem();
        int num = addProducts_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addProducts_productID.setText(String.valueOf(prodD.getProductID()));
        addProducts_productName.setText(prodD.getProductName());
        addProducts_productPrice.setText(String.valueOf(prodD.getPrice()));

        String uri = "file:" + prodD.getImage();

        image = new Image(uri, 115, 128, false, true);
        addProducts_imageView.setImage(image);
        getData.path = prodD.getImage();

    }

 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     
        homeDisplayTotalOrders();
        homeTotalIncome();
        homeAvailableProducts();
        homeIncomeChart();
        homeOrdersChart();

        // To show the data on tableview
        addProductsShowListData();
        addProductsListStatus();
        addProductsListType();
        addProductsListSize();
        ordersListType();
        ordersShowListData();
        ordersListSize();
        ordersListName();
        ordersSpinner();
        ordersDisplayTotal();

    }

}
