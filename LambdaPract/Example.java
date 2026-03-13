
public class Example {
    public  Operation add(){
        return new AdditionOperation();
    }
    
    public Operation mult() {
        return new Operation(){
            public double Op(int a, int b) {return a*b;}
        };
    }

    public Operation exponent() {
        return (int a, int b) -> Math.pow(a, b);
    }

    class AdditionOperation implements Operation{
        @Override
        public double Op(int a, int b) {return a + b;}
    }
}