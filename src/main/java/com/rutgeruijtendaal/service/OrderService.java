package com.rutgeruijtendaal.service;

import com.rutgeruijtendaal.auth.jwt.AuthUser;
import com.rutgeruijtendaal.core.db.entities.*;
import com.rutgeruijtendaal.core.json.OrderJson;
import com.rutgeruijtendaal.db.*;
import com.rutgeruijtendaal.exceptions.DropwizardException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderService {

    private OrderDAO orderDAO;
    private OrderedProductDAO orderedProductDAO;
    private CartDAO cartDAO;
    private CartItemDAO cartItemDAO;
    private ProductDAO productDAO;
    private PaymentTypeDAO paymentTypeDAO;

    public OrderService() {
        this.orderDAO = DaoManager.getInstance().getOrderDAO();
        this.orderedProductDAO = DaoManager.getInstance().getOrderedProductDAO();
        this.cartDAO = DaoManager.getInstance().getCartDAO();
        this.cartItemDAO = DaoManager.getInstance().getCartItemDAO();
        this.productDAO = DaoManager.getInstance().getProductDAO();
        this.paymentTypeDAO = DaoManager.getInstance().getPaymentTypeDAO();
    }

    public Order placeOrder(AuthUser authUser, OrderJson orderJson) throws DropwizardException {
        Cart cart = cartDAO.getActiveCart(authUser.getId());
        List<CartItem> cartItems = cartItemDAO.getCartItems(cart.getCartId());
        Optional<PaymentType> paymentType = paymentTypeDAO.findById(orderJson.paymentTypeId);

        if(cartItems.size() < 1) {
            throw new DropwizardException(400, "Cart can't be empty");
        }

        if(!paymentType.isPresent()) {
            throw new DropwizardException(404, "Couldn't find payment type");
        }

        Order order = buildNewOrder(authUser, orderJson.paymentTypeId);
        order = orderDAO.create(order);

        createOrderedProducts(authUser, order, cartItems);

        createNewCart(authUser, cart);

        return order;
    }

    public Order updateOrder(AuthUser authUser, Order order) throws DropwizardException {
        if(order.getUserId() != authUser.getId()) {
            throw new DropwizardException(401, "You do not own this order");
        }

        return this.orderDAO.update(order);
    }

    public List<Order> getAllByUser(AuthUser authUser) {
        return orderDAO.getOrdersByUser(authUser);
    }

    public List<OrderedProduct> getProductsForOrder(AuthUser authUser, int orderId) throws DropwizardException{
        Optional optional = orderDAO.findById(orderId);

        if(!optional.isPresent()) {
            throw new DropwizardException(404, "Order not found");
        }

        Order order = (Order) optional.get();

        if(!(order.getUserId() == authUser.getId())) {
            throw new DropwizardException(401, "Not a valid order for this user");
        }

        return orderedProductDAO.getOrderedProducts(orderId);

    }

    private void createNewCart(AuthUser authUser, Cart cart) {
        cart.setOrdered(true);
        cartDAO.update(cart);

        Cart newCart = new Cart(authUser.getId());
        cartDAO.create(newCart);
    }

    private void createOrderedProducts(AuthUser authUser, Order order, List<CartItem> cartItems) throws DropwizardException {
        for(CartItem cartItem: cartItems) {
            Optional optional = productDAO.findById(cartItem.getProductId());

            Product product;

            if(optional.isPresent()) {
                product = (Product) optional.get();
            } else {
                throw new DropwizardException(404, "Product not found");
            }

            OrderedProduct orderedProduct = buildNewOrderedProduct(authUser, order, cartItem, product);

            orderedProductDAO.create(orderedProduct);
        }
    }

    private OrderedProduct buildNewOrderedProduct(AuthUser authUser, Order order, CartItem cartItem, Product product) {
        return new OrderedProduct(
                cartItem.getProductId(),
                order.getOrderId(),
                product.getPrice(),
                cartItem.getItemCount()
        );
    }

    private Order buildNewOrder(AuthUser authUser, int paymentId) {
        return new Order(
                new Timestamp(new Date().getTime()),
                authUser.getId(),
                1,
                paymentId,
                1
        );
    }
}
