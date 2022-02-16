package br.com.yann.rssreader.service;

import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.yann.rssreader.auth.EncryptPassword;
import br.com.yann.rssreader.data.AuthAdminDao;
import br.com.yann.rssreader.entity.User;

@Stateful
public class AuthAdminService {

  @Inject
  @Named("AuthAdmin")
  private AuthAdminDao dao;

  @Inject
  private EncryptPassword crypto;


  public List<User> findAll() {
    return dao.findAll();
  }
  //TODO ajeitar
  public void updateAnyUser (User user){
    user.setPassword(crypto.hash(user.getPassword()));
    dao.update(user);
  }

  public User findAnyUserByUsername(String username) {
    return dao.findByUsername(username);
  }

  public void deleteAnyUser(String username) {
    dao.delete(dao.findByUsername(username));
  }


}
