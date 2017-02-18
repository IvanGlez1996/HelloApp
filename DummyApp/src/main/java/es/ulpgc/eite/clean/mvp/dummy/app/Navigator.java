package es.ulpgc.eite.clean.mvp.dummy.app;

import es.ulpgc.eite.clean.mvp.dummy.dummy.Dummy;
import es.ulpgc.eite.clean.mvp.dummy.hello.Hello;

public interface Navigator {
  void goToNextDummyScreen(Dummy.DummyTo presenter);
  void goToNextHelloScreen(Hello.HelloTo presenter);
}
