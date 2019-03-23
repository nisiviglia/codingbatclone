package com.siviglia.web.codingbatclone.model;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.ArrayList;

public class Question{
    
    @Id
    public String id;

    public long date;
    public String title;
    public String discription;
    public String text;
    public ArrayList<String> testCases;
    public boolean visible;

    public Question() {}
    
    public Question(long date, String title, String discription, 
            String text, boolean visible){
        
        this.date = date;
        this.title = title;
        this.discription = discription;
        this.text = text;
        this.testCases = testCases;
        this.visible = visible;
    }

    @Override
    public String toString(){

        return String.format(
                "Post[id=%s, date='%d', title='%s', discription='%s', text='%s', testCases=%s, visible=%b]", 
                id, date, title, discription, text, testCases, visible);

}


}
