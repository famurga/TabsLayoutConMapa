package com.example.fragmentsdinamicos.Controlador;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fragmentsdinamicos.Fragments.CoordenadasFragment;
import com.example.fragmentsdinamicos.Fragments.DireccionFragment;
import com.example.fragmentsdinamicos.Fragments.MapaFragment;

public class PagerController extends FragmentPagerAdapter {

    int numdeTabs;

    public PagerController(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numdeTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MapaFragment();
            case 1:
                return new CoordenadasFragment();
            case 2:
                return new DireccionFragment();
            default:
                return null;



        }
    }

    @Override
    public int getCount() {
        return numdeTabs;
    }
}
