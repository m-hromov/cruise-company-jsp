package com.cruisecompany.service.impl;

import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.UserAccountDAO;
import com.cruisecompany.db.dto.DTOMapper;
import com.cruisecompany.db.dto.UserAccountDTO;
import com.cruisecompany.db.entity.UserAccount;
import com.cruisecompany.service.UserAccountService;
import com.cruisecompany.utils.PasswordEncryption;

import java.util.Optional;

public class UserAccountServiceImpl implements UserAccountService {
    UserAccountDAO userAccountDAO;

    public UserAccountServiceImpl() {
        userAccountDAO = DAOFactory.getInstance().getUserAccountDAO();
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

}
