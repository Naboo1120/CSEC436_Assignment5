import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
public class SimpleRSA
{
   public static void main (String[] args)
   {
      try {
   BigInteger e= new BigInteger("65537");
   BigInteger d= new BigInteger("89489425009274444368228545921773093919669586065884257445497854456487674839629818390934941973262879616797970608917283679875499331574161113854088813275488110588247193077582527278437906504015680623423550067240042466665654232383502922215493623289472138866445818789127946123407807725702626644091036502372545139713");
   BigInteger n= new BigInteger("145906768007583323230186939349070635292401872375357164399581871019873438799005358938369571402670149802121818086292467422828157022922076746906543401224889672472407926969987100581290103199317858753663710862357656510507883714297115637342788911463535102712032765166518411726859837988672111837205085526346618740053");
   
   //Read a string from the keyboard
   BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
   System.out.println("Enter the message to encrypt");
   String mstring = in.readLine();
   
   BigInteger m = convertStringToBigInteger(mstring);
   BigInteger c = m.modPow(e, n); //Encryption
   String cstring = convertBigIntegerToString(c);
   System.out.println(cstring); //print the encrypted text


   }
   catch (Exception e) {
         System.out.println("One or more of the assigned numbers were unable to compute, ending the program");
   }

  }
  

//This is a method to convert a String to BigInteger by
//packing each character into a BigInteger
//Input: String
//Output: BigInteger
public static BigInteger convertStringToBigInteger(String s)
{
      BigInteger b = new BigInteger("0");
      for (int i = 0; i < s.length(); i++)
         {
         Integer code = new Integer((int)s.charAt(i));
         BigInteger c = new BigInteger(code.toString());
         b = b.shiftLeft(8);
         b = b.or(c);
         }
  return b;
}


//This is a method to convert a BigInteger to String
//by converting each byte into a character and forming 
//a string of characters.
//Input: BigInteger
//Output: String

public static String  convertBigIntegerToString(BigInteger b)
  { 
  String s = new String();
  while (b.compareTo(BigInteger.ZERO) == 1)
         {
         BigInteger c = new BigInteger("11111111", 2);
         int cb = (b.and(c)).intValue();
         Character cv= new Character((char)cb);
         s = (cv.toString()).concat(s);
         b = b.shiftRight(8);
         }
    return s;
  }

}