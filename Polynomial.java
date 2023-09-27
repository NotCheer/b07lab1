import java.lang.Math;

public class Polynomial{
  double[] coefficients;
	int[] exponents;

  public Polynomial(){
  }

  public Polynomial(double[] c, int[] e){
    coefficients = c;
		exponents = e;
  }

  public Polynomial add(Polynomial p){
    if (this == null) return null;
    int pLength = p.coefficients.length;
    int thisLength = this.coefficients.length;
		k = max(pLength, thisLength);
		double newPoly[k];
		int newExpo[k];

		int i=0, j=0, index=0;
    while(i<thisLength || j<pLength){
    	if(i<thisLength && j<pLength && this.exponents[i]==p.exponents[j])
			{
				newExpo[index]=this.exponents[i];
				newPoly[index]=this.coefficients[i]+p.coefficients[j];
				index++;
				i++;
				j++;
			}
			else if(j=pLength || this.exponents[i]<p.exponents[j])
			{
				newExpo[index]=this.exponents[i];
				newPoly[index]=this.coefficients[i];
				index++;
				i++;
			}
			else
			{
				newExpo[index]=p.exponents[j];
				newPoly[index]=p.coefficients[j];
				index++;
				j++;
			}
		}
		

    Polynomial re = new Polynomial(newPoly[index], newExpo[index]);
    return re;
  }
  
  public double evaluate(double a){
    if (this==null || this.coefficients==null) return 0.0;
    double result=0;
    for(int i=0; i<this.coefficients.length; i++)
    {
      result+=this.coefficients[i] * Math.pow(a,this.exponents[i]);
    }
    return result;
  }

  public boolean hasRoot(double root)
  {
    if (this==null) return false;
    if (this.evaluate(root)==0) return true;
    else return false;
  }

	public Polynomial multiply(Polynomial p)
	{
		int pLength = p.exponents.length;
		int thisLength = this.exponents.length;
		int maxLength = p.exponents[pLength-1]*this.exponents[thisLength-1];
		int i=0, j=0, index=0;
		int newExpo[] = new int[maxLength];
		double newPoly[] = new double[maxLength];
		while(i<thisLength || j<pLength)
		{
			if(this.exponents[i]=p.exponents[j])
			{
				newPoly[this.exponents[i]*p.exponents[j]]=this.coefficients[i]*p.coefficients[j];
				i++;
				j++;
			}	
		}	
}
