package com.rutgeruijtendaal.db;

import com.rutgeruijtendaal.core.db.entities.ContactInfo;
import org.hibernate.SessionFactory;

public class ContactInfoDAO extends BaseDAO<ContactInfo> {

    public ContactInfoDAO(SessionFactory factory) {super(factory);}

    public ContactInfo create(ContactInfo  contactInfo) {
        return persist(contactInfo);
    }

}
