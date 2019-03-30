package com.siviglia.web.codingbatclone.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Basic;
import javax.persistence.Column;

import java.util.List;
import java.util.ArrayList;

@Entity
public class Question{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private long id;

    @Basic(optional = false)
    private long date;

    @Basic(optional = false)
    @Column(name = "title",unique=true, nullable = false)
    private String title;

    @Basic(optional = false)
    private String discription;

    @Basic(optional = false)
    private String text;

    @Basic(optional = false)
    private ArrayList<String> testCases;

    @Basic(optional = false)
    private boolean visible = true;

    public Question() {}
    
    public Question(long date, String title, String discription, 
            String text, ArrayList<String> testCases, boolean visible){
        
        this.date = date;
        this.title = title;
        this.discription = discription;
        this.text = text;
        this.testCases = testCases;
        this.visible = visible;
    }
    
    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void setDate(long date){
       this.date = date; 
    }

    public long getDate(){
        return date;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setDiscription(String discription){
        this.discription = discription;
    }

    public String getDiscription(){
        return discription;
    }
    
    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public void setTestCases(ArrayList<String> testCases){
        this.testCases = testCases; 
    }

    public ArrayList<String> getTestCases(){
        return testCases;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public boolean getVisible(){
        return visible;
    }

    @Override
    public String toString(){

        return String.format(
                "Post[id=%s, date='%d', title='%s', discription='%s', text='%s', testCases=%s, visible=%b]", 
                id, date, title, discription, text, testCases, visible);

}


}
