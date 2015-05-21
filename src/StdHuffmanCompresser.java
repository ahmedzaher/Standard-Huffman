/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 *
 * @author ahmed
 */
public class StdHuffmanCompresser {
    ArrayList<String> charsList = new ArrayList<>(); 
    HashMap <Character,String>charCode = new HashMap(); //Hold the code     
    HashMap <String,Integer>charCount = new HashMap (); //Hold the frequency
    
    public StdHuffmanCompresser() {
  
       
          System.out.print(compress("Ahmed Zaher") );
          //11001010000100000001011101010001111
        
    }
    
    public void countLetters(String text ) {
       for(int i = 0 ; i < text.length() ; i++ ) {
            if( charCount.containsKey(text.charAt(i)+"") )
                charCount.put(text.charAt(i)+"", (Integer)charCount.get(text.charAt(i)+"")+(Integer)1) ; //increase
            else
                charCount.put(text.charAt(i)+"" , 1);  
                
        } 
    }
    public void sortCharsList(){
        for(int i=0 ; i< charsList.size() ;i++ )
            for(int j=i+1 ; j<charsList.size() ;j++ )
                if( charCount.get(charsList.get(j) ) < charCount.get(charsList.get(i) )) {
                    String tmp = charsList.get(j);
                    charsList.set(j, charsList.get(i));
                    charsList.set(i,tmp);
                }
    }
    public void appendToCode(String str , char ele) {
        for(int i = 0 ; i < str.length() ; i++ ){
            if( charCode.containsKey(str.charAt(i)))
                charCode.put ( str.charAt(i) , charCode.get(str.charAt(i) )+ele);
            else
                charCode.put ( str.charAt(i) , ele+"");
        }
    }
    
    public void setCodes(ArrayList<String>charsList) { 
        //Sort the list in ascending order
        sortCharsList();
        
        //Base case
        if( charsList.size() == 2 ) {
            appendToCode(charsList.get(0), '1');
            appendToCode(charsList.get(1), '0');
            return;
        }
        String tmp1 = charsList.get(0);
        String tmp2 = charsList.get(1);
        String tmp3 = tmp1 + tmp2 ;
        charCount.put(tmp3, charCount.get(tmp1) + charCount.get(tmp2));
        
        charsList.remove(0);
        charsList.remove(0);
        
        charsList.add(tmp3);
        setCodes(charsList); 
        
        appendToCode(tmp1 , '1');
        appendToCode(tmp2 , '0');
        
        
        
    } 
    public String compress(String originalText ){
        //count letters
        countLetters(originalText);
        
        //construct the list of letters
        for(String key : charCount .keySet() )
            charsList.add(key);
            
        
        //set code for each letter
        setCodes(charsList);
        
        //encode each letter with its code
        StringBuilder compressedData = new StringBuilder();
        for(int i = 0 ; i<originalText.length() ; i++ )
            compressedData.append (   charCode.get(originalText.charAt(i))   );
        return new String(compressedData);
    }
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
         
        // TODO code application logic here 
        new StdHuffmanDecompresser();
   
        //Fill frequency Map
        
        //Fill Chars List 
       
        //Sort Chars List 
          
        
        
          
        
         
         
         
        
    }
    
}
