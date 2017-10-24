package edu.brandeis.cs.elizabethkoshelev.expenselog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Elizabeth on 10/24/2016.
 */
public class AddExpense extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_add);
    }

    public void goSave(View view){
        EditText description = (EditText) findViewById(R.id.editDescription);
        String desc = description.getText().toString();
        EditText notes = (EditText) findViewById(R.id.editNotes);
        String note = notes.getText().toString();
        Intent goBack= new Intent();
        goBack.putExtra("getDescription", desc);
        goBack.putExtra("getNote", note);
        setResult(RESULT_OK, goBack);
        finish();
    }

    public void goCancel(View view){
        Intent goBack= new Intent();
        goBack.putExtra("getDescription", "");
        goBack.putExtra("getNote", "");
        setResult(RESULT_OK, goBack);
        finish();
    }
}
