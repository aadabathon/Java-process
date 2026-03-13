public class ChuckALuck{

    public int Simulation(int epochs){
        int payout = 0;
        while (epochs > 0){
            int k = (int)(Math.random()*6) + 1;
            int die1 = (int)(Math.random() * 6) +1;
            int die2 = (int)(Math.random() * 6) +1;
            int die3 = (int)(Math.random() * 6) +1;
            if (die1 == k && die2 == k && die3 ==k){
                System.out.println("Triple Hit! 3x payout!");
                payout += 3;

            } else if (die1 == k && die2 == k && !(die3 == k) || die1 == k && !(die2 == k) && die3 == k || !(die1 == k) && die2 == k && die3 == k){
                System.out.println("Double Hit! 2x payout!");
                payout += 2;
            } else if (!(die1 == k) && !(die2 == k) && !(die3 == k)){
                System.out.println("No Hit! Lose $1!");
                payout -= 1;
            } else{
                System.out.println("Single Hit! Win $1!");
                payout += 1;
            }
            epochs--;
        }
        return payout;
    }

    public static void main(String[] args){ 
        int epochs = 300;
        ChuckALuck ChuckALuck = new ChuckALuck();
        int payout = ChuckALuck.Simulation(epochs);
        System.out.println("Total payout: " + payout);
        System.out.println("The expected return : $" + (1.0 * payout/epochs) + " per game.");
    }
}
