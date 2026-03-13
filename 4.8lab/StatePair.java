public class StatePair <Type1 extends Comparable<Type1>, Type2 extends Comparable<Type2>> {
   private Type1 value1;
   private Type2 value2;
   
    public Type1 getKey() {
        return this.value1;
    }
    public void setKey(Type1 key) {
        this.value1 = key;
    }
    public Type2 getValue() {
        return this.value2;
    }
    public void setValue(Type2 value) {
        this.value2 = value;
    }
   public StatePair(Type1 value1, Type2 value2){
    this.value1 = value1;
    this.value2 = value2;
   }
   public void printInfo(){
    System.out.println(this.value1 + ": " + this.value2);
   }
}