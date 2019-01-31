package com.marc.app.firebase;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button logIn, signUp;
    EditText email, password;

    private LogInListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        email = view.findViewById(R.id.emailEditText);
        password = view.findViewById(R.id.passwordEditText);
        logIn = view.findViewById(R.id.logInButton);
        signUp = view.findViewById(R.id.signUpButton);

        // Cuando se de al boton se lanzara el metodo logIn() pasandole los argumentos cogidos de los EditText
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.logIn(email.getText().toString(), password.getText().toString());
            }
        });

        // Cuando se de al boton se lanzara el metodo signUp() pasandole los argumentos cogidos de los EditText
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.signUp(email.getText().toString(), password.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LogInListener) {
            mListener = (LogInListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // Interfaz de los metodos a usar
    public interface LogInListener {
        void logIn(String email, String password);
        void signUp(String email, String password);
    }
}
