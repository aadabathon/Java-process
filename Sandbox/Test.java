import java.util.ArrayList;

public class Test{

    interface Program{
        public void derez();
    }
    public static void main(String args[]){
        ArrayList<Object> list = new ArrayList();
        for (int i = 0; i < 3; i ++){
            int j = i*i;
            Program ares = () -> System.out.println("Hello " + j);
            list.add(ares);
        }

    }

    Program list.get(0).derez();

}