
import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ahmed
 */
public class StdHuffmanDecompresser {
    ArrayList<String> charsList = new ArrayList<>(); 
    HashMap <Character,String>charCode = new HashMap(); //Hold the code     
    HashMap<String , Character>charCodeInverse = new HashMap();
    HashMap <String,Integer>charCount = new HashMap (); //Hold the frequency

    public StdHuffmanDecompresser() {
        System.out.println(decompress("11001010000100000001011101010001111"));
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
    
    public String decompress(String binaryData ){ 
        //count letters
        countLetters("Ahmed Zaher");
        //construct the list of letters
        for(String key : charCount .keySet() ) {
           charsList.add(key);
        
        }
            
        
        //set code for each letter
        setCodes(charsList);
        
        //Swap key & values
        for(Character key : charCode.keySet() ){
            charCodeInverse.put(charCode.get(key), key);
        }
         
        
        //decode the binary string into letters
        StringBuilder originalText = new StringBuilder();
        for(int i = 0 ; i< binaryData.length() ; i++ ){
            int counter = 0;
            String tmp = binaryData.charAt(i)+"";
            while(  charCodeInverse.containsValue(tmp) == false) {
                counter++;
                try {
                tmp = binaryData.substring( i , i + counter );
                }
                catch(StringIndexOutOfBoundsException e){}
            }
       //     System.out.println("XXXX"+charCodeInverse.get(tmp));
            originalText.append(charCodeInverse.get(tmp));
            i+=counter;
        }
        
        
        
        return new String(originalText);
    }
}
