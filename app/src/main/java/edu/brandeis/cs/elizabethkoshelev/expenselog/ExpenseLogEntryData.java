package edu.brandeis.cs.elizabethkoshelev.expenselog;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by Elizabeth on 10/24/2016.
 */
public class ExpenseLogEntryData {

        private String description;
        private String notes;
        private String time;


        ExpenseLogEntryData(String newDesc, String newNote){
            description = newDesc;
            notes = newNote;
            time = setTime();
        }
		
		ExpenseLogEntryData(String newDesc, String newNote, String newTime){
            description = newDesc;
            notes = newNote;
            time = newTime;
        }

        private void setDescription(String setdes){
            description=setdes;
        }

        private void setNotes(String setnote){
            notes=setnote;
        }

        private String setTime(){
            Date now = new Date();
            time=now.toString();
            return time;
        }

        protected String getDescription(){
            return description;
        }

        protected String getNotes(){
            return notes;
        }

        protected String getTime(){
            return time;
        }

    }

