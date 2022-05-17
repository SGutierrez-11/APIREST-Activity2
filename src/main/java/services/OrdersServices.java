package services;

import entity.Orders;
import entity.Users;
import model.Message;
import model.Order_Product;
import providers.OrdersProvider;
import providers.UsersProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("orders")
public class OrdersServices {

    @POST
    @Path("createOrders")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createOrders(Orders orders){
        OrdersProvider provider = new OrdersProvider();
        try {
            provider.create(orders);
            return Response.status(200).entity(orders).build();
        } catch (SQLException e) {
            return Response.status(500).entity(new Message(""+ e.getMessage())).build();
        }
    }

    @POST
    @Path("addProducts")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addProducts(Order_Product order_product){
        OrdersProvider provider = new OrdersProvider();
        try {
            provider.addProduct(order_product);
            return Response.status(200).entity(order_product).build();
        } catch (SQLException e) {
            return Response.status(500).entity(new Message(""+ e.getMessage())).build();
        }
    }

    @PUT
    @Path("deleteProduct")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteProduct(Order_Product order_product){
        OrdersProvider provider = new OrdersProvider();
        try {
            provider.deleteProduct(order_product);
            return Response.status(200).entity(order_product).build();
        } catch (SQLException e) {
            return Response.status(500).entity(new Message(""+ e.getMessage())).build();
        }
    }

    @PUT
    @Path("updateOrder/{orderId}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateOrder(@PathParam("orderId") int orderId){
        OrdersProvider provider = new OrdersProvider();
        try {
            provider.updatePay(orderId);
            return Response.status(200).entity(orderId).build();
        } catch (SQLException e) {
            return Response.status(500).entity(new Message(""+ e.getMessage())).build();
        }
    }

}
