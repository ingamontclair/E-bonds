package edu.bonds;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class HelloWorldApplication extends Application {

  public HelloWorldApplication() {
  }

  @Override
  public Set<Object> getSingletons() {
    HashSet<Object> set = new HashSet<Object>();
    return set;
  }
}

