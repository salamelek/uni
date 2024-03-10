import java.util.*;

public class DN03_63230192{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        long a = sc.nextInt();
        long b = sc.nextInt();
        int potencaMaxPloscice = sc.nextInt();
        long maxPloscica = (long) Math.pow(2, potencaMaxPloscice);

        long stPloscic = a * b;

        long trenutnaPloscica = 2;

        while(trenutnaPloscica <= maxPloscica){
            stPloscic -= (a / trenutnaPloscica) * (b / trenutnaPloscica) * 3;
            trenutnaPloscica *= 2;
        }
        System.out.println(stPloscic);
    }
}
