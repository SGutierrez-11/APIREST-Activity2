package services;

import entity.Orders;
import model.Message;
import entity.Users;
import providers.OrdersProvider;
import providers.UsersProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("users")
public class UsersServices {

    @POST
    @Path("createUsers")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createUser(Users user){
        UsersProvider provider = new UsersProvider();
        try {
            provider.create(user);
            return Response.status(200).entity(user).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).entity(new Message("FAILED")).build();
        }
    }

    @GET
    @Path("showInformation/{userId}")
    @Produces("application/json")
    public Response getInformationUser(@PathParam("userId") int userId) throws SQLException {
        OrdersProvider provider = new OrdersProvider();

        try {
            ArrayList<Orders> orders = provider.getInformationUser(userId);
            return Response.status(200).entity(orders).build();
        } catch (SQLException e) {
            Message m = new Message("SQL Exception");
            return Response.status(500).entity(m).build();
        }
    }
}
