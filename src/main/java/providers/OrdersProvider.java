package providers;

import db.DBConnection;
import entity.Orders;
import entity.Products;
import model.Order_Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentNavigableMap;

public class OrdersProvider {

    public void create(Orders orders) throws SQLException {
        String sql = "INSERT INTO ordersA00369145(creationDate, payDate, isPay, amountProduct, userId) VALUES ('$CREATIONDATE', '$PAYDATE', '$ISPAY', '$AMOUNTPRODUCT', '$USERID')";
        sql = sql.replace("$CREATIONDATE", "" + new Date().getTime());
        sql = sql.replace("$PAYDATE", ""+0);
        sql = sql.replace("$ISPAY", ""+orders.getIsPay());
        sql = sql.replace("$AMOUNTPRODUCT", ""+orders.getAmountProduct());
        sql = sql.replace("$USERID", ""+orders.getUserId());
        DBConnection connection = new DBConnection();
        connection.connect();
        connection.commandSQL(sql);
        connection.disconnect();
    }

    public ArrayList<Orders> getInformationUser(int id1) throws SQLException {

        DBConnection connection = new DBConnection();
        ArrayList<Orders> orders = new ArrayList<>();



        String sql = "SELECT * FROM ordersA00369145 WHERE userId = $USERID";
        sql = sql.replace("$USERID",""+id1);

        connection.connect();

        ResultSet results = connection.getDataBySQL(sql);

        while(results.next()){
            int id = results.getInt("id");
            long creationDate = results.getLong("creationDate");
            long payDate = results.getLong("payDate");
            int isPay = results.getInt("isPay");
            int amountProduct = results.getInt("amountProduct");
            int userId = results.getInt("userId");
            Orders order = new Orders(id, creationDate, payDate, isPay, amountProduct, userId);
            orders.add(order);
        }
        connection.disconnect();
        return orders;
    }

    public void addProduct(Order_Product order_product) throws SQLException {

        String sql = "INSERT INTO orders_productsA00369145(amount, orderId, productId) VALUES ('$AMOUNT', '$ORDERID','$PRODUCTID')";
        sql = sql.replace("$AMOUNT", ""+order_product.getAmountProduct());
        sql = sql.replace("$ORDERID", ""+order_product.getOrderId());
        sql = sql.replace("$PRODUCTID", ""+order_product.getProductId());
        DBConnection connection = new DBConnection();
        connection.connect();
        connection.commandSQL(sql);
        connection.disconnect();
    }

    public void deleteProduct(Order_Product order_product) throws SQLException {
        DBConnection connection = new DBConnection();

        String sql = "SELECT * FROM orders_productsA00369145 WHERE orderId=$ORDERID AND productId=$PRODUCTID";
        sql = sql.replace("$ORDERID", ""+order_product.getOrderId());
        sql = sql.replace("$PRODUCTID", ""+order_product.getProductId());

        connection.connect();

        ResultSet results = connection.getDataBySQL(sql);

        Order_Product deleteProduct = new Order_Product();

        while(results.next()) {
            int orderId = results.getInt("orderId");
            int productId = results.getInt("productId");
            int amount = results.getInt("amount");
            deleteProduct.setOrderId(orderId);
            deleteProduct.setProductId(productId);
            deleteProduct.setAmountProduct(amount);
        }

        int minus = deleteProduct.getAmountProduct()-order_product.getAmountProduct();

        sql = "UPDATE orders_productsA00369145 SET amount=$AMOUNT WHERE orderId=$ORDERID AND productId=$PRODUCTID";
        sql = sql.replace("$AMOUNT", ""+minus);
        sql = sql.replace("$ORDERID", ""+order_product.getOrderId());
        sql = sql.replace("$PRODUCTID", ""+order_product.getProductId());

        connection.commandSQL(sql);
        connection.disconnect();
    }

    public void updatePay(int id) throws SQLException {
        String sql = "UPDATE ordersA00369145 SET isPay=$ISPAY AND payDate=$PAYDATE WHERE id=$ID";
        sql = sql.replace("$ISPAY", ""+1);
        sql = sql.replace("$PAYDATE", "" + new Date().getTime());
        sql = sql.replace("$ID", ""+ id);

        DBConnection connection = new DBConnection();
        connection.connect();
        connection.commandSQL(sql);
        connection.disconnect();

    }


}
