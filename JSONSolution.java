/*
    MongoDB Coding Challenge
    Desciption: Program that reads a JSON object from an input file and
                outputs a flattened version of the JSON object onto an output file
    Author: Sinead Manuel
    Date: 20/04/2021
*/

import java.io.*;
import java.util.*;

public class JSONSolution
{    
    public static void main(String[] args) throws FileNotFoundException {
        // Checking for JSON file
        try {
            if (args.length == 1 && null != args[0] && args[0].endsWith(".json")) {
                String fileName = args[0];
                File input = new File(fileName);
                Scanner JSONInput = new Scanner(input);
                String data, fieldSubStr, embeddedSubStr;

                // Removes .JSON file extention from input JSON file
                fileName = fileName.substring(0, fileName.lastIndexOf("."));

                // Creates an output file by using name of input file to write flattened JSON objects
                File output = new File(fileName + "-Flattened.json");
                System.out.println("File created: " + output.getName());
                FileWriter JSONOutput = new FileWriter(output);

                // Looks at each line in JSON object
                while (JSONInput.hasNextLine()) {
                    data = JSONInput.nextLine();
                    
                    if(data.contains(": {")) {
                        int numOfQuotes = 0; // Keeps track of double quotes
                        int startPos = 0; // Start postion of an embedded document
                        int endPos = 0; // End position of the field of an embedded document
                        int i;

                        // Searching for the end position of the field of the embedded document
                        for(i = 0; i < data.length(); i++) {
                            if(data.charAt(i) == '\"' && numOfQuotes != 1){
                                numOfQuotes = 1;
                            } else if(data.charAt(i) == '\"' && numOfQuotes == 1){
                                endPos = i;
                                break;
                            }
                        }

                        // Start of the flattened document
                        fieldSubStr = data.substring(0, endPos);
                        JSONOutput.write(fieldSubStr + ".");
                        data = JSONInput.nextLine();
                        
                        // Working with the embedded document
                        while(!data.contains("}")) {
                            // Searching for the start postion of the embedded document
                            for(i = 0; i < data.length(); i++) {
                                if(data.charAt(i) == '\"') {
                                    startPos = i;
                                    break;
                                }
                            }
                                                
                            // Remainder of the flattened document
                            embeddedSubStr = data.substring(startPos + 1, data.length());
                            JSONOutput.write(embeddedSubStr);
                            data = JSONInput.nextLine();

                            if(!data.contains("}")) {
                                JSONOutput.write("\n" + fieldSubStr + ".");
                            }
                        } // end while

                        data = JSONInput.nextLine();

                        // Checks for more documents
                        if(!data.contains("}")) {
                            JSONOutput.write(",\n");
                        } else {
                            JSONOutput.write("\n" + data);
                        } 
                    } else {
                        JSONOutput.write(data + "\n");
                    } // end if - else
                } // end while
                JSONInput.close();
                JSONOutput.close();
            } else {
                System.out.println("Please enter a file containing a JSON object.");
            } // end if - else
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        } // end try - catch
    } // end main 
} // end class