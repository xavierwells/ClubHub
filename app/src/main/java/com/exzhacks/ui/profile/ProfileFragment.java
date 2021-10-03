package com.exzhacks.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.exzhacks.R;

import static com.exzhacks.MainActivity.curUser;

public class ProfileFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView name;
        TextView uni;
        TextView loc;

        name = root.findViewById(R.id.tv_name);
        uni = root.findViewById(R.id.tv_uniname);
        loc = root.findViewById(R.id.tv_loc);

        name.setText(curUser.getFirstName() + " " + curUser.getLastName());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}