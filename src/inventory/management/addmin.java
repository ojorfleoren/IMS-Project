package inventory.management;

import static inventory.management.User.alignChecking;
import static inventory.management.User.alignDisposal;
import static inventory.management.User.alignRelease;
import static inventory.management.User.alignRepair;
import static inventory.management.User.alignReturn;
import static inventory.management.User.alignText;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author OJT-21
 */
public class addmin extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rst = null;
public addmin() {
    
        initComponents();
        conn = LogIn.connectDB();
        setExtendedState(addmin.MAXIMIZED_BOTH); 
        //Table Sizes
        //Display the total quantity into the dashboard
        displayTotalDataCount();
        displayReleaseCount();
        displayCheckingCount();
        displayRepairCount();
        displayReturnCount();
        displayDisposalCount();
        alignText(tblStock);
        alignRelease(tblRelease);
        alignChecking(tblChecking);
        alignReturn(tblReturn);
        alignRepair(tblRepair);
        alignDisposal(tblDisposal);
    // Attach key listener to txtQty and the Employee number field
    txtQty.addKeyListener(createNumericKeyListener());
    }
private static final int[] stockAlignment = {SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER};
    private static final int[] minWidths = {15, 40, 150, 120, 200, 50, 70, 15};
    //
    private static final int[] alignment = {SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER};
    private static final int[] Widths = {60, 15, 80, 130, 100, 170, 60, 90, 15, 90};
    
    
    public static void alignText(JTable tblStock) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblStock.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();
        
        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < tblStock.getColumnCount() && i < stockAlignment.length; i++) {
            int align = stockAlignment[i];
            int minWidth = minWidths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblStock.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblStock.getColumnModel().getColumn(i).setMinWidth(minWidth); // Set minimum width for cell
           
            // Set minimum width for header
            tblStock.getTableHeader().getColumnModel().getColumn(i).setMinWidth(minWidth);
        }
    }
    public static void alignRelease(JTable tblRelease) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblRelease.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();
        
        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < tblRelease.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int Width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblRelease.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblRelease.getColumnModel().getColumn(i).setMinWidth(Width); // Set minimum width for cell
           
            // Set minimum width for header
            tblRelease.getTableHeader().getColumnModel().getColumn(i).setMinWidth(Width);
        }
    }
    public static void alignChecking(JTable tblChecking) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblChecking.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();
        
        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < tblChecking.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int Width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblChecking.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblChecking.getColumnModel().getColumn(i).setMinWidth(Width); // Set minimum width for cell
            tblChecking.getTableHeader().getColumnModel().getColumn(i).setMinWidth(Width);
        }
    }
    public static void alignReturn(JTable tblReturn) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblReturn.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();
        
        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < tblReturn.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int Width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblReturn.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblReturn.getColumnModel().getColumn(i).setMinWidth(Width); // Set minimum width for cell
            tblReturn.getTableHeader().getColumnModel().getColumn(i).setMinWidth(Width);
        }
    }
    public static void alignRepair(JTable tblRepair) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblRepair.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();
        
        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < tblRepair.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int Width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblRepair.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblRepair.getColumnModel().getColumn(i).setMinWidth(Width); // Set minimum width for cell
            tblRepair.getTableHeader().getColumnModel().getColumn(i).setMinWidth(Width);
        }
    }
    public static void alignDisposal(JTable tblDisposal) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblDisposal.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();
        
        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < tblDisposal.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int Width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblDisposal.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblDisposal.getColumnModel().getColumn(i).setMinWidth(Width); // Set minimum width for cell
            tblDisposal.getTableHeader().getColumnModel().getColumn(i).setMinWidth(Width);
        }
    }
    //End-------------------------------------------------------------------------------------------------------------End
//Keyboard listener
private KeyListener createNumericKeyListener() {
    return new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE)) {
                e.consume(); // Ignore the event
            }
        }
    };
}
//Display Count in Dashboard
public void displayTotalDataCount() {
    try {
        // Execute a SQL query to sum the total quantity in the "Qty" column of the "Stock" table
        String sumSql = "SELECT SUM(Qty) AS total FROM Stock";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblTotalqty.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
//Display the total quantity of each area into the dashboard
public void displayReleaseCount() {
    try {
        String sumSql = "SELECT SUM(Qty) AS total FROM Release";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblRelease.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayCheckingCount() {
    try {
        String sumSql = "SELECT SUM(Qty) AS total FROM Checking";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblChecking.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayRepairCount() {
    try {
        // Execute a SQL query to sum the total quantity in the "Qty" column of the "Stock" table
        String sumSql = "SELECT SUM(Qty) AS total FROM Repair";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblRepair.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayReturnCount() {
    try {
        // Execute a SQL query to sum the total quantity in the "Qty" column of the "Stock" table
        String sumSql = "SELECT SUM(Qty) AS total FROM Return";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblReturn.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayDisposalCount() {
    try {
        // Execute a SQL query to sum the total quantity in the "Qty" column of the "Stock" table
        String sumSql = "SELECT SUM(Qty) AS total FROM Disposal";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblDisposal.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
//End--------------------------------------------------------------------------------End
//Search Functionality
public void searchItems(String searchText) {
    DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        String sql = "SELECT * FROM Stock WHERE ItemName LIKE ? OR SerialNo LIKE ? OR Model LIKE ? OR Category LIKE ? OR Brand LIKE ?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, "%" + searchText + "%"); // Search itemName
        pst.setString(2, "%" + searchText + "%"); // Search Serial No
        pst.setString(3, "%" + searchText + "%"); // Search Category
        pst.setString(4, "%" + searchText + "%"); // Search Status
        pst.setString(5, "%" + searchText + "%");
        rst = pst.executeQuery();

        while (rst.next()) {
            int ItemID = rst.getInt("ItemID");
            int serialNumber = rst.getInt("SerialNo");
            String itemName = rst.getString("ItemName");
            String modelValue = rst.getString("Model");
            String specification = rst.getString("Specification");
            String category = rst.getString("Category");
            String brand = rst.getString("Brand");
            int quantity = rst.getInt("Qty");

            // Map database column names to JTable column names
            model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, quantity});
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
//End--------------------------------------------------------------------------------End
//ADD item functionality
public void insertItem() {
    try {
        // Validate fields before proceeding
        if (!validateFields()) {
            return; // Stop execution if fields are not filled up
        }

        // Get the values from the text fields
        String serialNum = txtSerial.getText();
        String itemName = txtItemName.getText();
        String model = txtModel.getText();
        String specification = txtSpecification.getText();
        String category = cbSCategory.getSelectedItem().toString();
        String brand = txtBrand.getText();
        String qtyText = txtQty.getText();

        // Validate integer field (Quantity)
        int qty;
        try {
            qty = Integer.parseInt(qtyText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer value for Quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String message = "Are you sure you want to add this item?";
        int option = JOptionPane.showConfirmDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            String sql = "INSERT INTO Stock (SerialNo, ItemName, Model, Specification, Category, Brand, Qty) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";

            pst = conn.prepareStatement(sql);
            pst.setString(1, serialNum);
            pst.setString(2, itemName);
            pst.setString(3, model);
            pst.setString(4, specification);
            pst.setString(5, category);
            pst.setString(6, brand);
            pst.setInt(7, qty);

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
//End--------------------------------------------------------------------------------End
//Get info of column in database
private String getItemSerialNumber(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
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
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
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
private String getItemModel(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
        String sql = "SELECT Model FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Model");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item name.");
    }
    return null;
}
private String getItemSpecification(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
        String sql = "SELECT Specification FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Specification");
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
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
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
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
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
//End-----------------------------------------------------------------------End
//Display the 6 areas from the database
public void displayReturn() {
    DefaultTableModel returnModel = (DefaultTableModel) tblReturn.getModel();
    returnModel.setRowCount(0); // Clear existing rows

    try {
        String sql = "SELECT r.ReturnID, r.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification,  s.Category, s.Brand, r.Qty AS ReturnedQty, r.Date " +
                     "FROM Return r " +
                     "LEFT JOIN Stock s ON r.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int returnID = rst.getInt("ReturnID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int returnedQty = rst.getInt("ReturnedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Return table with all the information
                returnModel.addRow(new Object[]{returnID, itemID, serialNo, itemName, model, specification, category, brand, returnedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Return data from the database.");
    }
}
public void displayRepair() {
    DefaultTableModel repairModel = (DefaultTableModel) tblRepair.getModel();
    repairModel.setRowCount(0); // Clear existing rows

    try {
        // Modify the SQL query for Repair area
        String sql = "SELECT rep.RepairID, rep.ItemID, s.SerialNo, s.Model, s.Specification, s.Category, s.Brand, rep.Qty AS RepairedQty, rep.Date " +
                     "FROM Repair rep " +
                     "LEFT JOIN Stock s ON rep.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int repairID = rst.getInt("RepairID");
                int itemID = rst.getInt("ItemID");
                String serialNo = getItemSerialNumber(itemID);
                String itemName = getItemName(itemID); // Utilize getItemName method
                String model = getItemModel(itemID); // Utilize getItemModel method
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int repairedQty = rst.getInt("RepairedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Repair table with all the information
                repairModel.addRow(new Object[]{repairID, itemID, serialNo, itemName, model, specification, category, brand, repairedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Repair data from the database.");
    }
}
public void displayDisposal() {
    DefaultTableModel disposalModel = (DefaultTableModel) tblDisposal.getModel();
    disposalModel.setRowCount(0); // Clear existing rows

    try {
        // Modify the SQL query for Disposal area
        String sql = "SELECT d.DisposalID, d.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, d.Qty AS DisposedQty, d.Date " +
                     "FROM Disposal d " +
                     "LEFT JOIN Stock s ON d.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int disposalID = rst.getInt("DisposalID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int disposedQty = rst.getInt("DisposedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Disposal table with all the information
                disposalModel.addRow(new Object[]{disposalID, itemID, serialNo, itemName, model, specification, category, brand, disposedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Disposal data from the database.");
    }
}
public void displayCheckingData() {
    DefaultTableModel checkingModel = (DefaultTableModel) tblChecking.getModel();
    checkingModel.setRowCount(0); // Clear existing rows
    try {
        String sql = "SELECT c.CheckingID, c.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, c.Qty AS TransferredQty, c.Date " +
                     "FROM Checking c " +
                     "LEFT JOIN Stock s ON c.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int checkingID = rst.getInt("CheckingID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int transferredQty = rst.getInt("TransferredQty");
                Date date = rst.getDate("Date");

                // Add a row to the Checking table with all the information
                checkingModel.addRow(new Object[]{checkingID, itemID, serialNo, itemName, model, specification, category, brand, transferredQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Checking data from the database.");
    }
}
public void displayReturnData() {
    DefaultTableModel model = (DefaultTableModel) tblReturn.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");

        String sql = "SELECT r.ReturnID, r.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, c.Qty AS CheckedQty, r.Qty AS ReturnedQty, r.Date " +
                     "FROM Return r " +
                     "INNER JOIN Checking c ON r.ItemID = c.ItemID " +
                     "INNER JOIN Stock s ON r.ItemID = s.ItemID";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int returnID = rst.getInt("ReturnID");
                int itemID = rst.getInt("ItemID");
                String serialNumber = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String modelValue = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int checkedQty = rst.getInt("CheckedQty");
                int returnedQty = rst.getInt("ReturnedQty");
                Date returnDate = rst.getDate("Date");

                // Add a row to the table with all the information
                model.addRow(new Object[]{returnID, itemID, serialNumber, itemName, modelValue, specification,  category, brand, checkedQty, returnedQty, returnDate});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving return data from the database.");
    }
}
public void displayReleaseData() {
    DefaultTableModel releaseModel = (DefaultTableModel) tblRelease.getModel();
    releaseModel.setRowCount(0); // Clear existing rows
    
    try {
        String sql = "SELECT r.ReleaseID, r.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, r.Qty AS ReleasedQty, r.Date " +
                     "FROM Release r " +
                     "LEFT JOIN Stock s ON r.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int releaseID = rst.getInt("ReleaseID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int releasedQty = rst.getInt("ReleasedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Release table with all the information
                releaseModel.addRow(new Object[]{releaseID, itemID, serialNo, itemName, model, specification, category, brand, releasedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Release data from the database.");
    }
}
public void displayCombinedData() {
    DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");

        String sql = "SELECT s.ItemID, s.SerialNo, s.ItemName, s.Model, s.Category, s.Specification, s.Brand, s.Qty, " +
                     "c.Date AS CheckingDate " +
                     "FROM Stock s " +
                     "LEFT JOIN Checking c ON s.ItemID = c.ItemID ";

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
                int quantity = rst.getInt("Qty");

                // Retrieve dates from different tables
                Date checkingDate = rst.getDate("CheckingDate");

                // Add a row to the table with all the information
                model.addRow(new Object[]{itemID, serialNumber, itemName, modelValue, category, specification, brand, quantity, checkingDate});
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
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");

        String sql = "SELECT s.ItemID, s.SerialNo, s.ItemName, s.Model, s.Category, s.Specification, s.Brand, s.Qty, " +
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
                int quantity = rst.getInt("Qty");

                // Retrieve dates from different tables
                Date returnDate = rst.getDate("ReturnDate");

                // Add a row to the table with all the information
                model.addRow(new Object[]{itemID, serialNumber, itemName, modelValue, category, specification, brand, quantity, returnDate});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving combined data from the database.");
    }
}
//End--------------------------------------------------------------------------------End
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
//End--------------------------------------------------------------------------------End
//Clear funtionality
public void clear(){
        txtSerial.setText("");
        txtItemName.setText("");
        txtModel.setText("");
        txtSpecification.setText("");
        cbSCategory.setSelectedIndex(0);
        txtBrand.setText("");
        txtQty.setText("");
        txtSearchItem.setText("");
    } 
//End--------------------------------------------------------------------------------End
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
            String serialNumber = rst.getString("SerialNo");
            String itemName = rst.getString("ItemName");
            String modelValue = rst.getString("Model");
            String specification = rst.getString("Specification");
            String category = rst.getString("Category");
            String brand = rst.getString("Brand");
            int quantity = rst.getInt("Qty");

            // Map database column names to JTable column names
            model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, quantity});
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
            String serialNumber = rst.getString("SerialNo");
            String itemName = rst.getString("ItemName");
            String modelValue = rst.getString("Model");
            String specification = rst.getString("Specification");
            String category = rst.getString("Category");
            String brand = rst.getString("Brand");
            int quantity = rst.getInt("Qty");

            // Map database column names to JTable column names
            model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, quantity});
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
public static void printStock(JTable tblStock) {
        // Get the table model
        TableModel model = tblStock.getModel();

        try {
            // Print the table
            boolean complete = tblStock.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printRelease(JTable tblRelease) {
        // Get the table model
        TableModel model = tblRelease.getModel();

        try {
            // Print the table
            boolean complete = tblRelease.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printChecking(JTable tblChecking) {
        // Get the table model
        TableModel model = tblChecking.getModel();

        try {
            // Print the table
            boolean complete = tblChecking.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printReturn(JTable tblReturn) {
        // Get the table model
        TableModel model = tblReturn.getModel();

        try {
            // Print the table
            boolean complete = tblReturn.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printRepair(JTable tblRepair) {
        // Get the table model
        TableModel model = tblRepair.getModel();

        try {
            // Print the table
            boolean complete = tblRepair.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printDisposal(JTable tblDisposal) {
        // Get the table model
        TableModel model = tblDisposal.getModel();

        try {
            // Print the table
            boolean complete = tblDisposal.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
//End--------------------------------------------------------------------------------End
// Define a method to fetch and display data from the accounts table
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        tabPane = new javax.swing.JTabbedPane();
        dashboard = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        lblChecking = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        lblRepair = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        lblDisposal = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        lblReturn = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        lblTotalqty = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        lblRelease = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        txtModel = new javax.swing.JTextField();
        txtQty = new javax.swing.JTextField();
        txtBrand = new javax.swing.JTextField();
        btnInsert = new javax.swing.JButton();
        txtClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDataInfo = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtSpecification = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbSCategory = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtSerial = new javax.swing.JTextField();
        txtSearchItem = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        btnViewUser3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        tabSuperadmin = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStock = new javax.swing.JTable();
        txtSearchStock = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        btnViewUser4 = new javax.swing.JButton();
        btnPrint_stock = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblRelease = new javax.swing.JTable();
        btnPrint_release = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChecking = new javax.swing.JTable();
        btnPrint_checking = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblReturn = new javax.swing.JTable();
        btnPrint_return = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblRepair = new javax.swing.JTable();
        btnPrint_repair = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDisposal = new javax.swing.JTable();
        btnPrint_disposal = new javax.swing.JButton();
        btnStock = new javax.swing.JButton();
        btnChecking = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();
        btnRepair = new javax.swing.JButton();
        btnDisposal = new javax.swing.JButton();
        btnDisposal1 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnRecords = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/logo_1.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        btnDashboard.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/dashboard.png"))); // NOI18N
        btnDashboard.setText("  Dashboard");
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });
        jPanel1.add(btnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 240, 60));

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

        jPanel12.setBackground(new java.awt.Color(211, 109, 109));
        jPanel12.setPreferredSize(new java.awt.Dimension(241, 104));

        lblChecking.setFont(new java.awt.Font("Rockwell", 1, 40)); // NOI18N
        lblChecking.setText("0");

        jLabel31.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel31.setText("Checking Items");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Checking.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(lblChecking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(lblChecking)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel31))
                    .addComponent(jLabel4))
                .addGap(30, 30, 30))
        );

        jPanel14.setBackground(new java.awt.Color(106, 177, 135));
        jPanel14.setPreferredSize(new java.awt.Dimension(241, 104));

        lblRepair.setFont(new java.awt.Font("Rockwell", 1, 40)); // NOI18N
        lblRepair.setText("0");

        jLabel33.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel33.setText("Repair Items");

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/repair...png"))); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addContainerGap(54, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblRepair, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblRepair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel33)))
                .addGap(23, 23, 23))
        );

        jPanel20.setBackground(new java.awt.Color(190, 159, 191));
        jPanel20.setPreferredSize(new java.awt.Dimension(241, 104));

        lblDisposal.setFont(new java.awt.Font("Rockwell", 1, 40)); // NOI18N
        lblDisposal.setText("0");

        jLabel39.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel39.setText("Disposal Items");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Disposal.png"))); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel39)
                        .addContainerGap(31, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDisposal, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(lblDisposal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel39))
                    .addComponent(jLabel9))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(102, 102, 154));
        jPanel21.setPreferredSize(new java.awt.Dimension(241, 104));

        lblReturn.setFont(new java.awt.Font("Rockwell", 1, 40)); // NOI18N
        lblReturn.setText("0");

        jLabel37.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel37.setText("Return Items");

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Return.png"))); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel37)
                        .addContainerGap(41, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(lblReturn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel37)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(19, 19, 19))))
        );

        jLabel47.setFont(new java.awt.Font("Rockwell", 1, 48)); // NOI18N
        jLabel47.setText("DASHBOARD");

        jPanel19.setBackground(new java.awt.Color(255, 232, 163));
        jPanel19.setPreferredSize(new java.awt.Dimension(241, 104));

        lblTotalqty.setFont(new java.awt.Font("Rockwell", 1, 40)); // NOI18N
        lblTotalqty.setText("0");

        jLabel28.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel28.setText("Total");

        jLabel29.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel29.setText("Stock Items");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel29))
                .addContainerGap(82, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotalqty, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(lblTotalqty, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addContainerGap())
        );

        jPanel28.setBackground(new java.awt.Color(251, 202, 126));
        jPanel28.setPreferredSize(new java.awt.Dimension(241, 104));

        lblRelease.setFont(new java.awt.Font("Rockwell", 1, 40)); // NOI18N
        lblRelease.setText("0");

        jLabel38.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel38.setText("Release Items");

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/release.png"))); // NOI18N

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38)
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRelease, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel28Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRelease)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel28Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel40)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dashboardLayout = new javax.swing.GroupLayout(dashboard);
        dashboard.setLayout(dashboardLayout);
        dashboardLayout.setHorizontalGroup(
            dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(dashboardLayout.createSequentialGroup()
                .addGap(363, 363, 363)
                .addComponent(jLabel47))
            .addGroup(dashboardLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dashboardLayout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dashboardLayout.createSequentialGroup()
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        dashboardLayout.setVerticalGroup(
            dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel47)
                .addGap(56, 56, 56)
                .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabPane.addTab("tab1", dashboard);

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Rockwell", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(247, 247, 217));
        jLabel3.setText("       “Outsourcing Inspired by Quality”");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 6, 1008, 77));

        jLabel5.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel5.setText("Item Name:");

        jLabel6.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel6.setText("Model:");

        jLabel7.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel7.setText("Category:");

        jLabel8.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel8.setText("Brand:");

        jLabel10.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel10.setText("Quantity:");

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

        btnInsert.setBackground(new java.awt.Color(93, 190, 163));
        btnInsert.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        txtClear.setBackground(new java.awt.Color(93, 190, 163));
        txtClear.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        txtClear.setText("Clear");
        txtClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClearActionPerformed(evt);
            }
        });

        tblDataInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "SerialNo", "ItemName", "Model", "Specification", "Category", "Brand", "Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        }

        jLabel13.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel13.setText("ADD ITEM FORM");

        jLabel15.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel15.setText("Specification:");

        cbSCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hardware", "Software" }));

        jLabel18.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel18.setText("Serial No:");

        txtSearchItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchItemActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel25.setText("Search:");

        btnViewUser3.setBackground(new java.awt.Color(0, 153, 153));
        btnViewUser3.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(330, 330, 330)
                        .addComponent(jLabel13)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(2, 2, 2)
                        .addComponent(txtSearchItem, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnViewUser3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtItemName)
                                    .addComponent(txtSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(txtSpecification, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbSCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(57, 57, 57)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(txtClear, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSerial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSpecification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(cbSCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(101, 101, 101)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInsert)
                            .addComponent(txtClear)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnViewUser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSearchItem, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel25)))
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        tabPane.addTab("tab2", jPanel3);

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
                .addContainerGap(544, Short.MAX_VALUE))
        );

        tabSuperadmin.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        tblStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Serial No.", "Item  Name", "Model", "Specification", "Category", "Brand", "Qty."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        }

        txtSearchStock.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchStockActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel27.setText("Search:");

        btnViewUser4.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser4.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser4ActionPerformed(evt);
            }
        });

        btnPrint_stock.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_stock.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnPrint_stock.setText("Print");
        btnPrint_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_stockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(btnPrint_stock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27)
                        .addGap(5, 5, 5)
                        .addComponent(txtSearchStock, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnViewUser4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnViewUser4)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(btnPrint_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSearchStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        tabSuperadmin.addTab("tab1", jPanel13);

        tblRelease.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ReleaseID", "ItemID", "SerialNo", "ItemName", "Model", "Specification", "Category", "Brand", "Qty", "ReleaseDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tblRelease);

        btnPrint_release.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_release.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnPrint_release.setText("Print");
        btnPrint_release.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_releaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPrint_release)
                .addContainerGap(911, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnPrint_release, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        tabSuperadmin.addTab("tab6", jPanel2);

        tblChecking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CheckingID", "ItemID", "Serial No.", "Name", "Model", "Specification", "Category", "Brand", "Qty.", "CheckDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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
            tblChecking.getColumnModel().getColumn(9).setResizable(false);
        }

        btnPrint_checking.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_checking.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnPrint_checking.setText("Print");
        btnPrint_checking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_checkingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btnPrint_checking)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(btnPrint_checking, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        tabSuperadmin.addTab("tab2", jPanel15);

        tblReturn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ReturnID", "ItemID", "SerialNo.", "Name", "Model", "Specification", "Category", "Brand", "Qty.", "ReturnDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false, false, false
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
            tblReturn.getColumnModel().getColumn(9).setResizable(false);
        }

        btnPrint_return.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_return.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnPrint_return.setText("Print");
        btnPrint_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_returnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(btnPrint_return)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(btnPrint_return, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        tabSuperadmin.addTab("tab3", jPanel16);

        tblRepair.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "RepairID", "ItemID", "Serial No.", "Name", "Model", "Specification", "Category", "Brand", "Qty", "RepairDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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
            tblRepair.getColumnModel().getColumn(9).setResizable(false);
        }

        btnPrint_repair.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_repair.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnPrint_repair.setText("Print");
        btnPrint_repair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_repairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(btnPrint_repair)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(btnPrint_repair, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        tabSuperadmin.addTab("tab4", jPanel17);

        tblDisposal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DisposalID", "ItemID", "Serial No.", "Name", "Model", "Specification", "Category", "Brand", "Qty.", "DisposalDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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
            tblDisposal.getColumnModel().getColumn(9).setResizable(false);
        }

        btnPrint_disposal.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_disposal.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnPrint_disposal.setText("Print");
        btnPrint_disposal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_disposalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(btnPrint_disposal)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(btnPrint_disposal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
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

        btnDisposal1.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnDisposal1.setText("Release");
        btnDisposal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisposal1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabSuperadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnStock, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDisposal1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChecking)
                .addGap(2, 2, 2)
                .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnRepair)
                .addGap(2, 2, 2)
                .addComponent(btnDisposal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnChecking, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDisposal1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRepair, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDisposal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(tabSuperadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
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
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add item.png"))); // NOI18N
        btnAdd.setText("  Add Item");
        btnAdd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 240, 60));

        btnRecords.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btnRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Records.png"))); // NOI18N
        btnRecords.setText("  Records");
        btnRecords.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecordsActionPerformed(evt);
            }
        });
        jPanel1.add(btnRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 240, 60));

        btnLogout.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btnLogout.setText("LOG OUT");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 636, 180, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        displayTotalDataCount();
        displayReleaseCount();
        displayCheckingCount();
        displayRepairCount();
        displayReturnCount();
        displayDisposalCount();
        tabPane.setSelectedIndex(0);
        displayTotalDataCount();
        
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
          tabPane.setSelectedIndex(1);
          displayDataItems();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecordsActionPerformed
        // TODO add your handling code here:
          tabPane.setSelectedIndex(2);
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

    private void txtClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_txtClearActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
        insertItem();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(0);
        displayStockItems();
    }//GEN-LAST:event_btnStockActionPerformed

    private void btnCheckingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckingActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(2);
        displayCheckingData();
    }//GEN-LAST:event_btnCheckingActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(3);
        displayReturnData();
        displayReturn();

    }//GEN-LAST:event_btnReturnActionPerformed

    private void btnRepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepairActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(4);
        displayRepair();

    }//GEN-LAST:event_btnRepairActionPerformed

    private void btnDisposalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisposalActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(5);
        displayDisposal();
        
    }//GEN-LAST:event_btnDisposalActionPerformed

    private void txtSearchStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchStockActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchStock.getText().trim();

        DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
        model.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT * FROM Stock WHERE SerialNo LIKE ? OR ItemName LIKE ? OR Model LIKE ? OR Category LIKE ? OR Brand LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%");
            pst.setString(2, "%" + searchText + "%");
            pst.setString(3, "%" + searchText + "%");
            pst.setString(4, "%" + searchText + "%");
            pst.setString(5, "%" + searchText + "%");
            rst = pst.executeQuery();

            while (rst.next()) {
                int ItemID = rst.getInt("ItemID");
                String serialNumber = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String modelValue = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int quantity = rst.getInt("Qty");

                // Map database column names to JTable column names
                model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, quantity});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error searching items in the database.");
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
    
    }//GEN-LAST:event_txtSearchStockActionPerformed

    private void txtSearchItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchItemActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchItem.getText().trim();

        DefaultTableModel model = (DefaultTableModel) tblDataInfo.getModel();
        model.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT * FROM Stock WHERE SerialNo LIKE ? OR ItemName LIKE ? OR Category LIKE ? OR Model LIKE? OR Brand LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%");
            pst.setString(2, "%" + searchText + "%");
            pst.setString(3, "%" + searchText + "%");
            pst.setString(4, "%" + searchText + "%");
            pst.setString(5, "%" + searchText + "%");
            rst = pst.executeQuery();

            while (rst.next()) {
                int ItemID = rst.getInt("ItemID");
                String serialNumber = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String modelValue = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int quantity = rst.getInt("Qty");

                // Map database column names to JTable column names
                model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, quantity});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error searching items in the database.");
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
    }//GEN-LAST:event_txtSearchItemActionPerformed

    private void btnViewUser3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser3ActionPerformed
        // TODO add your handling code here:
        txtSearchItem.setText("");
        displayDataItems();
        clear();
        
    }//GEN-LAST:event_btnViewUser3ActionPerformed

    private void btnViewUser4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser4ActionPerformed
        // TODO add your handling code here:
        txtSearchStock.setText("");
        displayStockItems();
    }//GEN-LAST:event_btnViewUser4ActionPerformed

    private void btnDisposal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisposal1ActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(1);
        displayReleaseData();
    }//GEN-LAST:event_btnDisposal1ActionPerformed

    private void btnPrint_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_stockActionPerformed
        // TODO add your handling code here:
        printStock(tblStock);
    }//GEN-LAST:event_btnPrint_stockActionPerformed

    private void btnPrint_releaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_releaseActionPerformed
        // TODO add your handling code here:
        printRelease(tblRelease);
    }//GEN-LAST:event_btnPrint_releaseActionPerformed

    private void btnPrint_checkingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_checkingActionPerformed
        // TODO add your handling code here:
        printChecking(tblChecking);
    }//GEN-LAST:event_btnPrint_checkingActionPerformed

    private void btnPrint_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_returnActionPerformed
        // TODO add your handling code here:
        printReturn(tblReturn);
    }//GEN-LAST:event_btnPrint_returnActionPerformed

    private void btnPrint_repairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_repairActionPerformed
        // TODO add your handling code here:
        printRepair(tblRepair);
    }//GEN-LAST:event_btnPrint_repairActionPerformed

    private void btnPrint_disposalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_disposalActionPerformed
        // TODO add your handling code here:
        printDisposal(tblDisposal);
    }//GEN-LAST:event_btnPrint_disposalActionPerformed

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
            java.util.logging.Logger.getLogger(addmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChecking;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDisposal;
    private javax.swing.JButton btnDisposal1;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPrint_checking;
    private javax.swing.JButton btnPrint_disposal;
    private javax.swing.JButton btnPrint_release;
    private javax.swing.JButton btnPrint_repair;
    private javax.swing.JButton btnPrint_return;
    private javax.swing.JButton btnPrint_stock;
    private javax.swing.JButton btnRecords;
    private javax.swing.JButton btnRepair;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnStock;
    private javax.swing.JButton btnViewUser3;
    private javax.swing.JButton btnViewUser4;
    private javax.swing.JComboBox<String> cbSCategory;
    private javax.swing.JPanel dashboard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblChecking;
    private javax.swing.JLabel lblDisposal;
    private javax.swing.JLabel lblRelease;
    private javax.swing.JLabel lblRepair;
    private javax.swing.JLabel lblReturn;
    private javax.swing.JLabel lblTotalqty;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JTabbedPane tabSuperadmin;
    private javax.swing.JTable tblChecking;
    private javax.swing.JTable tblDataInfo;
    private javax.swing.JTable tblDisposal;
    private javax.swing.JTable tblRelease;
    private javax.swing.JTable tblRepair;
    private javax.swing.JTable tblReturn;
    private javax.swing.JTable tblStock;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JButton txtClear;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSearchItem;
    private javax.swing.JTextField txtSearchStock;
    private javax.swing.JTextField txtSerial;
    private javax.swing.JTextField txtSpecification;
    // End of variables declaration//GEN-END:variables
}
