package com.example.app.service.impl.user;


import com.example.app.model.PasswordHistory;
import com.example.app.repository.PasswordHistoryRepository;
import com.example.app.service.PasswordHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PasswordHistoryServiceImpl implements PasswordHistoryService {

  @Autowired
  private PasswordHistoryRepository passwordHistoryRepository;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @Override
  public void saveNewRecord(PasswordHistory passwordHistory) {
    passwordHistoryRepository.saveAndFlush(passwordHistory);
  }

}
