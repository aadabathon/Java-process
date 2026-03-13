public class meow{
    public static int mod83(int x) {
        int base = 324;
        int mod = 362;
        int result = 1;



        while (x > 0){
            if ( x%2 ==1) {
                result = (result * base) %mod;
            }
            base = (base*base)% mod;
            x /= 2;
        }
    return result;


  }
  public static void main(String args[]){
    System.out.println(mod83(286));
}
}

