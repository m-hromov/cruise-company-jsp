package com.cruisecompany.service.impl;

import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.PassengerDAO;
import com.cruisecompany.db.dao.UserAccountDAO;
import com.cruisecompany.db.dto.DTOMapper;
import com.cruisecompany.db.dto.UserAccountDTO;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.service.UserAccountService;
import com.cruisecompany.util.PasswordEncryption;

import java.util.Optional;

public class UserAccountServiceImpl implements UserAccountService {
    UserAccountDAO userAccountDAO;
    PassengerDAO passengerDAO;

    public UserAccountServiceImpl() {
        userAccountDAO = DAOFactory.getInstance().getUserAccountDAO();
        passengerDAO = DAOFactory.getInstance().getPassengerDAO();
    }

    @Override
    public Optional<UserAccountDTO> signIn(String login, String password) {
        Optional<UserAccount> optionalUserAccount = userAccountDAO.getUserAccountByLogin(login);
        if(optionalUserAccount.isPresent()) {
            UserAccount userAccount = optionalUserAccount.get();
            if(userAccount.getPassword().equals(PasswordEncryption.passwordToHash(password)))
                return optionalUserAccount.map(DTOMapper::toUserAccountDTO);
        }
        return Optional.empty();
    }

    @Override
    public boolean signUp(Passenger passenger) {
        UserAccount userAccount = passenger.getUserAccount();
        long userAccountId = userAccountDAO.save(userAccount);
        userAccount.setId(userAccountId);
        long passengerId = passengerDAO.save(passenger);
        passenger.setId(passengerId);
        return true;
    }

}
