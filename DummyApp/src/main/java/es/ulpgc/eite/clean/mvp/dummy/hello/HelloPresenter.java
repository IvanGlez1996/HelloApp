package es.ulpgc.eite.clean.mvp.dummy.hello;


import android.content.Context;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.dummy.app.Mediator;

public class HelloPresenter extends GenericPresenter
    <Hello.PresenterToView, Hello.PresenterToModel, Hello.ModelToPresenter, HelloModel>
    implements Hello.ViewToPresenter, Hello.ModelToPresenter, Hello.HelloTo, Hello.ToHello {


  private boolean toolbarVisible;
  private boolean buttonClicked;
  private boolean textVisible;
  private boolean progressBarVisible;

  /**
   * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
   * Responsible to initialize MODEL.
   * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
   * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onCreate(Hello.PresenterToView view) {
    super.onCreate(es.ulpgc.eite.clean.mvp.dummy.hello.HelloModel.class, this);
    setView(view);
    Log.d(TAG, "calling onCreate()");

    Log.d(TAG, "calling startingHelloScreen()");
    Mediator app = (Mediator) getView().getApplication();
    try {
      app.startingHelloScreen(this);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Operation called by VIEW after its reconstruction.
   * Always call {@link GenericPresenter#setView(ContextView)}
   * to save the new instance of PresenterToView
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onResume(Hello.PresenterToView view) {
    setView(view);
    Log.d(TAG, "calling onResume()");

    if(configurationChangeOccurred()) {
      getView().setSayHelloLabel(getModel().getSayHelloLabel());
      getView().setGoToByeLabel(getModel().getGoToByeLabel());

      checkToolbarVisibility();
      checkTextVisibility();

      if (buttonClicked) {
        getView().setText(getModel().getText());
      }
    }
  }

  /**
   * Helper method to inform Presenter that a onBackPressed event occurred
   * Called by {@link GenericActivity}
   */
  @Override
  public void onBackPressed() {
    Log.d(TAG, "calling onBackPressed()");
  }

  /**
   * Hook method called when the VIEW is being destroyed or
   * having its configuration changed.
   * Responsible to maintain MVP synchronized with Activity lifecycle.
   * Called by onDestroy methods of the VIEW layer, like: {@link GenericActivity#onDestroy()}
   *
   * @param isChangingConfiguration true: configuration changing & false: being destroyed
   */
  @Override
  public void onDestroy(boolean isChangingConfiguration) {
    super.onDestroy(isChangingConfiguration);
    Log.d(TAG, "calling onDestroy()");
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // View To Presenter /////////////////////////////////////////////////////////////

  @Override
  public void onSayHelloBtnClicked() throws InterruptedException {
    Log.d(TAG, "calling onSayHelloBtnClicked()");
    if(isViewRunning()) {
      getModel().onChangeMsgByBtnClicked();
      getView().setText(getModel().getText());
      textVisible = true;
      buttonClicked = true;
      progressBarVisible = false;
    }
    checkTextVisibility();
  }

  @Override
  public void onGoToByeBtnClicked() {
    Log.d(TAG, "calling onGoToByeBtnClicked()");
    if(isViewRunning()) {
      getModel().onChangeMsgByBtnClicked();
      getView().setText(getModel().getText());
      textVisible = true;
      buttonClicked = true;
      progressBarVisible = false;
    }
    checkTextVisibility();
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // To Hello //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() throws InterruptedException {
    Log.d(TAG, "calling onScreenStarted()");
    if(isViewRunning()) {
      getView().setSayHelloLabel(getModel().getSayHelloLabel());
      getView().setGoToByeLabel(getModel().getGoToByeLabel());
    }
    checkToolbarVisibility();
    checkTextVisibility();
  }

  @Override
  public void setToolbarVisibility(boolean visible) {
    toolbarVisible = visible;
  }

  @Override
  public void setTextVisibility(boolean visible) {
    textVisible = visible;
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Hello To //////////////////////////////////////////////////////////////////////


  @Override
  public Context getManagedContext(){
    return getActivityContext();
  }

  @Override
  public void destroyView(){
    if(isViewRunning()) {
      getView().finishScreen();
    }
  }
  @Override
  public boolean isToolbarVisible() {
    return toolbarVisible;
  }

  @Override
  public boolean isTextVisible() {
    return textVisible;
  }


  ///////////////////////////////////////////////////////////////////////////////////

  private void checkToolbarVisibility(){
    Log.d(TAG, "calling checkToolbarVisibility()");
    if(isViewRunning()) {
      if (!toolbarVisible) {
        getView().hideToolbar();
      }
    }
  }

  private void checkProgressBarVisibility() throws InterruptedException {
    Log.d(TAG, "calling checkProgressBarVisibility()");
    if(isViewRunning()) {
      getView().showProgressBar();
      wait(1000);
      if (!progressBarVisible) {
        getView().hideProgressBar();
      }
    }
  }

  private void checkTextVisibility(){
    Log.d(TAG, "calling checkTextVisibility()");
    if(isViewRunning()) {
      if(!textVisible) {
        getView().hideText();
      } else {
        getView().showText();
      }
    }
  }

}
