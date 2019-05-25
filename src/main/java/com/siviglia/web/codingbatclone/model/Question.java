package com.siviglia.web.codingbatclone.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Transient;

import java.util.List;
import java.util.ArrayList;

@Entity
public class Question{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true, nullable = false)
    private long id;

    @Basic(optional = false)
    private long date;

    @Basic(optional = false)
    @Column(name = "title",unique = true, nullable = false)
    private String title;

    @Basic(optional = false)
    private String discription;
    
    @Basic(optional = false)
    private String functionName;

    @Basic(optional = true)
    private String text;

    @Basic(optional = false)
    private String testInput1;

    @Basic(optional = false)
    private String testInput2;

    @Basic(optional = false)
    private String testInput3;

    @Basic(optional = false)
    private String testOutput1;

    @Basic(optional = false)
    private String testOutput2;

    @Basic(optional = false)
    private String testOutput3;

    @Transient
    private String runOutput1;

    @Transient
    private String runOutput2;

    @Transient
    private String runOutput3;

    @Basic(optional = false)
    private boolean visible = true;

    public Question() {}
    
    public Question(
            long date, 
            String title, 
            String discription, 
            String text, 
            String testInput1,
            String testInput2,
            String testInput3,
            String testOutput1,
            String testOutput2,
            String testOutput3,
            boolean visible){
        
        this.date = date;
        this.title = title;
        this.discription = discription;
        this.text = text;
        this.testInput1 = testInput1;
        this.testInput2 = testInput2;
        this.testInput3 = testInput3;
        this.testOutput1 = testOutput1;
        this.testOutput2 = testOutput2;
        this.testOutput3 = testOutput3;
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
    
    public String getFunctionName(){
        return functionName;
    }

    public void setFunctionName(String functionName){
        this.functionName = functionName;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public String getTestInput1(){
        return testInput1;
    }

    public void setTestInput1(String testInput1){
        this.testInput1 = testInput1;
    }

    public String getTestInput2(){
        return testInput2;
    }

    public void setTestInput2(String testInput2){
        this.testInput2 = testInput2;
    }

    public String getTestInput3(){
        return testInput3;
    }

    public void setTestInput3(String testInput3){
        this.testInput3 = testInput3;
    }

    public void setTestOutput1(String testOutput1){
        this.testOutput1 = testOutput1;
    }

    public String getTestOutput1(){
        return testOutput1;
    }   

    public void setTestOutput2(String testOutput2){
        this.testOutput2 = testOutput2;
    }

    public String getTestOutput2(){
        return testOutput2;
    }   

    public void setTestOutput3(String testOutput3){
        this.testOutput3 = testOutput3;
    }

    public String getTestOutput3(){
        return testOutput3;
    }   

    public String getRunOutput1(){
        return runOutput1;
    }

    public void setRunOutput1(String runOutput){
        this.runOutput1 = runOutput;
    }

    public String getRunOutput2(){
        return runOutput2;
    }

    public void setRunOutput2(String runOutput){
        this.runOutput2 = runOutput;
    }

    public String getRunOutput3(){
        return runOutput3;
    }

    public void setRunOutput3(String runOutput){
        this.runOutput3 = runOutput;
    }

    public String[] getTestInputArray(){
        return new String[]{testInput1, testInput2, testInput3};
    }

    public String[] getTestOutputArray(){
        return new String[]{testOutput1, testOutput2, testOutput3};
    }

    public String[] getRunOutputArray(){
        return new String[]{runOutput1, runOutput2, runOutput3};
    }

    public void setRunOutputArray(String[] runOutputArray){
        runOutput1 = runOutputArray[0];
        runOutput2 = runOutputArray[1];
        runOutput3 = runOutputArray[2];
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
                "Post[id=%s, date='%d', title='%s', discription='%s', functionName='%s', text='%s', testInput1=%s, testInput2=%s, testInput3=%s, testOutput1=%s, testOutput2=%s, testOutput3=%s,isible=%b]", 
                id, date, title, discription, functionName, text, testInput1, testInput2, testInput3, testOutput1, testOutput2, testOutput3, visible);

}


}
