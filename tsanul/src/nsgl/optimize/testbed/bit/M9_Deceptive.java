package nsgl.optimize.testbed.bit;

import nsgl.optimize.*;
import nsgl.bit.array.Array;

/**
 * <p>Title: M9_Deceptive</p>
 * <p>Description: Extended deceptive binary functions</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class M9_Deceptive extends OptimizationFunction<Array> {

  protected int length = 8;
  /**
   * Distance
   * @param genes Bitarray source
   * @param b Boolean array
   * @param start Position
   * @return The distance
   */
  public int distance(Array genes, boolean[] b, int start) {
    int d = 0;
    int j = 0;
    try{
    	for (int i = length * start; i < length * (start + 1); i++) {
    		if (genes.get(i) ^ b[j]) { d++; }
    		j++;
    	}
    }catch(Exception e){}	
    return d;
  }
  /**
   * Distance0
   * @param genes Bitarray source
   * @param start Position
   * @return Distance0
   */
  public int distance0(Array genes, int start){
    boolean[] b = new boolean[]{false,false,false,false,false,false,false,false};
    return distance(genes,b,start);
  }
  /**
   * Distance1
   * @param genes Bitarray source
   * @param start Position
   * @return Distance1
   */
  public int distance1(Array genes, int start) {
    boolean[] b = new boolean[]{true,false,false,false,true,true,false,false};
    return distance(genes,b,start);
  }
  /**
   * Distance2
   * @param genes Bitarray source
   * @param start Position
   * @return Distance2
   */
  public int distance2(  Array genes, int start ){
    boolean[] b = new boolean[]{false,true,false,false,true,false,true,false};
    return distance(genes,b,start);
  }

  /**
   * Return the integer value codified by the bits in a section of the array
   * @param genes Bitarray source
   * @param start Index of the first bit in the section to extract the index
   * @return The integer value codified by the bits in a section of the array
   */
  public int getValue(Array genes, int start) {
    int d0 = distance0( genes, start );
    int d1 = distance1( genes, start );
    int d2 = distance2( genes, start );
    int d = Math.min(d0,d1);
    d = Math.min(d,d2);
    if( d == 0 ){ d = 10; };
    return d;
  }

  /**
   * Evaluate the max ones OptimizationFunction function over the binary array given
   * @param x Binary Array to be evaluated
   * @return the OptimizationFunction function over the binary array
   */
  public Double compute( Array x ){
    double f=0.0;
    int n = x.size() / length;
    for( int i=0; i<n; i++ ){
      f += getValue(x, i);
    }
    return f;
  }
}
