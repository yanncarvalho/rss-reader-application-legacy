package br.dev.yann.rssreader.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.dev.yann.rssreader.auth.JWTToken;
import br.dev.yann.rssreader.data.RssDao;
import br.dev.yann.rssreader.entity.User;

public class RssUrlService {

  @Inject
  @Named("Rss")
  private RssDao dao;

  @Inject
  private JWTToken tokenJWT;

  public List<String> findAll(String token) {
    User user  = dao.findByUsername((String)tokenJWT.decode(token).get("username"));
    return user.getUrlsRss();
  }

  public void deleteRss(String token, List<String> rssUrls) {
    User user  = dao.findByUsername((String)tokenJWT.decode(token).get("username"));
    user.removeAllUrlsRss(rssUrls);
    dao.deleteRss(user);
  }

  public void addRss(String token, List<String> rssUrls) {
    User user  = dao.findByUsername((String)tokenJWT.decode(token).get("username"));
    user.addAllUrlRss(rssUrls);
    dao.addRss(user);
  }

  public void deleteAllRss(String token) {
    User user  = dao.findByUsername((String)tokenJWT.decode(token).get("username"));
    user.cleanUrlsRss();
    dao.deleteRss(user);
  }

  public List<String> hasRss(String token, List<String> rssUrls) {
     User user  = dao.findByUsername((String)tokenJWT.decode(token).get("username"));
      rssUrls.retainAll(user.getUrlsRss());
    return rssUrls;
  }


}