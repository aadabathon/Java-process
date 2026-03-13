public class BinomialEvaluator {
    public int binomial(int n, int r) {
        if (n < 0 || r < 0) {
            return -1;
        }
        if (r == 1) {
            return n;
        } else if (n == r || r == 0) {
            return 1;
        } else if (n < r) {
            return 0;
        } else {
            return binomial(n - 1, r - 1) + binomial(n - 1, r);
        }
    
            
        }
        public int fac(int x){
            if (x < 0){
                return -1;
            } else if (x == 0 || x == 1) {
                return 1;
            } else { 
                return x * fac(x-1);

            }
            }
    

    public static void main(String[] args) {
        BinomialEvaluator eval = new BinomialEvaluator();
        System.out.println(eval.binomial(12, 5) * eval.binomial(16, 4)); 
        System.out.println(eval.fac(11));
    }
}
