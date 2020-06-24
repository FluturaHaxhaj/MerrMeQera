package com.fiek.ppmapp.Lista;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.fiek.ppmapp.R;

public class FragmentShtepi extends Fragment {

    View v;
    public FragmentShtepi() {
    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.shtepi_fragment,container,false);
        return v;
    }
}
