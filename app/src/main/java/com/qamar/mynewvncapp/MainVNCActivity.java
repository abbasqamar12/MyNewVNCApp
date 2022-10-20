package com.qamar.mynewvncapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainVNCActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtNickName, edtUserName, edtPassword, edtIpAddress, edtPort;
    TextView txtConnect;

    private VncDatabase database;
    private ConnectionBean selected;
    private boolean repeaterTextSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vnc_activity_main);
        edtNickName = findViewById(R.id.edtNickName);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        edtIpAddress = findViewById(R.id.edtIP);
        edtPort = findViewById(R.id.edtPort);
        txtConnect = findViewById(R.id.txtConnect);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        selected = new ConnectionBean();

        //  selected = null;
        setOnClickListener();
    }

    private void setOnClickListener() {
        txtConnect.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtConnect:
                canvasStart();
                break;
            default:
                Toast.makeText(this, "Something went wrong, Please try again later", Toast.LENGTH_SHORT).show();
        }
    }

    private void canvasStart() {
        //  if (selected == null) return;
        ActivityManager.MemoryInfo info = Utils.getMemoryInfo(this);
        if (info.lowMemory) {
            Log.d("VNC", "Low Memory");
            // Low Memory situation.  Prompt.
            Utils.showYesNoPrompt(this, "Continue?", "Android reports low system memory.\nContinue with VNC connection?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    vnc();
                }
            }, null);
        } else
            vnc();
    }

    private void updateSelectedFromView() {
        if (selected == null) {
            // return;
        }
        selected.setAddress(edtIpAddress.getText().toString());
        Log.d("VNC", "IP : " + edtIpAddress.getText().toString());
        try {
            selected.setPort(Integer.parseInt(edtPort.getText().toString()));
            Log.d("VNC", "Port : " + edtPort.getText().toString());
        } catch (NumberFormatException nfe) {

        }
        selected.setNickname(edtNickName.getText().toString());
        Log.d("VNC", "Nick Name : " + edtNickName.getText().toString());
        selected.setUserName(edtUserName.getText().toString());
        //  selected.setForceFull(groupForceFullScreen.getCheckedRadioButtonId()==R.id.radioForceFullScreenAuto ? BitmapImplHint.AUTO : (groupForceFullScreen.getCheckedRadioButtonId()==R.id.radioForceFullScreenOn ? BitmapImplHint.FULL : BitmapImplHint.TILE));
        selected.setPassword(edtPassword.getText().toString());
        Log.d("VNC", "Password : " + edtPassword.getText().toString());
        // selected.setKeepPassword(checkboxKeepPassword.isChecked());
        selected.setKeepPassword(true);
        // selected.setUseLocalCursor(checkboxLocalCursor.isChecked());
        selected.setUseLocalCursor(true);
        // selected.setColorModel(((COLORMODEL)colorSpinner.getSelectedItem()).nameString());
        //  selected.setUseWakeLock(checkboxWakeLock.isChecked());
        selected.setUseWakeLock(false);
        // selected.setUseImmersive(checkboxUseImmersive.isChecked());
        selected.setUseImmersive(true);
        if (repeaterTextSet) {
            // selected.setRepeaterId(repeaterText.getText().toString());
            selected.setUseRepeater(true);
        } else {
            selected.setUseRepeater(false);
        }
    }

    private void saveAndWriteRecent() {
       /* SQLiteDatabase db = database.getWritableDatabase();
        db.beginTransaction();
        try
        {
            selected.save(db);
            MostRecentBean mostRecent = getMostRecent(db);
            if (mostRecent == null)
            {
                mostRecent = new MostRecentBean();
                mostRecent.setConnectionId(selected.get_Id());
                mostRecent.Gen_insert(db);
            }
            else
            {
                mostRecent.setConnectionId(selected.get_Id());
                mostRecent.Gen_update(db);
            }
            db.setTransactionSuccessful();
        }
        finally
        {
            db.endTransaction();
        }*/
    }


    private void vnc() {
        Log.d("VNC", "Open VNC");
        updateSelectedFromView();
       // saveAndWriteRecent();
        Intent intent = new Intent(this, VncCanvasActivity.class);
        intent.putExtra(VncConstants.CONNECTION, selected.Gen_getValues());
        startActivity(intent);
    }
}