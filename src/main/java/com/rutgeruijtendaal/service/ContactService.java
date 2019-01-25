package com.rutgeruijtendaal.service;

import com.rutgeruijtendaal.auth.jwt.AuthUser;
import com.rutgeruijtendaal.core.db.entities.ContactInfo;
import com.rutgeruijtendaal.core.db.entities.User;
import com.rutgeruijtendaal.db.ContactInfoDAO;
import com.rutgeruijtendaal.db.DaoManager;
import com.rutgeruijtendaal.db.UserDAO;
import com.rutgeruijtendaal.exceptions.DropwizardException;
import com.rutgeruijtendaal.utils.ValidationService;
import io.dropwizard.auth.PrincipalImpl;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactService {

    private ContactInfoDAO contactInfoDAO;
    private UserDAO userDAO;

    public ContactService() {
        this.contactInfoDAO = DaoManager.getInstance().getContactInfoDAO();
        this.userDAO = DaoManager.getInstance().getUserDAO();
    }

    public ContactInfo saveNewContact(PrincipalImpl principal, ContactInfo contactInfo) throws DropwizardException {
        Optional<User> optionalUser = userDAO.findById(contactInfo.getUserId());

        if(!optionalUser.isPresent()) {
            throw new DropwizardException(404, "User not found");
        }

        User user = optionalUser.get();

        if(!user.getEmail().equals(principal.getName())) {
            throw new DropwizardException(400, "Not your account");
        }

        validateContactInfo(contactInfo);

        return contactInfoDAO.create(contactInfo);
    }

    public ContactInfo getContactInfo(AuthUser authUser) {
        return findSafely(authUser.getId());

    }

    private boolean validateContactInfo(ContactInfo contactInfo) throws DropwizardException{
        List<String> errors = new ArrayList<>();

        if(!ValidationService.isValidName(contactInfo.getFirstName())) {
            errors.add("Incorrect First Name");
        }

        if(!ValidationService.isValidName(contactInfo.getLastName())) {
            errors.add("Incorrect Last Name");
        }

        if(!ValidationService.isValidName(contactInfo.getStreetName())) {
            errors.add("Incorrect Street Name");
        }

        if(!ValidationService.isValidName(contactInfo.getCountry())) {
            errors.add("Incorrect Country");
        }

        if(!ValidationService.isValidPostNumbers(contactInfo.getZipNumbers())) {
            errors.add("Incorrect Zip Numbers");
        }

        if(!ValidationService.isValidPostLetters(contactInfo.getZipLetters())) {
            errors.add("Incorrect Zip Letters");
        }

        if(!ValidationService.isValidHouseNumber(contactInfo.getHouseNumber())) {
            errors.add("Incorrect House Number");
        }

        if(errors.size() > 0) {
            throw new DropwizardException(400, errors.get(0));
        }

        return true;

    }

    private ContactInfo findSafely(int contactInfoId) {
        return contactInfoDAO.findById(contactInfoId).orElseThrow(() -> new NotFoundException("No such contact info."));
    }
}
