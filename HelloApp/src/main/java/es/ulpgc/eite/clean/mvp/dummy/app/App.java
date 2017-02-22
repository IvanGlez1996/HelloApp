package es.ulpgc.eite.clean.mvp.dummy.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.dummy.bye.Bye;
import es.ulpgc.eite.clean.mvp.dummy.bye.ByeView;
import es.ulpgc.eite.clean.mvp.dummy.dummy.Dummy;
import es.ulpgc.eite.clean.mvp.dummy.hello.Hello;
import es.ulpgc.eite.clean.mvp.dummy.hello.HelloView;


public class App extends Application implements Mediator, Navigator {

  private DummyState toDummyState, dummyToState;
  private HelloState toHelloState, helloToState;
  private ByeState toByeState, byeToState;

  @Override
  public void onCreate() {
    super.onCreate();
    toDummyState = new DummyState();
    toDummyState.toolbarVisibility = true;
    toDummyState.textVisibility = false;
    toHelloState = new HelloState();
    toHelloState.toolbarVisibility = true;
    toHelloState.textVisibility = false;
    toHelloState.progressBarVisibility = false;
    toByeState = new ByeState();
    toByeState.toolbarVisibility = true;
    toByeState.textVisibility = false;
    toByeState.progressBarVisibility = false;
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Mediator //////////////////////////////////////////////////////////////////////

  @Override
  public void startingDummyScreen(Dummy.ToDummy presenter){
    if(toDummyState != null) {
      presenter.setToolbarVisibility(toDummyState.toolbarVisibility);
      presenter.setTextVisibility(toDummyState.textVisibility);
    }
    presenter.onScreenStarted();
  }

  @Override
  public void startingHelloScreen(Hello.ToHello presenter) {
    if(toHelloState != null) {
      presenter.setToolbarVisibility(toHelloState.toolbarVisibility);
      presenter.setTextVisibility(toHelloState.textVisibility);
      presenter.setProgressBarVisibility(toHelloState.progressBarVisibility);
      presenter.setStateText(toHelloState.text);
    }
    presenter.onScreenStarted();
  }

  @Override
  public void startingByeScreen(Bye.HelloToBye presenter)  {
    if(toByeState != null) {
      presenter.setToolbarVisibility(toByeState.toolbarVisibility);
      presenter.setTextVisibility(toByeState.textVisibility);
      presenter.setProgressBarVisibility(toByeState.progressBarVisibility);
      presenter.setStateText(toByeState.text);
      Log.d("Test", toByeState.text);
    }
    presenter.onScreenStarted();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Navigator /////////////////////////////////////////////////////////////////////


  @Override
  public void goToHelloScreen(Bye.ByeToHello presenter) {
    byeToState = new ByeState();
    byeToState.toolbarVisibility = presenter.isToolbarVisible();
    byeToState.textVisibility = presenter.isTextVisible();
    byeToState.progressBarVisibility = presenter.isProgressBarVisible();
    byeToState.text = presenter.getStateText();
    toHelloState.text = presenter.getStateText();
    Context view = presenter.getManagedContext();
    if (view != null) {
      view.startActivity(new Intent(view, HelloView.class));
      presenter.destroyView();
    }

  }

  @Override
  public void goToByeScreen(Hello.HelloToBye presenter) {
    helloToState = new HelloState();
    helloToState.textVisibility = presenter.isTextVisible();
    helloToState.toolbarVisibility = presenter.isToolbarVisible();
    helloToState.progressBarVisibility = presenter.isProgressBarVisible();
    helloToState.text = presenter.getStateText();
    toByeState.text = presenter.getStateText();
    Log.d("Test", presenter.getStateText());

    Context view = presenter.getManagedContext();
    if (view != null) {
      view.startActivity(new Intent(view, ByeView.class));
      presenter.destroyView();
    }

  }

  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  private class DummyState {
    boolean toolbarVisibility;
    boolean textVisibility;
  }

  private class HelloState {
    boolean toolbarVisibility;
    boolean textVisibility;
    boolean progressBarVisibility;
    String text;
  }

  private class ByeState {
    boolean toolbarVisibility;
    boolean textVisibility;
    boolean progressBarVisibility;
    String text;
  }

}
