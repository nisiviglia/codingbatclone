/* Copyright (C) 
 * 2019 - Nicholas Siviglia
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * 
 */
/**
 * @file RunPython.java
 * @brief Runs python code in the shell and returns the results.
 * @author Nicholas Siviglia
 * @version 1.0
 * @date 2019-05-20
 */

package com.siviglia.web.codingbatclone.util;

import java.io.IOException;
import java.util.Scanner;

import com.siviglia.web.codingbatclone.model.Question;

/*
 * This class is deisgned to run Python code and return
 * the results in the supplied Question class.
 *
*/
public class RunPython implements RunTask<Question>{

    //How long the subprocess should wait before stopping.
    private int timeout = 3;

    public RunPython(){}

    /**
    * @brief Runs the supplied code and test cases and returns 
    * the results.
    *
    * @param question
    *
    * @return Question
    */
    public Question run(Question question){
        
        String[] testInputArray = question.getTestInputArray();
        String[] runOutputArray = new String[testInputArray.length];

        for(int i=0; i < testInputArray.length; i++){
            try{
                
                //Put together the string of commands and run.
                String[] cmdStringArray = getCmdStringArray(
                        question.getText(), 
                        question.getFunctionName(), 
                        testInputArray[i]);
                Process proc = new ProcessBuilder(cmdStringArray).start();
                
                //get output and convert from inputstream to string and store.
                //https://stackoverflow.com/a/35446009
                Scanner scanner = new Scanner(proc.getInputStream()).useDelimiter("\\A");
                String output = scanner.hasNext() ? scanner.next() : "";
                runOutputArray[i] = output;

            }
            catch(IOException e){
                runOutputArray[i] = e.toString();
            }
        }

        //set the output in the question class and return.
        question.setRunOutputArray(runOutputArray);
        return question;
    }

    /**
     * @brief Builds the string of commands that will be passed to the shell to run.
     *
     * @param text
     * @param functionName
     * @param input
     *
     * @return String[]
     */
    private String[] getCmdStringArray(String text, String functionName, String input){
        
        //Code String
        String codeString = String.format("%s\nprint(%s(%s))", 
                text, 
                functionName, 
                input);

        //Command String
        String commandString = String.format("timeout %d python3 -c \'%s\' 2>&1", 
                timeout, 
                codeString); 

        //Combine in string array with bash 
        String[] commandStringArray = new String[] {
            "/bin/bash", 
            "-c", 
           commandString};

        return commandStringArray;
    } 

    /**
     * @brief returns the timeout variable.
     *
     * @return int
     */
    public int getTimeout(){
        return timeout;
    }

    /**
     * @brief sets the timeout variable.
     *
     * @param timeout
     *
     * @return 
     */
    public void setTimeout(int timeout){
        this.timeout = timeout;
    }
}
