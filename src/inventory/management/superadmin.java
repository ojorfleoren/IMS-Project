package inventory.management;



import java.beans.Statement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author OJT-21
 */
public class superadmin extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rst = null;

public superadmin() {
    
        initComponents();
        conn = DBConnection.connectDB();
        setExtendedState(superadmin.MAXIMIZED_BOTH); 
        //Table Sizes
        tblDataInfo.getColumnModel().getColumn(0).setPreferredWidth(15);
        tblDataInfo.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblDataInfo.getColumnModel().getColumn(5).setPreferredWidth(60);
        tblDataInfo.getColumnModel().getColumn(6).setPreferredWidth(40);
        tblDataInfo.getColumnModel().getColumn(7).setPreferredWidth(40);
        tblDataInfo.getColumnModel().getColumn(8).setPreferredWidth(30);
        tblStock.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblStock.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblChecking.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblChecking.getColumnModel().getColumn(1).setPreferredWidth(5);
        tblChecking.getColumnModel().getColumn(2).setPreferredWidth(20);
        tblChecking.getColumnModel().getColumn(7).setPreferredWidth(10);
        tblChecking.getColumnModel().getColumn(8).setPreferredWidth(30);
        
    }
//ADD item functionality
public void insertItem() {
        try {
            // Validate fields before proceeding
            if (!validateFields()) {
                return; // Stop execution if fields are not filled up
            }

            String message = "Are you sure you want to add this item?";
            int option = JOptionPane.showConfirmDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                String sql = "INSERT INTO Stock (SerialNo, ItemName, Model, Specification, Category, Brand, Status, Qty) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                pst = conn.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(txtSerial.getText()));
                pst.setString(2, txtItemName.getText());
                pst.setString(3, txtModel.getText());
                pst.setString(4, txtSpecification.getText());
                pst.setString(5, cbSCategory.getSelectedItem().toString());
                pst.setString(6, txtBrand.getText());
                pst.setString(7, cbSStatus.getSelectedItem().toString());
                pst.setInt(8, Integer.parseInt(txtQty.getText()));

                pst.execute();
                JOptionPane.showMessageDialog(this, "Item added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                clear();
                displayDataItems();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error adding item to the database.");
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }   
//Delete Item Functionality
public void delete_tblStock() {
    try {
        int selectedRow = tblStock.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int itemID = (int) tblStock.getValueAt(selectedRow, 0);

        String message = "Are you sure you want to delete this item?";
        int option = JOptionPane.showConfirmDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM Stock WHERE ItemID = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, itemID);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Item deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            displayDataItems(); // Refresh the JTable after deletion
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error deleting item from the database.");
    } finally {
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
//Delete End
//Transfer Functionlity
public void transferSelectedItem() {
    try {
        int selectedRow = tblStock.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to transfer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int itemID = (int) tblStock.getValueAt(selectedRow, 0);
        int currentQty = (int) tblStock.getValueAt(selectedRow, 8); // Assuming quantity is at index 8

        String quantityStr = JOptionPane.showInputDialog(this, "Enter quantity to transfer:", "Transfer Quantity", JOptionPane.PLAIN_MESSAGE);

        if (quantityStr == null || quantityStr.trim().isEmpty()) {
            return; // User canceled or entered an empty quantity
        }
        
        int transferQty = Integer.parseInt(quantityStr);

        if (transferQty <= 0 || transferQty > currentQty) {
            JOptionPane.showMessageDialog(this, "Invalid quantity to transfer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Combo box for selecting the destination area
        JComboBox<String> areaComboBox = new JComboBox<>(new String[]{"Stock","Checking", "Return", "Repair", "Disposal"});
        JLabel areaLabel = new JLabel("Select the destination area:");
        Object[] areaDialog = {areaLabel, areaComboBox};

        int areaOption = JOptionPane.showConfirmDialog(this, areaDialog, "Select Destination Area", JOptionPane.OK_CANCEL_OPTION);

        if (areaOption == JOptionPane.CANCEL_OPTION) {
            return; // User canceled
        }

        int selectedAreaIndex = areaComboBox.getSelectedIndex();
        String selectedArea = getDestinationTableName(selectedAreaIndex);

        // Input dialog for the transfer date
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(dateModel);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        JLabel dateLabel = new JLabel("Select the transfer date:");
        Object[] dateDialog = {dateLabel, dateSpinner};

        int dateOption = JOptionPane.showConfirmDialog(this, dateDialog, "Select Transfer Date", JOptionPane.OK_CANCEL_OPTION);

        if (dateOption == JOptionPane.CANCEL_OPTION) {
            return; // User canceled
        }

        java.sql.Date transferDate = new java.sql.Date(((java.util.Date) dateSpinner.getValue()).getTime());

        String message = "Are you sure you want to transfer " + transferQty + " items?";
        int option = JOptionPane.showConfirmDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            // Update quantity in the Stock table
            String updateSql = "UPDATE Stock SET Qty = ? WHERE ItemID = ?";
            try (PreparedStatement updatePst = conn.prepareStatement(updateSql)) {
                updatePst.setInt(1, currentQty - transferQty);
                updatePst.setInt(2, itemID);
                updatePst.executeUpdate();
            }

            // Insert a new record into the destination table
            String insertSql = "INSERT INTO " + selectedArea + " (ItemID, Qty, Date) VALUES (?, ?, ?)";
            try (PreparedStatement insertPst = conn.prepareStatement(insertSql)) {
                insertPst.setInt(1, itemID);
                insertPst.setInt(2, transferQty);
                insertPst.setDate(3, transferDate);
                insertPst.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, "Transfer completed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            displayCombinedData(); // Refresh the JTable after transfer
            displayCheckingData(); // Display Checking data
        }
    } catch (SQLException | NumberFormatException e) {
        e.printStackTrace();
        System.err.println("Error during item transfer.");
    }
}
//END Transfer
//Transfer Item from the Checking table
public void transferCheckingItem(String sourceTable) {
    try {
        int selectedRow = tblChecking.getSelectedRow(); // Assuming you have a JTable for each source table

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to transfer from the " + sourceTable + " table.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int itemID = -1;
        int checkedQty = -1;
        // Assuming different tables have different column indices for ItemID and Qty
        switch (sourceTable) {
            case "Checking":
                itemID = (int) tblChecking.getValueAt(selectedRow, 1); // Assuming ItemID is at index 1 in Checking table
                checkedQty = (int) tblChecking.getValueAt(selectedRow, 7); // Assuming Qty is at index 7 in Checking table
                break;
            case "Return":
                itemID = (int) tblReturn.getValueAt(selectedRow, 1); // Assuming ItemID is at index 1 in Return table
                checkedQty = (int) tblReturn.getValueAt(selectedRow, 7); // Assuming CheckedQty is at index 7 in Return table
                break;
            case "Repair":
                itemID = (int) tblRepair.getValueAt(selectedRow, 1); // Assuming ItemID is at index 1 in Repair table
                checkedQty = (int) tblRepair.getValueAt(selectedRow, 7); // Assuming CheckedQty is at index 7 in Repair table
                break;
            case "Disposal":
                itemID = (int) tblDisposal.getValueAt(selectedRow, 1); // Assuming ItemID is at index 1 in Disposal table
                checkedQty = (int) tblDisposal.getValueAt(selectedRow, 7); // Assuming CheckedQty is at index 7 in Disposal table
                break;
            // Add cases for Repair and Disposal tables if needed
        }

        // Combo box for selecting the destination area
        JComboBox<String> areaComboBox = new JComboBox<>(new String[]{"Checking", "Return", "Repair", "Disposal"});
        JLabel areaLabel = new JLabel("Select the destination area:");
        Object[] areaDialog = {areaLabel, areaComboBox};

        int areaOption = JOptionPane.showConfirmDialog(this, areaDialog, "Select Destination Area", JOptionPane.OK_CANCEL_OPTION);

        if (areaOption == JOptionPane.CANCEL_OPTION) {
            return; // User canceled
        }

        String selectedArea = (String) areaComboBox.getSelectedItem();

        // Input dialog for the return date
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(dateModel);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        JLabel dateLabel = new JLabel("Select the return date:");
        Object[] dateDialog = {dateLabel, dateSpinner};

        int dateOption = JOptionPane.showConfirmDialog(this, dateDialog, "Select Return Date", JOptionPane.OK_CANCEL_OPTION);

        if (dateOption == JOptionPane.CANCEL_OPTION) {
            return; // User canceled
        }

        java.sql.Date returnDate = new java.sql.Date(((java.util.Date) dateSpinner.getValue()).getTime());

        String message = "Are you sure you want to transfer this item from the " + sourceTable + " table to the " + selectedArea + " table?";
        int option = JOptionPane.showConfirmDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            // Insert a new record into the selected destination table
            String insertSql = "INSERT INTO " + selectedArea + " (ItemID, Qty, ReturnDate) VALUES (?, ?, ?)";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:ITEMS.db");
                 PreparedStatement insertPst = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                insertPst.setInt(1, itemID);
                insertPst.setInt(2, checkedQty);
                insertPst.setDate(3, returnDate);
                insertPst.executeUpdate();

                // Retrieve generated ReturnID
                ResultSet rs = insertPst.getGeneratedKeys();
                int returnID = -1;
                if (rs.next()) {
                    returnID = rs.getInt(1);
                }

                // Display information
                JOptionPane.showMessageDialog(this, "Transfer completed successfully.\n\n"
                        + "ReturnID: " + returnID + "\n"
                        + "ItemID: " + itemID + "\n"
                        + "Serial Number: " + getItemSerialNumber(itemID) + "\n"
                        + "Item Name: " + getItemName(itemID) + "\n"
                        + "Category: " + getCategory(itemID) + "\n"
                        + "Brand: " + getBrand(itemID) + "\n"
                        + "Status: " + getStatus(itemID) + "\n"
                        + "Checked Quantity: " + checkedQty + "\n"
                        + "Return Date: " + returnDate.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
            }

            // Remove the item from the source table
            String deleteSql = "DELETE FROM " + sourceTable + " WHERE ItemID = ?";
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:ITEMS.db");
                 PreparedStatement deletePst = conn.prepareStatement(deleteSql)) {
                deletePst.setInt(1, itemID);
                deletePst.executeUpdate();
            }

            // Refresh the corresponding table after transfer
            switch (sourceTable) {
                case "Checking":
                    displayCheckingData();
                    break;
                case "Return":
                    displayReturnData();
                    break;
                case "Repair":
                    displayRepairData();
                    break;
                case "Disposal":
                    displayDisposalData();
                    break;
                // Add cases for Repair and Disposal tables if needed
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error during item transfer.");
    }
}
//End TransferChk
//Display the from 
public void displayReturnData() {
    DefaultTableModel model = (DefaultTableModel) tblReturn.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:ITEMS.db");

        String sql = "SELECT r.ReturnID, r.ItemID, s.SerialNo, s.ItemName, s.Category, s.Brand, s.Status, c.Qty AS CheckedQty, r.Qty AS ReturnedQty, r.ReturnDate " +
                     "FROM Return r " +
                     "INNER JOIN Checking c ON r.ItemID = c.ItemID " +
                     "INNER JOIN Stock s ON r.ItemID = s.ItemID";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int returnID = rst.getInt("ReturnID");
                int itemID = rst.getInt("ItemID");
                int serialNumber = rst.getInt("SerialNo");
                String itemName = rst.getString("ItemName");

                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                String status = rst.getString("Status");
                int checkedQty = rst.getInt("CheckedQty");
                int returnedQty = rst.getInt("ReturnedQty");
                Date returnDate = rst.getDate("ReturnDate");

                // Add a row to the table with all the information
                model.addRow(new Object[]{returnID, itemID, serialNumber, itemName, category, brand, status, checkedQty, returnedQty, returnDate});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving return data from the database.");
    }
}
private String getItemSerialNumber(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:ITEMS.db");
        String sql = "SELECT SerialNo FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("SerialNo");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item serial number.");
    }
    return null;
}

private String getItemName(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:ITEMS.db");
        String sql = "SELECT ItemName FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("ItemName");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item name.");
    }
    return null;
}

private String getCategory(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:ITEMS.db");
        String sql = "SELECT Category FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Category");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item category.");
    }
    return null;
}

private String getBrand(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:ITEMS.db");
        String sql = "SELECT Brand FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Brand");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item brand.");
    }
    return null;
}

private String getStatus(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:ITEMS.db");
        String sql = "SELECT Status FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Status");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item status.");
    }
    return null;
}


private void transferItems(int itemID, int currentQty, int transferQty, String sourceTable, String sourceArea, String destinationTable, String destinationArea, java.sql.Date transferDate) {
    try {
        // Update quantity in the source table
        String updateSourceSql = "UPDATE " + sourceTable + " SET Qty = ? WHERE ItemID = ? AND Area = ?";
        try (PreparedStatement updatePst = conn.prepareStatement(updateSourceSql)) {
            updatePst.setInt(1, currentQty - transferQty);
            updatePst.setInt(2, itemID);
            updatePst.setString(3, sourceArea);
            updatePst.executeUpdate();
        }

        // Insert a new record into the destination table
        String insertDestinationSql = "INSERT INTO " + destinationTable + " (ItemID, Qty, Area, Date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement insertPst = conn.prepareStatement(insertDestinationSql)) {
            insertPst.setInt(1, itemID);
            insertPst.setInt(2, transferQty);
            insertPst.setString(3, destinationArea);
            insertPst.setDate(4, transferDate);
            insertPst.executeUpdate();
        }

        JOptionPane.showMessageDialog(this, "Transfer completed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        // Refresh the JTables after transfer based on your specific requirements
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error during item transfer.");
    }
}
public void displayCombinedData() {
    DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:ITEMS.db");

        String sql = "SELECT s.ItemID, s.SerialNo, s.ItemName, s.Model, s.Category, s.Specification, s.Brand, s.Status, s.Qty, " +
                     "c.Date AS CheckingDate " +
                     "FROM Stock s " +
                     "RIGHT JOIN Checking c ON s.ItemID = c.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int itemID = rst.getInt("ItemID");
                int serialNumber = rst.getInt("SerialNo");
                String itemName = rst.getString("ItemName");
                String modelValue = rst.getString("Model");
                String category = rst.getString("Category");
                String specification = rst.getString("Specification");
                String brand = rst.getString("Brand");
                String status = rst.getString("Status");
                int quantity = rst.getInt("Qty");

                // Retrieve dates from different tables
                Date checkingDate = rst.getDate("CheckingDate");

                // Add a row to the table with all the information
                model.addRow(new Object[]{itemID, serialNumber, itemName, modelValue, category, specification, brand, status, quantity, checkingDate});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving combined data from the database.");
    }
}
public void displayChecking() {
    DefaultTableModel model = (DefaultTableModel) tblChecking.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:ITEMS.db");

        String sql = "SELECT s.ItemID, s.SerialNo, s.ItemName, s.Model, s.Category, s.Specification, s.Brand, s.Status, s.Qty, " +
                     "c.Date AS CheckingDate " +
                     "FROM Stock s " +
                     "LEFT JOIN Return c ON s.ItemID = c.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int itemID = rst.getInt("ItemID");
                int serialNumber = rst.getInt("SerialNo");
                String itemName = rst.getString("ItemName");
                String modelValue = rst.getString("Model");
                String category = rst.getString("Category");
                String specification = rst.getString("Specification");
                String brand = rst.getString("Brand");
                String status = rst.getString("Status");
                int quantity = rst.getInt("Qty");

                // Retrieve dates from different tables
                Date returnDate = rst.getDate("ReturnDate");

                // Add a row to the table with all the information
                model.addRow(new Object[]{itemID, serialNumber, itemName, modelValue, category, specification, brand, status, quantity, returnDate});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving combined data from the database.");
    }
}
// Helper method to get the destination table name based on the user's selection
private String getDestinationTableName(int destinationTableIndex) {
    switch (destinationTableIndex) {
        case 0:
            return "Stock";
        case 1:
            return "Checking";
        case 2:
            return "Return";
        case 3:
            return "Repair";
        case 4:
            return "Disposal";
        default:
            throw new IllegalArgumentException("Invalid destination table index");
    }
}

public void displayCheckingData() {
    DefaultTableModel checkingModel = (DefaultTableModel) tblChecking.getModel();
    checkingModel.setRowCount(0); // Clear existing rows
    try {
        String sql = "SELECT c.CheckingID, c.ItemID, s.SerialNo, s.ItemName, s.Category, s.Brand, s.Status, c.Qty AS TransferredQty, c.Date " +
                     "FROM Checking c " +
                     "LEFT JOIN Stock s ON c.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int checkingID = rst.getInt("CheckingID");
                int itemID = rst.getInt("ItemID");
                int serialNo = rst.getInt("SerialNo");
                String itemName = rst.getString("ItemName");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                String status = rst.getString("Status");
                int transferredQty = rst.getInt("TransferredQty");
                Date date = rst.getDate("Date");

                // Add a row to the Checking table with all the information
                checkingModel.addRow(new Object[]{checkingID, itemID, serialNo, itemName, category, brand, status, transferredQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Checking data from the database.");
    }
}
public void displayReturndata() {
    DefaultTableModel returnModel = (DefaultTableModel) tblReturn.getModel();
    returnModel.setRowCount(0); // Clear existing rows

    try {
        String sql = "SELECT r.ReturnID, r.ItemID, s.SerialNo, s.ItemName, s.Category, s.Brand, s.Status, r.Qty AS ReturnedQty, r.ReturnDate " +
                     "FROM Return r " +
                     "LEFT JOIN Stock s ON r.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int returnID = rst.getInt("ReturnID");
                int itemID = rst.getInt("ItemID");
                int serialNo = rst.getInt("SerialNo");
                String itemName = rst.getString("ItemName");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                String status = rst.getString("Status");
                int returnedQty = rst.getInt("ReturnedQty");
                Date date = rst.getDate("ReturnDate");

                // Add a row to the Return table with all the information
                returnModel.addRow(new Object[]{returnID, itemID, serialNo, itemName, category, brand, status, returnedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Return data from the database.");
    }
}
// Similarly, create methods for Repair and Disposal areas
public void displayRepairData() {
    DefaultTableModel repairModel = (DefaultTableModel) tblRepair.getModel();
    repairModel.setRowCount(0); // Clear existing rows

    try {
        // Modify the SQL query for Repair area
        String sql = "SELECT rep.RepairID, rep.ItemID, s.SerialNo, s.ItemName, s.Category, s.Brand, s.Status, rep.Qty AS RepairedQty, rep.RepairDate " +
                     "FROM Repair rep " +
                     "LEFT JOIN Stock s ON rep.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int repairID = rst.getInt("RepairID");
                int itemID = rst.getInt("ItemID");
                int serialNo = rst.getInt("SerialNo");
                String itemName = rst.getString("ItemName");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                String status = rst.getString("Status");
                int repairedQty = rst.getInt("RepairedQty");
                Date date = rst.getDate("RepairDate");

                // Add a row to the Repair table with all the information
                repairModel.addRow(new Object[]{repairID, itemID, serialNo, itemName, category, brand, status, repairedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Repair data from the database.");
    }
}
public void displayDisposalData() {
    DefaultTableModel disposalModel = (DefaultTableModel) tblDisposal.getModel();
    disposalModel.setRowCount(0); // Clear existing rows

    try {
        // Modify the SQL query for Disposal area
        String sql = "SELECT d.DisposalID, d.ItemID, s.SerialNo, s.ItemName, s.Category, s.Brand, s.Status, d.Qty AS DisposedQty, d.Date " +
                     "FROM Disposal d " +
                     "LEFT JOIN Stock s ON d.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int disposalID = rst.getInt("DisposalID");
                int itemID = rst.getInt("ItemID");
                int serialNo = rst.getInt("SerialNo");
                String itemName = rst.getString("ItemName");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                String status = rst.getString("Status");
                int disposedQty = rst.getInt("DisposedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Disposal table with all the information
                disposalModel.addRow(new Object[]{disposalID, itemID, serialNo, itemName, category, brand, status, disposedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Disposal data from the database.");
    }
}
    //Validation method
private boolean validateFields() {
        if (txtSerial.getText().isEmpty() || txtItemName.getText().isEmpty() ||
            txtModel.getText().isEmpty() || txtSpecification.getText().isEmpty() ||
            txtBrand.getText().isEmpty() || txtQty.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Validation failed
        }

        return true; // Validation passed
    }
    //Clear funtionality
public void clear(){
        txtSerial.setText("");
        txtItemName.setText("");
        txtModel.setText("");
        txtSpecification.setText("");
        cbSCategory.setSelectedIndex(0);
        txtBrand.setText("");
        cbSStatus.setSelectedIndex(0);
        txtQty.setText("");
    } 
    //Display data from database to ADD form table
public void displayDataItems() {
    DefaultTableModel model = (DefaultTableModel) tblDataInfo.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        String sql = "SELECT * FROM Stock";
        pst = conn.prepareStatement(sql);
        rst = pst.executeQuery();

        while (rst.next()) {
            int ItemID = rst.getInt("ItemID");
            int serialNumber = rst.getInt("SerialNo");
            String itemName = rst.getString("ItemName");
            String modelValue = rst.getString("Model");
            String specification = rst.getString("Specification");
            String category = rst.getString("Category");
            String brand = rst.getString("Brand");
            String status = rst.getString("Status");
            int quantity = rst.getInt("Qty");

            // Map database column names to JTable column names
            model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, status, quantity});
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving items from the database.");
    } finally {
        try {
            if (rst != null) {
                rst.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
     //Display Stock Records
public void displayStockItems() {
    DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        String sql = "SELECT * FROM Stock";
        pst = conn.prepareStatement(sql);
        rst = pst.executeQuery();

        while (rst.next()) {
            int ItemID = rst.getInt("ItemID");
            int serialNumber = rst.getInt("SerialNo");
            String itemName = rst.getString("ItemName");
            String modelValue = rst.getString("Model");
            String specification = rst.getString("Specification");
            String category = rst.getString("Category");
            String brand = rst.getString("Brand");
            String status = rst.getString("Status");
            int quantity = rst.getInt("Qty");

            // Map database column names to JTable column names
            model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, status, quantity});
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving items from the database.");
    } finally {
        try {
            if (rst != null) {
                rst.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
    //Display Checking Records
 
private void addUser() {
    int employeeNum = Integer.parseInt(txtEmployeeNum.getText());
    String firstName = txtFirstName.getText();
    String lastName = txtLastName.getText();
    String username = txtUserName.getText();
    String password = new String(txtPass.getPassword());
    String accountType = (String) cbAccountType.getSelectedItem();

    // Validation
    if (username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Username and Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Confirmation dialog
    int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this user?", "Confirmation", JOptionPane.YES_NO_OPTION);
    if (option != JOptionPane.YES_OPTION) {
        return; // User selected No, so return without adding the user
    }

    // Hash the password
    String hashedPassword = hashPassword(password);

    // Database interaction (integrate this with your existing connection code)
    try {
        String query = "INSERT INTO Accounts (EmployeeNo, FirstName, LastName, UserName, PassWord, AccountType) VALUES (?, ?, ?, ?, ?, ?)";
        pst = conn.prepareStatement(query);
        pst.setInt(1, employeeNum);
        pst.setString(2, firstName);
        pst.setString(3, lastName);
        pst.setString(4, username);
        pst.setString(5, hashedPassword); // Store hashed password
        pst.setString(6, accountType);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(this, "User added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        displayAccountsData();
        // Reset fields after successful addition
        txtEmployeeNum.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtUserName.setText("");
        txtPass.setText("");
        cbAccountType.setSelectedIndex(0);
        chkPass.setSelected(false);

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding user.", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close resources in a finally block
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
// Define a method to fetch and display data from the accounts table
private void displayAccountsData() {
    DefaultTableModel model = new DefaultTableModel(); // Create a table model
    model.addColumn("ID");
    model.addColumn("Employee No");
    model.addColumn("First Name");
    model.addColumn("Last Name");
    model.addColumn("Username");
    model.addColumn("Password");
    model.addColumn("Account Type");

    try {
        // Query to fetch data from the accounts table
        String query = "SELECT ID, EmployeeNo, FirstName, LastName, UserName,PassWord, AccountType FROM Accounts";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        // Loop through the result set and add rows to the table model
        while (rs.next()) {
            Object[] row = {
                rs.getInt("ID"),
                rs.getInt("EmployeeNo"),
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getString("UserName"),
                rs.getString("PassWord"),
                rs.getString("AccountType")
            };
            model.addRow(row);
        }

        // Close resources
        pst.close();
        rs.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error fetching accounts data.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Set the table model to the JTable for display
    tblAccounts.setModel(model);
}

private String hashPassword(String password) {
    try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(password.getBytes());

        // Convert bytes to hexadecimal representation
        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            String hex = Integer.toHexString(0xff & hashByte);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
        return null;
    }
}
private void editUserTable() {
    int selectedRow = tblAccounts.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a user to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Retrieve data from the selected row
    int id = (int) tblAccounts.getValueAt(selectedRow, 0);
    int employeeNum = (int) tblAccounts.getValueAt(selectedRow, 1);
    String firstName = (String) tblAccounts.getValueAt(selectedRow, 2);
    String lastName = (String) tblAccounts.getValueAt(selectedRow, 3);
    String username = (String) tblAccounts.getValueAt(selectedRow, 4);
    String password = (String) tblAccounts.getValueAt(selectedRow, 5);
    String accountType = (String) tblAccounts.getValueAt(selectedRow, 6);

    // Prompt for confirmation before editing
    int option = JOptionPane.showConfirmDialog(this, "Do you want to edit this user?", "Confirmation", JOptionPane.YES_NO_OPTION);
    if (option != JOptionPane.YES_OPTION) {
        return; // User selected No, so return without editing the user
    }
    // Populate data into input fields
    txtEmployeeNum.setText(String.valueOf(employeeNum));
    txtFirstName.setText(firstName);
    txtLastName.setText(lastName);
    txtUserName.setText(username);
    // Allow editing of the password field
    txtPass.setText(password);
    cbAccountType.setSelectedItem(accountType);

    // Enable the "Save" button for confirming changes
    btnSave.setEnabled(true);
}
private void save() {
    int selectedRow = tblAccounts.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a user to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int userId = (int) tblAccounts.getValueAt(selectedRow, 0);
    int employeeNum = Integer.parseInt(txtEmployeeNum.getText());
    String firstName = txtFirstName.getText();
    String lastName = txtLastName.getText();
    String username = txtUserName.getText();
    String newPassword = new String(txtPass.getPassword());
    String accountType = (String) cbAccountType.getSelectedItem();

    // Validation
    if (username.isEmpty() || newPassword.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Username and Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to save changes?", "Confirmation", JOptionPane.YES_NO_OPTION);
    if (option != JOptionPane.YES_OPTION) {
        return;
    }

    // Hash the new password
    String hashedPassword = hashPassword(newPassword);

    // Database interaction to update user data
    try {
        String query = "UPDATE Accounts SET EmployeeNo=?, FirstName=?, LastName=?, UserName=?, PassWord=?, AccountType=? WHERE ID=?";
        pst = conn.prepareStatement(query);
        pst.setInt(1, employeeNum);
        pst.setString(2, firstName);
        pst.setString(3, lastName);
        pst.setString(4, username);
        pst.setString(5, hashedPassword);
        pst.setString(6, accountType);
        pst.setInt(7, userId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(this, "Changes saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        displayAccountsData(); // Refresh the Accounts table after saving changes

        clearUserFields();
        btnSave.setEnabled(false);

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error saving changes.", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close resources in a finally block
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
// Define a method for the "Save" button to confirm changes
//Delete User functionality
private void deleteUser() {
    int selectedRow = tblAccounts.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a user to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int userId = (int) tblAccounts.getValueAt(selectedRow, 0); // ID from the table

    // Confirmation dialog
    int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Confirmation", JOptionPane.YES_NO_OPTION);
    if (option != JOptionPane.YES_OPTION) {
        return; // User selected No, so return without deleting
    }

    // Database interaction to delete user data
    try {
        String query = "DELETE FROM Accounts WHERE ID=?";
        pst = conn.prepareStatement(query);
        pst.setInt(1, userId);
        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "User deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Remove the selected row from the JTable
            DefaultTableModel model = (DefaultTableModel) tblAccounts.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete user.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error deleting user.", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close resources in a finally block
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
//Clear the Add User Fields
private void clearUserFields() {
    txtEmployeeNum.setText("");
    txtFirstName.setText("");
    txtLastName.setText("");
    txtUserName.setText("");
    txtPass.setText("");
    cbAccountType.setSelectedIndex(0);
}

// Call this method when the "Save" button is clicked to confirm and save changes
// Ensure the selectedUserId variable is set appropriately when editing a user


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        tabPane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        txtItemName = new javax.swing.JTextField();
        txtModel = new javax.swing.JTextField();
        txtQty = new javax.swing.JTextField();
        txtBrand = new javax.swing.JTextField();
        cbSStatus = new javax.swing.JComboBox<>();
        txtInsert = new javax.swing.JButton();
        txtClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDataInfo = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtSpecification = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbSCategory = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtSerial = new javax.swing.JTextField();
        txtInsert1 = new javax.swing.JButton();
        txtEdit = new javax.swing.JButton();
        btnView = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbAccountType = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnSaveUser = new javax.swing.JButton();
        txtUserName = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        chkPass = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblAccounts = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        txtFirstName = new javax.swing.JTextField();
        txtEmployeeNum = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        btnSaveUser1 = new javax.swing.JButton();
        btnSaveUser2 = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnViewUser = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        tabSuperadmin = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStock = new javax.swing.JTable();
        btnSearch1 = new javax.swing.JButton();
        btnDeleteStock = new javax.swing.JButton();
        txtSearchStock = new javax.swing.JTextField();
        btnTransferStock = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChecking = new javax.swing.JTable();
        txtSearch3 = new javax.swing.JTextField();
        btnDelete3 = new javax.swing.JButton();
        btnTransferChk = new javax.swing.JButton();
        btnSearch3 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblReturn = new javax.swing.JTable();
        btnSearch2 = new javax.swing.JButton();
        txtSearch2 = new javax.swing.JTextField();
        btnDelete2 = new javax.swing.JButton();
        btnTransferRtn = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblRepair = new javax.swing.JTable();
        btnSearch4 = new javax.swing.JButton();
        txtSearch4 = new javax.swing.JTextField();
        btnDelete4 = new javax.swing.JButton();
        btnTransferRpr = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDisposal = new javax.swing.JTable();
        btnSearch5 = new javax.swing.JButton();
        txtSearch5 = new javax.swing.JTextField();
        btnDelete5 = new javax.swing.JButton();
        btnTransferDps = new javax.swing.JButton();
        btnStock = new javax.swing.JButton();
        btnChecking = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();
        btnRepair = new javax.swing.JButton();
        btnDisposal = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnRecords = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnAddUser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/logo_1.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        btnDashboard.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/menu icon.png"))); // NOI18N
        btnDashboard.setText("  Dashboard");
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });
        jPanel1.add(btnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 240, 60));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Rockwell", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(247, 247, 217));
        jLabel2.setText("        “Outsourcing Inspired by Quality”");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(749, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 571, Short.MAX_VALUE))
        );

        tabPane.addTab("tab1", jPanel2);

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));

        jLabel3.setFont(new java.awt.Font("Rockwell", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(247, 247, 217));
        jLabel3.setText("       “Outsourcing Inspired by Quality”");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );

        jLabel4.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel4.setText("ID");

        jLabel5.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel5.setText("Item Name");

        jLabel6.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel6.setText("Model");

        jLabel7.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel7.setText("Category");

        jLabel8.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel8.setText("Brand");

        jLabel9.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel9.setText("Status");

        jLabel10.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel10.setText("Quantity");

        jTextField1.setEnabled(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        txtModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModelActionPerformed(evt);
            }
        });

        txtQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtyActionPerformed(evt);
            }
        });

        txtBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBrandActionPerformed(evt);
            }
        });

        cbSStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stock", "Checking", "Return", "Repair", "Disposal" }));
        cbSStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSStatusActionPerformed(evt);
            }
        });

        txtInsert.setBackground(new java.awt.Color(0, 153, 153));
        txtInsert.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        txtInsert.setText("Insert");
        txtInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInsertActionPerformed(evt);
            }
        });

        txtClear.setBackground(new java.awt.Color(0, 153, 153));
        txtClear.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        txtClear.setText("Clear");
        txtClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClearActionPerformed(evt);
            }
        });

        tblDataInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "SerialNo", "ItemName", "Model", "Specification", "Category", "Brand", "Status", "Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDataInfo);
        if (tblDataInfo.getColumnModel().getColumnCount() > 0) {
            tblDataInfo.getColumnModel().getColumn(0).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(1).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(2).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(3).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(4).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(5).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(6).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(7).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(8).setResizable(false);
        }

        jLabel13.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel13.setText("ADD FORM");

        jLabel15.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel15.setText("Specification");

        cbSCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hardware", "Software" }));

        jLabel18.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel18.setText("Serial No.");

        txtInsert1.setBackground(new java.awt.Color(0, 153, 153));
        txtInsert1.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        txtInsert1.setText("Save");

        txtEdit.setBackground(new java.awt.Color(0, 153, 153));
        txtEdit.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        txtEdit.setText("Edit");
        txtEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEditActionPerformed(evt);
            }
        });

        btnView.setBackground(new java.awt.Color(0, 153, 153));
        btnView.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnView.setText("View");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(390, 390, 390)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSerial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtModel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel6))
                                                    .addGap(25, 25, 25))
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(jLabel15)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cbSCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbSStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtSpecification, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtItemName)
                                                .addComponent(txtBrand, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtClear, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtInsert1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtSerial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSpecification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbSCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbSStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addGap(12, 12, 12)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtInsert)
                            .addComponent(txtClear))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEdit)
                            .addComponent(txtInsert1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnView))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        tabPane.addTab("tab2", jPanel3);

        jPanel10.setBackground(new java.awt.Color(51, 51, 51));

        jLabel11.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel11.setText("Username");

        jLabel12.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel12.setText("Password");

        cbAccountType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SuperAdmin", "Admin", "User" }));
        cbAccountType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAccountTypeActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel16.setText("Account Type");

        jLabel17.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Add User Form");

        btnSaveUser.setBackground(new java.awt.Color(0, 153, 153));
        btnSaveUser.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnSaveUser.setText("Add");
        btnSaveUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveUserActionPerformed(evt);
            }
        });

        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });

        chkPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPassActionPerformed(evt);
            }
        });

        jLabel20.setText("ShowPassword");

        tblAccounts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "EmployeeNo", "FirstName", "LastName", "Username", "Password", "AccountType"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tblAccounts);
        if (tblAccounts.getColumnModel().getColumnCount() > 0) {
            tblAccounts.getColumnModel().getColumn(0).setResizable(false);
            tblAccounts.getColumnModel().getColumn(1).setResizable(false);
            tblAccounts.getColumnModel().getColumn(2).setResizable(false);
            tblAccounts.getColumnModel().getColumn(3).setResizable(false);
            tblAccounts.getColumnModel().getColumn(4).setResizable(false);
            tblAccounts.getColumnModel().getColumn(5).setResizable(false);
            tblAccounts.getColumnModel().getColumn(6).setResizable(false);
        }

        jLabel21.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel21.setText("Employee No.");

        jLabel22.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel22.setText("First Name");

        txtLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLastNameActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel23.setText("Last Name");

        btnSaveUser1.setBackground(new java.awt.Color(0, 153, 153));
        btnSaveUser1.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnSaveUser1.setText("Edit");
        btnSaveUser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveUser1ActionPerformed(evt);
            }
        });

        btnSaveUser2.setBackground(new java.awt.Color(0, 153, 153));
        btnSaveUser2.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnSaveUser2.setText("Delete");
        btnSaveUser2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveUser2ActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(0, 153, 153));
        btnClear.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(0, 153, 153));
        btnSave.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnViewUser.setBackground(new java.awt.Color(0, 153, 153));
        btnViewUser.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser.setText("View");
        btnViewUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(335, 335, 335)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(txtEmployeeNum, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel22)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel23)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel16)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(cbAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(chkPass)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel20))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnSaveUser2)
                                .addGap(15, 15, 15)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSaveUser, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnSaveUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnViewUser, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(5, 5, 5)
                        .addComponent(txtEmployeeNum, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel22)
                        .addGap(5, 5, 5)
                        .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel23)
                        .addGap(5, 5, 5)
                        .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel11)
                        .addGap(6, 6, 6)
                        .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel12)
                        .addGap(5, 5, 5)
                        .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel16)
                        .addGap(5, 5, 5)
                        .addComponent(cbAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkPass)
                            .addComponent(jLabel20))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSaveUser2)
                                .addComponent(btnClear))
                            .addComponent(btnSaveUser))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSaveUser1)
                            .addComponent(btnSave)
                            .addComponent(btnViewUser)))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel24.setFont(new java.awt.Font("Rockwell", 1, 48)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(247, 247, 217));
        jLabel24.setText("       “Outsourcing Inspired by Quality”");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel24))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabPane.addTab("tab3", jPanel8);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));

        jLabel14.setFont(new java.awt.Font("Rockwell", 1, 48)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(247, 247, 217));
        jLabel14.setText("''Outsourcing inspired by Quality''");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1863, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(556, Short.MAX_VALUE))
        );

        tabSuperadmin.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        tblStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Serial No.", "Item  Name", "Model", "Specification", "Category", "Brand", "Status", "Qty."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblStock);
        if (tblStock.getColumnModel().getColumnCount() > 0) {
            tblStock.getColumnModel().getColumn(0).setResizable(false);
            tblStock.getColumnModel().getColumn(1).setResizable(false);
            tblStock.getColumnModel().getColumn(2).setResizable(false);
            tblStock.getColumnModel().getColumn(3).setResizable(false);
            tblStock.getColumnModel().getColumn(4).setResizable(false);
            tblStock.getColumnModel().getColumn(5).setResizable(false);
            tblStock.getColumnModel().getColumn(6).setResizable(false);
            tblStock.getColumnModel().getColumn(7).setResizable(false);
            tblStock.getColumnModel().getColumn(8).setResizable(false);
        }

        btnSearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/magnifying-glass (1).png"))); // NOI18N

        btnDeleteStock.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnDeleteStock.setText("Delete");
        btnDeleteStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteStockActionPerformed(evt);
            }
        });

        txtSearchStock.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N

        btnTransferStock.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnTransferStock.setText("Transfer");
        btnTransferStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferStockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(358, 630, Short.MAX_VALUE)
                        .addComponent(btnTransferStock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteStock)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearchStock, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearchStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeleteStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTransferStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabSuperadmin.addTab("tab1", jPanel13);

        tblChecking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CheckingID", "ItemID", "Serial No.", "Name", "Category", "Brand", "Status", "Qty.", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblChecking);
        if (tblChecking.getColumnModel().getColumnCount() > 0) {
            tblChecking.getColumnModel().getColumn(0).setResizable(false);
            tblChecking.getColumnModel().getColumn(1).setResizable(false);
            tblChecking.getColumnModel().getColumn(2).setResizable(false);
            tblChecking.getColumnModel().getColumn(3).setResizable(false);
            tblChecking.getColumnModel().getColumn(4).setResizable(false);
            tblChecking.getColumnModel().getColumn(5).setResizable(false);
            tblChecking.getColumnModel().getColumn(6).setResizable(false);
            tblChecking.getColumnModel().getColumn(7).setResizable(false);
            tblChecking.getColumnModel().getColumn(8).setResizable(false);
        }

        txtSearch3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N

        btnDelete3.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnDelete3.setText("Delete");
        btnDelete3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete3ActionPerformed(evt);
            }
        });

        btnTransferChk.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnTransferChk.setText("Transfer");
        btnTransferChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferChkActionPerformed(evt);
            }
        });

        btnSearch3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/magnifying-glass (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(630, Short.MAX_VALUE)
                .addComponent(btnTransferChk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete3)
                .addGap(3, 3, 3)
                .addComponent(txtSearch3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnSearch3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTransferChk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSearch3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabSuperadmin.addTab("tab2", jPanel15);

        tblReturn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ReturnID", "ItemID", "SerialNo.", "Name", "Category", "Brand", "Status", "Qty.", "ReturnDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblReturn);
        if (tblReturn.getColumnModel().getColumnCount() > 0) {
            tblReturn.getColumnModel().getColumn(0).setResizable(false);
            tblReturn.getColumnModel().getColumn(1).setResizable(false);
            tblReturn.getColumnModel().getColumn(2).setResizable(false);
            tblReturn.getColumnModel().getColumn(3).setResizable(false);
            tblReturn.getColumnModel().getColumn(4).setResizable(false);
            tblReturn.getColumnModel().getColumn(5).setResizable(false);
            tblReturn.getColumnModel().getColumn(6).setResizable(false);
            tblReturn.getColumnModel().getColumn(7).setResizable(false);
            tblReturn.getColumnModel().getColumn(8).setResizable(false);
        }

        btnSearch2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/magnifying-glass (1).png"))); // NOI18N

        txtSearch2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N

        btnDelete2.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnDelete2.setText("Delete");
        btnDelete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete2ActionPerformed(evt);
            }
        });

        btnTransferRtn.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnTransferRtn.setText("Transfer");
        btnTransferRtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferRtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(630, Short.MAX_VALUE)
                .addComponent(btnTransferRtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete2)
                .addGap(3, 3, 3)
                .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTransferRtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabSuperadmin.addTab("tab3", jPanel16);

        tblRepair.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "RepairID", "ItemID", "Serial No.", "Name", "Category", "Brand", "Status", "Qty", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblRepair);
        if (tblRepair.getColumnModel().getColumnCount() > 0) {
            tblRepair.getColumnModel().getColumn(0).setResizable(false);
            tblRepair.getColumnModel().getColumn(1).setResizable(false);
            tblRepair.getColumnModel().getColumn(2).setResizable(false);
            tblRepair.getColumnModel().getColumn(3).setResizable(false);
            tblRepair.getColumnModel().getColumn(4).setResizable(false);
            tblRepair.getColumnModel().getColumn(5).setResizable(false);
            tblRepair.getColumnModel().getColumn(6).setResizable(false);
            tblRepair.getColumnModel().getColumn(7).setResizable(false);
            tblRepair.getColumnModel().getColumn(8).setResizable(false);
        }

        btnSearch4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/magnifying-glass (1).png"))); // NOI18N

        txtSearch4.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N

        btnDelete4.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnDelete4.setText("Delete");
        btnDelete4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete4ActionPerformed(evt);
            }
        });

        btnTransferRpr.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnTransferRpr.setText("Transfer");
        btnTransferRpr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferRprActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(0, 630, Short.MAX_VALUE)
                        .addComponent(btnTransferRpr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete4)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearch4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(btnSearch4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTransferRpr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSearch4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabSuperadmin.addTab("tab4", jPanel17);

        tblDisposal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DisposalID", "ItemID", "Serial No.", "Name", "Category", "Brand", "Status", "Qty.", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblDisposal);
        if (tblDisposal.getColumnModel().getColumnCount() > 0) {
            tblDisposal.getColumnModel().getColumn(0).setResizable(false);
            tblDisposal.getColumnModel().getColumn(1).setResizable(false);
            tblDisposal.getColumnModel().getColumn(2).setResizable(false);
            tblDisposal.getColumnModel().getColumn(3).setResizable(false);
            tblDisposal.getColumnModel().getColumn(4).setResizable(false);
            tblDisposal.getColumnModel().getColumn(5).setResizable(false);
            tblDisposal.getColumnModel().getColumn(6).setResizable(false);
            tblDisposal.getColumnModel().getColumn(7).setResizable(false);
            tblDisposal.getColumnModel().getColumn(8).setResizable(false);
        }

        btnSearch5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/magnifying-glass (1).png"))); // NOI18N

        txtSearch5.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N

        btnDelete5.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnDelete5.setText("Delete");
        btnDelete5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete5ActionPerformed(evt);
            }
        });

        btnTransferDps.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnTransferDps.setText("Transfer");
        btnTransferDps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferDpsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(374, 630, Short.MAX_VALUE)
                        .addComponent(btnTransferDps)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete5)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearch5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(btnSearch5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTransferDps, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSearch5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabSuperadmin.addTab("tab5", jPanel18);

        btnStock.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnStock.setText("Stock");
        btnStock.setPreferredSize(new java.awt.Dimension(98, 25));
        btnStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockActionPerformed(evt);
            }
        });

        btnChecking.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnChecking.setText("Checking");
        btnChecking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckingActionPerformed(evt);
            }
        });

        btnReturn.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnReturn.setText("Return");
        btnReturn.setPreferredSize(new java.awt.Dimension(98, 25));
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        btnRepair.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnRepair.setText("Repair");
        btnRepair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepairActionPerformed(evt);
            }
        });

        btnDisposal.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnDisposal.setText("Disposal");
        btnDisposal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisposalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnStock, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnChecking)
                .addGap(2, 2, 2)
                .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnRepair)
                .addGap(2, 2, 2)
                .addComponent(btnDisposal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabSuperadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChecking, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRepair, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDisposal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabSuperadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jLabel19.setFont(new java.awt.Font("Rockwell", 1, 48)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(247, 247, 217));
        jLabel19.setText("“Outsourcing Inspired by Quality”");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel19)))
                .addGap(102, 102, 102)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabPane.addTab("tab4", jPanel4);

        jPanel1.add(tabPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, -40, 1020, 740));

        btnAdd.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/plus icon.png"))); // NOI18N
        btnAdd.setText("  Add Item");
        btnAdd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 240, 60));

        btnRecords.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btnRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/records icon.png"))); // NOI18N
        btnRecords.setText("  Records");
        btnRecords.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecordsActionPerformed(evt);
            }
        });
        jPanel1.add(btnRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 240, 60));

        btnLogout.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btnLogout.setText("LOG OUT");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 650, -1, -1));

        btnAddUser.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btnAddUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/user icon (2).png"))); // NOI18N
        btnAddUser.setText("  Add User");
        btnAddUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 240, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1368, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        // TODO add your handling code here:
        tabPane.setSelectedIndex(0);
        
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
          tabPane.setSelectedIndex(1);
          displayDataItems();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecordsActionPerformed
        // TODO add your handling code here:
          tabPane.setSelectedIndex(3);
          displayStockItems();
    }//GEN-LAST:event_btnRecordsActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {

            JOptionPane.showMessageDialog(null, "Logout Successful");
             new LogIn().setVisible(true);
            dispose();
        } else {

        }
          
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void txtBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBrandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBrandActionPerformed

    private void txtQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtyActionPerformed

    private void txtModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModelActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        // TODO add your handling code here:
        tabPane.setSelectedIndex(2);
        displayAccountsData();
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void txtClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_txtClearActionPerformed

    private void txtEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEditActionPerformed

    private void txtInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInsertActionPerformed
        // TODO add your handling code here:
        insertItem();
    }//GEN-LAST:event_txtInsertActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        // TODO add your handling code here:
        displayDataItems();
        displayStockItems();
    }//GEN-LAST:event_btnViewActionPerformed

    private void btnDeleteStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteStockActionPerformed
        // TODO add your handling code here:
        delete_tblStock();
        displayStockItems();
    }//GEN-LAST:event_btnDeleteStockActionPerformed

    private void btnTransferStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferStockActionPerformed
        // TODO add your handling code here:
        transferSelectedItem();
        displayStockItems();
    }//GEN-LAST:event_btnTransferStockActionPerformed

    private void btnDelete3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDelete3ActionPerformed

    private void btnTransferChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferChkActionPerformed
        // TODO add your handling code here:
        transferCheckingItem("Checking");

    }//GEN-LAST:event_btnTransferChkActionPerformed

    private void btnDelete2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDelete2ActionPerformed

    private void btnTransferRtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferRtnActionPerformed
        // TODO add your handling code here:
        transferCheckingItem("Return");
 
    }//GEN-LAST:event_btnTransferRtnActionPerformed

    private void btnDelete4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDelete4ActionPerformed

    private void btnTransferRprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferRprActionPerformed
        // TODO add your handling code here:
        transferCheckingItem("Repair");

    }//GEN-LAST:event_btnTransferRprActionPerformed

    private void btnDelete5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDelete5ActionPerformed

    private void btnTransferDpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferDpsActionPerformed
        // TODO add your handling code here:
        transferCheckingItem("Disposal");
    }//GEN-LAST:event_btnTransferDpsActionPerformed

    private void btnStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(0);
        displayStockItems();
    }//GEN-LAST:event_btnStockActionPerformed

    private void btnCheckingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckingActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(1);
        displayCheckingData();
    }//GEN-LAST:event_btnCheckingActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(2);
        displayReturndata();
    }//GEN-LAST:event_btnReturnActionPerformed

    private void btnRepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepairActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(3);
        displayRepairData();
    }//GEN-LAST:event_btnRepairActionPerformed

    private void btnDisposalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisposalActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(4);
        displayDisposalData();
    }//GEN-LAST:event_btnDisposalActionPerformed

    private void cbSStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSStatusActionPerformed

    private void btnViewUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUserActionPerformed
        // TODO add your handling code here:
        displayAccountsData();
    }//GEN-LAST:event_btnViewUserActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        txtEmployeeNum.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtUserName.setText("");
        txtPass.setText("");
        cbAccountType.setSelectedIndex(0);
        chkPass.setSelected(false);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSaveUser2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveUser2ActionPerformed
        // TODO add your handling code here:
        deleteUser();
    }//GEN-LAST:event_btnSaveUser2ActionPerformed

    private void btnSaveUser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveUser1ActionPerformed
        // TODO add your handling code here:
        editUserTable();
    }//GEN-LAST:event_btnSaveUser1ActionPerformed

    private void txtLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameActionPerformed

    private void chkPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPassActionPerformed
        // TODO add your handling code here:
        if(chkPass.isSelected()){
            txtPass.setEchoChar((char)0);
        }
        else{
            txtPass.setEchoChar('*');
        }
    }//GEN-LAST:event_chkPassActionPerformed

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void btnSaveUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveUserActionPerformed
        // TODO add your handling code here:
        addUser();
    }//GEN-LAST:event_btnSaveUserActionPerformed

    private void cbAccountTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAccountTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbAccountTypeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(superadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(superadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(superadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(superadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new superadmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnChecking;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDelete2;
    private javax.swing.JButton btnDelete3;
    private javax.swing.JButton btnDelete4;
    private javax.swing.JButton btnDelete5;
    private javax.swing.JButton btnDeleteStock;
    private javax.swing.JButton btnDisposal;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRecords;
    private javax.swing.JButton btnRepair;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnSaveUser;
    private javax.swing.JButton btnSaveUser1;
    private javax.swing.JButton btnSaveUser2;
    private javax.swing.JButton btnSaveUser3;
    private javax.swing.JButton btnSaveUser4;
    private javax.swing.JButton btnSaveUser5;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSearch2;
    private javax.swing.JButton btnSearch3;
    private javax.swing.JButton btnSearch4;
    private javax.swing.JButton btnSearch5;
    private javax.swing.JButton btnStock;
    private javax.swing.JButton btnTransferChk;
    private javax.swing.JButton btnTransferDps;
    private javax.swing.JButton btnTransferRpr;
    private javax.swing.JButton btnTransferRtn;
    private javax.swing.JButton btnTransferStock;
    private javax.swing.JButton btnView;
    private javax.swing.JButton btnViewUser;
    private javax.swing.JButton btnViewUser1;
    private javax.swing.JComboBox<String> cbAccountType;
    private javax.swing.JComboBox<String> cbAccountType1;
    private javax.swing.JComboBox<String> cbSCategory;
    private javax.swing.JComboBox<String> cbSStatus;
    private javax.swing.JCheckBox chkPass;
    private javax.swing.JCheckBox chkPass1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JTabbedPane tabSuperadmin;
    private javax.swing.JTable tblAccounts;
    private javax.swing.JTable tblAccounts1;
    private javax.swing.JTable tblChecking;
    private javax.swing.JTable tblDataInfo;
    private javax.swing.JTable tblDisposal;
    private javax.swing.JTable tblRepair;
    private javax.swing.JTable tblReturn;
    private javax.swing.JTable tblStock;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JButton txtClear;
    private javax.swing.JButton txtEdit;
    private javax.swing.JTextField txtEmployeeNum;
    private javax.swing.JTextField txtEmployeeNum1;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtFirstName1;
    private javax.swing.JButton txtInsert;
    private javax.swing.JButton txtInsert1;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtLastName1;
    private javax.swing.JTextField txtModel;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JPasswordField txtPass1;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSearch2;
    private javax.swing.JTextField txtSearch3;
    private javax.swing.JTextField txtSearch4;
    private javax.swing.JTextField txtSearch5;
    private javax.swing.JTextField txtSearchStock;
    private javax.swing.JTextField txtSerial;
    private javax.swing.JTextField txtSpecification;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JTextField txtUserName1;
    // End of variables declaration//GEN-END:variables
}
