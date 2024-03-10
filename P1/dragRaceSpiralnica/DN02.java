import java.util.Scanner;

public class DN02 {
  public static int d;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);      
    int b = sc.nextInt(); // tipkovnica type
    d = sc.nextInt(); // size of tipkovnica
    if (d <= 1) {System.out.print(0); return;}
    
    Tipkovnica t = null;
    switch (b) {
      case 1: t = DN02::ravnovrstnica; break;
      case 2: t = DN02::kvadratnica; break;
      case 3: t = DN02::piramidnica; break;
      case 4: t = DN02::spiralnica; break;
    }

    int n = sc.nextInt(); // num of moves
    int[] premiki = new int[n];

    for (int i = 0; i < n; i++) {
      premiki[i] = sc.nextInt();
    }

    int pot = 0;
    for (int i = 1; i < n; i++) {
      pot += t.premik(premiki[i-1], premiki[i]);
    }
    System.out.print(pot);
  }

 @FunctionalInterface
  public interface Tipkovnica {
    public int premik(int from, int to);
  }

  public static int ravnovrstnica(int from, int to) {
    return abs(from-to);
  }

  public static int kvadratnica(int from, int to) {
    int from_x = from % d; 
    int from_y = from / d;
    int to_x = to % d; 
    int to_y = to/ d;
    return abs(from_x - to_x) + abs(from_y-to_y);
  }

  public static int piramidnica(int from, int to) {
    int from_y = (int) Math.ceil(Math.sqrt(from+1));
    int to_y = (int) Math.ceil(Math.sqrt(to+1));

    int from_x = d-from_y + from - ((from_y-1)*(from_y-1)); 
    int to_x = d-to_y + to - ((to_y-1)*(to_y-1)); 
    return abs(from_x - to_x) + abs(from_y-to_y);
  }

  public static int spiralnica(int from, int to) {
    int[] f = spiralnica_coord(from);
    int[] t = spiralnica_coord(to);
    return abs(f[0] - t[0]) + abs(f[1] - t[1]);
  }

  public static int[] spiralnica_coord(int key) {
    if (key == 0) {
      return new int[] {d/2, d/2};
    }
    int ring_num = (int) Math.ceil(Math.floor(Math.sqrt(key))/2); 
    // int ring_width = ring_num * 2 + 1; 
    int prev_ring_width = (ring_num-1)*2 + 1;
    int ring_index = key - ((prev_ring_width)*(prev_ring_width));
    int ring_len = ((ring_num)*8);
    int krak_len = ring_len/4;
    int krak = ring_index / krak_len;
    int x = 0;
    int y = 0;
    switch (krak) {
      case 0:
        y = d/2 - ring_num;
        x = ring_index + d/2-ring_num;
        break;
      case 1:
        x = d/2 + ring_num; 
        y = ring_index - krak_len + d/2-ring_num;
        break;
      case 2:
        y = d/2 + ring_num;
        x = -ring_index + (3*krak_len) +  d/2-ring_num;
        break;
      case 3:
        y = -ring_index + 4*krak_len + d/2-ring_num;
        x = d/2 - ring_num;
        break;
    }
    return new int[] {x, y}; 
  } 

  public static int abs(int x){
    return x > 0 ? x : -x;
  }
}
