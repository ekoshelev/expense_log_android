package edu.brandeis.cs.elizabethkoshelev.expenselog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Elizabeth on 10/24/2016.
 */
public class ExpenseTracker extends BaseAdapter {
    ArrayList<ExpenseLogEntryData> list = new ArrayList<ExpenseLogEntryData>();
    LayoutInflater inflater;
    Context context;
    TextView description, notes,time;
    Button deleteButton;

    //add buttons to save/cancel
    public ExpenseTracker(Context newContext, ArrayList<ExpenseLogEntryData> newList){
        super();
        list=newList;
        context=newContext;
        inflater = LayoutInflater.from(this.context);
    }
    //add method to print
    public int getCount() {
        return list.size();
    }

    public Object getItem(int index){
        return list.get(index);
    }

    public long getItemId(int index){
        return index;
    }

    public View getView(final int index, View view, ViewGroup parent){

           view = inflater.inflate(R.layout.expense_entry, null);


        description = (TextView)view.findViewById(R.id.description);
        description.setText(list.get(index).getDescription());

        notes = (TextView) view.findViewById(R.id.notes);
        time.setText(list.get(index).getTime());

        time = (TextView) view.findViewById(R.id.time);
        time.setText(list.get(index).getTime());
		
		deleteButton = (Button) view.findViewById(R.id.delete);
		
		deleteButton.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
				 list.remove(index);
                 ((MainActivity) context).copyList();
             }
         });
        return view; //open expense entry, inflate xml
    }



}
