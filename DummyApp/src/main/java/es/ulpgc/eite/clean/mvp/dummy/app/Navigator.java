package es.ulpgc.eite.clean.mvp.dummy.app;

import es.ulpgc.eite.clean.mvp.dummy.bye.Bye;
import es.ulpgc.eite.clean.mvp.dummy.hello.Hello;

public interface Navigator {
  void goToHelloScreen(Bye.ByeTo presenter);
  void goToByeScreen(Hello.HelloTo presenter);
}
