package com.example.se_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
/**
 * The type Outside fragment is a {@link Fragment} used to settle the camera.
 */
public class OutsideFragment extends Fragment {

//    private Button entry;
//    private OnButtonClick onButtonClick;
    /**
     * To produce a new outside fragment。
     *
     * @return the outside fragment.
     */
    public static OutsideFragment newInstance() {
        return new OutsideFragment();
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_entry, container, false);
        view.findViewById(R.id.entry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                view.findViewById(R.id.entry).setVisibility(View.GONE);
                getActivity().getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.outside, CameraFragment.newInstance()).addToBackStack(null).commit();
            }
        });

        return view;
    }
    /**
     * When back pressed, this activity will be closed.
     *
     * @return the boolean
     */
    protected boolean onBackPressed() {
        getActivity().getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return true;
    }
}
