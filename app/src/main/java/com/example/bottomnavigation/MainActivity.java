package com.example.bottomnavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bottomnavigation.databinding.ActivityMainBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    private BottomSheetDialog myBottomSheetDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        replaceFragment(new HomeFragment());
       binding.buttonNavigation.setBackground(null);

       binding.buttonNavigation.setOnItemSelectedListener(item -> {

           switch (item.getItemId()){
               case R.id.home:
                   replaceFragment(new HomeFragment());
                   break;

               case R.id.Subscription:
                   replaceFragment(new SubscriptionFragment());
                   break;

               case R.id.library:
                   replaceFragment(new LibraryFragment());
                   break;

               case R.id.shorts:
                   replaceFragment(new ShortsFragment());
                   break;
           }
           return true;
       });
       binding.fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

                   showBottomDialog();
           }
       });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
    private void showBottomDialog(){
        myBottomSheetDialog = new BottomSheetDialog(this);
        myBottomSheetDialog.setContentView(R.layout.buttomsheetlayout);


        LinearLayout videoLayout = myBottomSheetDialog.findViewById(R.id.layoutVideo);
        LinearLayout shareLayout = myBottomSheetDialog.findViewById(R.id.layoutShare);
        LinearLayout shortsLayout = myBottomSheetDialog.findViewById(R.id.layoutShort);
        LinearLayout cancelButton = myBottomSheetDialog.findViewById(R.id.button_cancel);

        shortsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myBottomSheetDialog.dismiss();
                Toast.makeText(MainActivity.this, "Shorts", Toast.LENGTH_SHORT).show();
            }
        });

        shareLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myBottomSheetDialog.dismiss();
                Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
            }
        });
        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myBottomSheetDialog.dismiss();
                Toast.makeText(MainActivity.this, "Upload a video is clicked", Toast.LENGTH_SHORT).show();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myBottomSheetDialog.dismiss();

            }
        });
        myBottomSheetDialog.show();
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}