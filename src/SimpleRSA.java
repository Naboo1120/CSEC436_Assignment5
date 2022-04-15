import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class SimpleRSA{
    public static void main(String[] args) {

        try{

            Random rand  = new SecureRandom();
            BigInteger p = BigInteger.probablePrime(128, rand);
            BigInteger q = BigInteger.probablePrime(128, rand);
            BigInteger n = p.multiply(q);
            BigInteger phi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
            BigInteger e = new BigInteger("65537");


            while (e.compareTo(BigInteger.ONE)<= 0 || e.compareTo(phi_n) >=0 )
                if(e.gcd(phi_n).equals(BigInteger.ONE)) {
                    break;
                }

            BigInteger d = e.modInverse(phi_n);
            BufferedReader in = new BufferedReader((new InputStreamReader(System.in)));
            System.out.println("Enter the message to encrypt");
            String mstring = in.readLine();

            System.out.println("Original d = "+ d.toString());
            BigInteger d1 = find_d(e,n);
            System.out.println("d1 Found= "+d1.toString());

            BigInteger m = convertStringToBigInteger(mstring);
            long t1 = System.nanoTime();
            BigInteger c = m.modPow(d, n);
            long t2 = System.nanoTime();
            System.out.println("Time Elapsed for d: "+ d.toString());
            System.out.println(t2-t1);

            d1 = new BigInteger("1100011100000011");
            t1 = System.nanoTime();
            m.modPow(d1, n);
            t2 = System.nanoTime();
            System.out.println("Time Elapsed for d1: "+ d1.toString());
            System.out.println(t2-t1);

            String cstring = convertBigIntegerToString(c);
            System.out.println(cstring);

            BigInteger g = c.modPow(e, n);
            String mlstring = convertBigIntegerToString(g);
            System.out.println(mlstring);


        } catch (Exception e) {
            System.out.println("One or more of the assigned numbers were unable to compute, ending program");
        }


    }

    public static BigInteger find_d(BigInteger e, BigInteger n){
        boolean factorFound = false;
        BigInteger p = n;
        Random rand = new SecureRandom();
        while (factorFound == false){
            p = BigInteger.probablePrime(128, rand);
            if (n.mod(p).equals(BigInteger.ZERO));
                break;
        }
        BigInteger q = n.divide(p);
        BigInteger phi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger d = e.modInverse(phi_n);
        return d;
    }

    public static BigInteger convertStringToBigInteger(String s)
    {
        BigInteger b = new BigInteger("0");
        for (int i = 0; i < s.length(); i++)
        {
            Integer code = (int) s.charAt(i);
            BigInteger c = new BigInteger(code.toString());
            b = b.shiftLeft(8);
            b = b.or(c);
        }
        return b;
    }

    public static String  convertBigIntegerToString(BigInteger b)
    {
        String s = new String();
        while (b.compareTo(BigInteger.ZERO) == 1)
        {
            BigInteger c = new BigInteger("11111111", 2);
            int cb = (b.and(c)).intValue();
            Character cv= (char) cb;
            s = (cv.toString()).concat(s);
            b = b.shiftRight(8);
        }
        return s;
    }
}