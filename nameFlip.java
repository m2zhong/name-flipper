import java.io.*;
import javax.swing.*;
import java.util.Scanner;

/****************************************************************************

                                                        Michael Zhong
                                                        January 21, 2017
                        Name Flip Formatter

File Name:      nameFlip.java
Description:    This program reads the name in a user selected text file.
                It then formats each line into (lastname, firstname) order
                , while skipping the names that are already in
                (lastname, firstname) order.
****************************************************************************/
public class nameFlip{
  public static void main(String args[]){
    JFileChooser fc = new JFileChooser();
    File input, output;
    String fileType="txt";
    String buffStr = "";
    String lastName ="";
    String[] strBuffArr;
    
    /* User chooses txt file to process */
    fc.showOpenDialog(null);
    input = fc.getSelectedFile();
    
    /* Setting limitation to only txt files */
    if(!((getFileExtension(input)).equals(fileType))){
      System.out.println("The selected file's extension is " + getFileExtension(input));
      System.err.println("Only .txt format is supported for now");
      return;
    }
    
    /* Begin Scanning */
    try{
      Scanner sc = new Scanner(input);
      
      // Set up output file and writer
      output = new File("output.txt");
      PrintWriter pw = new PrintWriter(output);
      
      // Report status
      System.out.println("Reading...");
      
      // Checking to see if done reading file
      while(sc.hasNextLine()){
        
        // Extract next line of name from txt file
        buffStr = sc.nextLine();
        
        // Only format names that are not in "lastname, first" format
        if(!buffStr.contains(",")){
          
          //Reporting status
          System.out.println("Formatting name: " + buffStr);
          
          // Breaking name to array index for easier selection
          strBuffArr = buffStr.split(" ");
          
          // Storing last name
          lastName = strBuffArr[strBuffArr.length-1];
          
          // Writing Last name to file. Added ', ' for format
          pw.print(lastName + ", ");
          
          // Handling first and middle of names with more than 3 words
          if(strBuffArr.length > 2){
            for(int i = 0; i < strBuffArr.length-2; i++)
              pw.print(strBuffArr[i] + ' ');
            
            // Print end-middle name without space. Skip line when done
            pw.println(strBuffArr[strBuffArr.length-2]);
          }
          
          // Directly print first name for name with only 2 words
          else
            pw.println(strBuffArr[0]);
        }
      }
      
      // Close print writer and report status
      pw.close();
      System.out.println("done");
      
      // Report directory output file is stored
      System.out.println("Files stored at " + output.getAbsolutePath());
      
    }catch(Exception e){
      // Print error if caught
      System.out.println(e);
    }
  }
  
  /* Function Name: getFileExtension
   * Parameters: File file - a File type object with valid extension
   * Description: Read a file's name and return its extension in String format.
   */
  private static String getFileExtension(File file) {
    String fileName = file.getName();
    
    if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
      return fileName.substring(fileName.lastIndexOf(".")+1);
    
    else return "";
  }
}
