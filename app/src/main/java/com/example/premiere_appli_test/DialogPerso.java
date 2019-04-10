package com.example.premiere_appli_test;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class DialogPerso {


    public static void showDialog(page_1 mainactivity, String texte){
        Dialog dialog = new Dialog(mainactivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alertdialog);

        TextView tx = (TextView) dialog.findViewById(R.id.texte);
        tx.setText(texte);

        Button bt1 = (Button) dialog.findViewById(R.id.button1);
        Button bt2 = (Button) dialog.findViewById(R.id.button2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Quitter
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Commencer
            }
        });

        dialog.show();
    }
}
