import java.lang.Math;

public class Polynomial{
  int[] coefficients;

  public Polynomial(){
  }

  public Polynomial(int[] c){
    coefficients = c;
  }

  public void add(Polynomial p){
    int t = p.coefficients.length;
    if(t>this.coefficients.length) t=this.coefficients.length;

    for(int i=0; i<t; i++){
      this.coefficients[i]+=p.coefficients[i];
    }
  }
  public int evaluate(int a){
    int result=0;
    for(int i=0; i<this.coefficients.length; i++)
    {
      result+=this.coefficients[i] * Math.pow(a,i);
    }
    return result;
  }
}
