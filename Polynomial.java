import java.lang.Math;
import java.io.PrintStream;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial{
  double[] coefficients;
	int[] exponents;

  public Polynomial(){
  }

  public Polynomial(double[] c, int[] e){
    coefficients = c;
		exponents = e;
  }

  public Polynomial(File file) throws IOException
  {
    Scanner s = new Scanner(file);
    String line = s.nextLine();
    
    int i=1, index=0, pin=0;

    String[] tmp = new String[2];
    String elements[] = line.split("[+-]+");
    double[] coef = new double[elements.length];
    int[] expo = new int[elements.length];
    boolean flag = true;
    for(int n=0; n<elements[0].length(); n++)
    {
      if(elements[0].substring(n,n+1)=="x")
      {
        flag = false;
        i=0;
        break;
      }
    }
    if(flag)
    {
      coef[0]=Double.parseDouble(elements[0]);
      if(line.substring(0,1)=="-")
      {
        coef[0]*=-1;
        pin++;
      }
      pin+=elements[0].length();
      expo[0]=0;
    }
    for(i=i; i<elements.length; i++)
    {
      tmp=elements[i].split("x");
      coef[i]=Double.parseDouble(tmp[0]);
      if(line.substring(pin,pin+1)=="-") coef[i]*=-1;
      expo[i]=Integer.parseInt(tmp[1]);
      pin+=(1+elements[i].length());
    }
    this.coefficients=coef;
    this.exponents=expo;
  }

  public Polynomial add(Polynomial p){
    if (this == null) return null;
    int pLength = p.coefficients.length;
    int thisLength = this.coefficients.length;
		int k = Math.max(pLength, thisLength);
		double[] newPoly = new double[k];
		int[] newExpo = new int[k];

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
			else if(j==pLength || this.exponents[i]<p.exponents[j])
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
		double[] rePoly = new double[index];
    int[] reExpo = new int[index];
    for(int op=0; op<index; op++)
    {
      rePoly[op]=newPoly[op];
      reExpo[op]=reExpo[op];
    }

    Polynomial re = new Polynomial(rePoly, reExpo);
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
		int maxLength = p.exponents[pLength-1]+this.exponents[thisLength-1];
		int i=0, j=0, index=0;
		int newExpo[] = new int[maxLength];
		double newPoly[] = new double[maxLength];
    int returnLength = 0;
		while(i<thisLength || j<pLength)
		{
			if(i<thisLength && j<pLength && this.exponents[i]==p.exponents[j])
			{
				newPoly[this.exponents[i]+p.exponents[j]]+=this.coefficients[i]*p.coefficients[j];
				i++;
				j++;
			}
      else if(i<thisLength && this.exponents[i]<p.exponents[j])
      {
        newPoly[this.exponents[i]]+=this.coefficients[i];
        i++;
      }
      else
      {
        newPoly[p.exponents[j]]+=p.coefficients[j];
        j++;
      }
		}
    for(int n=0; n<maxLength; n++) if(newPoly[n]!=0)
    {
      newPoly[returnLength]=newPoly[n];
      newExpo[returnLength]=n;
      returnLength++;
    }
    int[] reExpo = new int[returnLength];
    double[] rePoly = new double[returnLength];
    for(int n=0; n<returnLength; n++)
    {
      reExpo[n]=newExpo[n];
      rePoly[n]=newPoly[n];
    }
    return new Polynomial(rePoly, reExpo);
  }

  public void saveToFile(String fileName) throws IOException
  {
    String out="";
    for(int i=0; i<this.coefficients.length; i++)
    {
      out+=this.coefficients[i];
      if(this.exponents[i]!=0) out+="x";
      out+=this.exponents[i];
    }
    PrintStream ps = new PrintStream("./output.txt");
    ps.println(out);
    return;
  }
}
