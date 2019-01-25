package com.rutgeruijtendaal.db;

import org.hibernate.SessionFactory;

public class DaoManager {

    private static final DaoManager instance = new DaoManager();

    private static OrderStatusDAO orderStatusDAO;
    private static TaxBracketDAO taxBracketDAO;
    private static ProductTypeDAO productTypeDAO;
    private static PaymentTypeDAO paymentTypeDAO;

    private static UserDAO userDAO;
    private static ContactInfoDAO contactInfoDAO;

    private static ProductDAO productDAO;

    private static CartDAO cartDAO;
    private static CartItemDAO cartItemDAO;

    private static OrderDAO orderDAO;
    private static OrderedProductDAO orderedProductDAO;

    private DaoManager() {}

    public static DaoManager getInstance() {
        return instance;
    }

    public void setupDAOS(SessionFactory factory) {
        orderStatusDAO = new OrderStatusDAO(factory);
        taxBracketDAO = new TaxBracketDAO(factory);
        productTypeDAO = new ProductTypeDAO(factory);
        paymentTypeDAO = new PaymentTypeDAO(factory);

        userDAO = new UserDAO(factory);
        contactInfoDAO = new ContactInfoDAO(factory);

        productDAO = new ProductDAO(factory);

        cartDAO = new CartDAO(factory);
        cartItemDAO = new CartItemDAO(factory);

        orderDAO = new OrderDAO(factory);
        orderedProductDAO = new OrderedProductDAO(factory);
    }

    public OrderStatusDAO getOrderStatusDAO() {
        return orderStatusDAO;
    }

    public TaxBracketDAO getTaxBracketDAO() {
        return taxBracketDAO;
    }

    public ProductTypeDAO getProductTypeDAO() {
        return productTypeDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public ContactInfoDAO getContactInfoDAO() {
        return contactInfoDAO;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public CartDAO getCartDAO() { return cartDAO; }

    public CartItemDAO getCartItemDAO() { return cartItemDAO; }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public OrderedProductDAO getOrderedProductDAO() {
        return orderedProductDAO;
    }

    public PaymentTypeDAO getPaymentTypeDAO() {
        return paymentTypeDAO;
    }
}
