// Generated by view binder compiler. Do not edit!
package com.example.mygoalskotlin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.mygoalskotlin.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonCreateUser;

  @NonNull
  public final EditText editTextConfirmPassword;

  @NonNull
  public final EditText editTextPassword;

  @NonNull
  public final EditText editTextRegisterEmail;

  @NonNull
  public final ProgressBar registerProgressBar;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView txtError;

  private ActivityRegisterBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button buttonCreateUser, @NonNull EditText editTextConfirmPassword,
      @NonNull EditText editTextPassword, @NonNull EditText editTextRegisterEmail,
      @NonNull ProgressBar registerProgressBar, @NonNull TextView textView,
      @NonNull TextView txtError) {
    this.rootView = rootView;
    this.buttonCreateUser = buttonCreateUser;
    this.editTextConfirmPassword = editTextConfirmPassword;
    this.editTextPassword = editTextPassword;
    this.editTextRegisterEmail = editTextRegisterEmail;
    this.registerProgressBar = registerProgressBar;
    this.textView = textView;
    this.txtError = txtError;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonCreateUser;
      Button buttonCreateUser = ViewBindings.findChildViewById(rootView, id);
      if (buttonCreateUser == null) {
        break missingId;
      }

      id = R.id.editTextConfirmPassword;
      EditText editTextConfirmPassword = ViewBindings.findChildViewById(rootView, id);
      if (editTextConfirmPassword == null) {
        break missingId;
      }

      id = R.id.edit_text_password;
      EditText editTextPassword = ViewBindings.findChildViewById(rootView, id);
      if (editTextPassword == null) {
        break missingId;
      }

      id = R.id.editTextRegisterEmail;
      EditText editTextRegisterEmail = ViewBindings.findChildViewById(rootView, id);
      if (editTextRegisterEmail == null) {
        break missingId;
      }

      id = R.id.registerProgressBar;
      ProgressBar registerProgressBar = ViewBindings.findChildViewById(rootView, id);
      if (registerProgressBar == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.txtError;
      TextView txtError = ViewBindings.findChildViewById(rootView, id);
      if (txtError == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((ConstraintLayout) rootView, buttonCreateUser,
          editTextConfirmPassword, editTextPassword, editTextRegisterEmail, registerProgressBar,
          textView, txtError);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
