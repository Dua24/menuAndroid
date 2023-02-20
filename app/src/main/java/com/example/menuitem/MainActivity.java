package com.example.menuitem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

public class MainActivity extends AppCompatActivity {
Button btnPopup,btnContextual;
MenuItem op1,op2,op3;
ActionMode myActMode = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPopup = findViewById(R.id.popupMenuBtn);
        btnContextual= findViewById(R.id.btnContextual);

        //handle show popup menu
        btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, btnPopup);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                        startActivity(intent);
                        return true;
                    }
                });
                // Showing the popup menu
                popupMenu.show();
            }
        });

        //handle show Contextual
        btnContextual.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (myActMode != null) {
                    return false;
                }
                myActMode = startActionMode(myActModeCallback);

                return true;
            }
        });

    }



    //handle Options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        op1 = findViewById(R.id.option1);
        op2 = findViewById(R.id.option2);
        op3 = findViewById(R.id.option3);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String idItem = Integer.toString(item.getItemId());
        switch (item.getItemId()) {
            case R.id.option1:
            case R.id.option2:
            case R.id.option3:
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //handle Contextual
    private ActionMode.Callback myActModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.contextual, menu);
            mode.setTitle("Select option here");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.option1:
                case R.id.option2:
                case R.id.option3:
                    Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(intent);
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            myActMode = null;
        }
    };
}