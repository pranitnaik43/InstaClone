package com.pdevelopers.instaclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    private Button nextBtn, saveBtn, viewBtn;
    private EditText bname, punchSpeed;
    private String allBoxers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            ParseInstallation.getCurrentInstallation().saveInBackground();

            bname=findViewById(R.id.bname);
            punchSpeed=findViewById(R.id.punchSpeed);
            nextBtn=findViewById(R.id.nextBtn);
            saveBtn=findViewById(R.id.saveBtn);
            viewBtn=findViewById(R.id.viewBtn);

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ParseObject myNewObject = new ParseObject("Boxer");
                    myNewObject.put("BoxerName", bname.getText().toString());
                    myNewObject.put("PunchSpeed", Integer.parseInt(punchSpeed.getText().toString()));

                    myNewObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(MainActivity.this, "Boxer Saved", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                bname.setText("");
                                punchSpeed.setText("");
                            } else {
                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    });
                }
            });
            viewBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //################## Get a single object ############################3
//                    ParseQuery<ParseObject> query = ParseQuery.getQuery("MyCustomClassName");
//                    query.getInBackground("<PARSE_OBJECT_ID>", new GetCallback<ParseObject>() {
//                        public void done(ParseObject result, ParseException e) {
//                            if (e == null) {
//                                System.out.println(result);
//                            } else {
//                                // something went wrong
//                            }
//                        }
//                    });

//                    ########### Get a list of objects  #####################3
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Boxer");

                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if (e == null) {
                                if(objects.size()>0){
                                    allBoxers="";
                                    for(ParseObject boxer : objects){
                                        allBoxers=allBoxers+boxer.get("BoxerName")+" "+boxer.get("PunchSpeed")+"\n";
                                    }
                                    FancyToast.makeText(MainActivity.this,allBoxers,FancyToast.LENGTH_SHORT,FancyToast.DEFAULT,true).show();
                                }
                            } else {
                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    });
                }
            });
        }catch(Exception e){
            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }
}
