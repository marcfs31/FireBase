package com.marc.app.firebase;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UsersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView userRecyclerView;
    List<User> usersList = new ArrayList<>();
    UsersAdapter miAdapter = new UsersAdapter(usersList);

    public UsersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UsersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UsersFragment newInstance(String param1, String param2) {
        UsersFragment fragment = new UsersFragment();
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
        View view = inflater.inflate(R.layout.fragment_users, container, false);

        //Apuntamos el recycledView creado en el xml al objeto RecycledView creado
        userRecyclerView = view.findViewById(R.id.usersRecyclerView);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        userRecyclerView.setAdapter(miAdapter);

        miAdapter.notifyDataSetChanged(); // Actualiza el listado (ahora no hace nada)
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView emailTextView;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView); //Ya está pintado el layout
            emailTextView = itemView.findViewById(R.id.emailTextView);

        }
    }

    // Creamos la clase UsersAdapter como clase interna de UsersFragment
    public class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder>{

        List<User> usersListAdapter = new ArrayList<>();

        public UsersAdapter(List<User> usersListAdapter) {
            this.usersListAdapter = usersListAdapter;
        }

        @NonNull
        @Override
        public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = getLayoutInflater().inflate(R.layout.users_viewholder,viewGroup,false);
            return new UsersViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull UsersViewHolder usersViewHolder, int i) {
            //Se pinta por pantalla
            usersViewHolder.emailTextView.setText(usersListAdapter.get(i).getEmail());
        }

        @Override
        public int getItemCount() {
            return usersListAdapter.size();
        }
    }
}
