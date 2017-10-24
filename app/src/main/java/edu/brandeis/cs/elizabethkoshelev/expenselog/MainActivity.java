//Elizabeth Koshelev
//PA 2
//Mobile Application Development
//10/25/16
package edu.brandeis.cs.elizabethkoshelev.expenselog;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView currentList;
    Context context = MainActivity.this;
    ArrayList<ExpenseLogEntryData> list = new ArrayList<ExpenseLogEntryData>();
	SQLiteDatabase expensesDB = null;
    ExpenseTracker tracker;
    protected void onCreate(Bundle savedInstanceState) {
		try{

            // Opens a current database or creates it
            // Pass the database name, designate that only this app can use it
            // and a DatabaseErrorHandler in the case of database corruption
            expensesDB = this.openOrCreateDatabase("MyExpenses", MODE_PRIVATE, null);

            // Execute an SQL statement that isn't select
            expensesDB.execSQL("CREATE TABLE IF NOT EXISTS expenses " +
                "(desc VARCHAR, note VARCHAR, time VARCHAR);");
		} catch(Exception e){

        }
		getData();
        tracker= new ExpenseTracker(context, list);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentList= (ListView) findViewById(R.id.listedElements);
        currentList.setAdapter(tracker);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_id) {
            Intent intent1 = new Intent(this,AddExpense.class);
            final int result = 1;
            startActivityForResult(intent1, result);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String descSentBack = data.getStringExtra("getDescription");
        String noteSentBack = data.getStringExtra("getNote");
        if (!descSentBack.equals("")) {
            ExpenseLogEntryData stuff = new ExpenseLogEntryData(descSentBack, noteSentBack);
            list.add(stuff);
			expensesDB.execSQL("INSERT INTO expenses (desc, note, time) VALUES ('" +
            stuff.getDescription() + "', '" + stuff.getNotes() + "', '" +stuff.getTime() + "');");

        }
    }


    public void copyList(){
        expensesDB.execSQL("DELETE FROM expenses");
        for (ExpenseLogEntryData entry: list) {
            String desc = entry.getDescription();
            String note = entry.getNotes();
            String time = entry.getTime();

            expensesDB.execSQL("INSERT INTO expenses (desc, note, time) VALUES ('" +
                    desc + "', '" + note + "', '" + time + "');");
        }

        Cursor cursor = expensesDB.rawQuery("SELECT * FROM entries", null);
        Toast.makeText(MainActivity.this, "Database Synced", Toast.LENGTH_SHORT).show();
        currentList.setAdapter(tracker);
    }



	public void getData() {
 		ArrayList<ExpenseLogEntryData> newlist = new ArrayList<ExpenseLogEntryData>();
        // A Cursor provides read and write access to database results
        Cursor cursor = expensesDB.rawQuery("SELECT * FROM expenses", null);

        // Get the index for the column name provided
        int descColumn = cursor.getColumnIndex("desc");
        int noteColumn = cursor.getColumnIndex("note");
        int timeColumn = cursor.getColumnIndex("time");

        // Move to the first row of results
        cursor.moveToFirst();


        // Verify that we have results
        if(cursor != null && (cursor.getCount() > 0)){

            do{
                // Get the results and store them in a String
                String desc = cursor.getString(descColumn);
                String note = cursor.getString(noteColumn);
                String time = cursor.getString(timeColumn);
 				ExpenseLogEntryData stuff = new ExpenseLogEntryData(desc, note, time);
                newlist.add(stuff);

                // Keep getting results as long as they exist
            }while(cursor.moveToNext());

            list=newlist;

        } else {



        }

    }
}
