import java.lang.Math;

public class Polynomial{
  double[] coefficients;

  public Polynomial(){
  }

  public Polynomial(double[] c){
    coefficients = c;
  }

  public Polynomial add(Polynomial p){
    if (this == null) return null;
    int t = p.coefficients.length;
    int k;
    double sum[];
    if(t>this.coefficients.length) 
    {
      k=t;
      t=this.coefficients.length;
      sum = p.coefficients;
    }
    else 
    {
      k = this.coefficients.length;
      sum = this.coefficients;
    }

    for(int i=0; i<t; i++){
      sum[i]=this.coefficients[i]+p.coefficients[i];
    }

    Polynomial re = new Polynomial(sum);
    return re;
  }
  
  public double evaluate(double a){
    if (this==null || this.coefficients==null) return 0.0;
    double result=0;
    for(int i=0; i<this.coefficients.length; i++)
    {
      result+=this.coefficients[i] * Math.pow(a,i);
    }
    return result;
  }

  public boolean hasRoot(double root)
  {
    if (this==null) return false;
    double sum = 0;
    for(int i=0; i<this.coefficients.length; i++)
    {
      sum += Math.pow(root, i)*this.coefficients[i];
    }
    if (sum == 0) return true;
    else return false;
  }
}
