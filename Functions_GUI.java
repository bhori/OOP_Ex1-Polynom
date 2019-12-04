package myMath.matala1;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.awt.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Functions_GUI implements functions  {
	private ArrayList<function> functions;
	
	public Functions_GUI() {
	   functions= new ArrayList<function>();
	}

	@Override 
	public boolean add(function arg0) {
		return functions.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		return functions.addAll(arg0);
	}

	@Override
	public void clear() {
		functions.clear();
		
	}

	@Override
	public boolean contains(Object arg0) {
		return functions.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return functions.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return functions.isEmpty() ;
	}

	@Override
	public Iterator<function> iterator() {
		return functions.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return functions.remove(arg0) ;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return functions.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return  functions.retainAll(arg0);
	}

	@Override
	public int size() {
		return  functions.size();
	}

	@Override
	public Object[] toArray() {
		return  functions.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return functions.toArray(arg0);
	}

	@Override
	public void initFromFile(String file) throws IOException {
		try 
	        {
	          BufferedReader br = new BufferedReader(new FileReader(file));
	          while(br.ready()) {
	        	String function = "";
	        	
	            while ((function = br.readLine()) != null) 
	            {
	                functions.add(functions.get(0).initFromString(function));
	            }
	          }

	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	            System.out.println("could not read file");
	        }
		
	}

	@Override
	public void saveToFile(String file) throws IOException {

		try 
		{
			PrintWriter pw = new PrintWriter(new File(file));
			


			pw.write(this.toString());
			pw.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
		
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<functions.size(); i++) {
		    sb.append(sb+ "/n"+ functions.get(i));
		}
		return  sb.toString();
	}

}


/**
 * This class demonstrate a simple set of sin funxtions and draw them in a GUI window.
 * A trivial example of inner class is also presented.
 */
class Sinus_Main {
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, 
			Color.red, Color.GREEN, Color.PINK};
	public static void main(String[] args) {
		ArrayList<function> ff = new ArrayList<function>();
		ff.addAll(init());
		drawFunctions(ff);
	}

	public static ArrayList<function> init() {
		ArrayList<function> ans = new ArrayList<function>();
		Sinus s0 = new Sinus();
		Monom m1 = new Monom("4x");
		Monom m2 = new Monom("0.3x^3");
		Sinus s1 = new Sinus(m1);
		Sinus s2 = new Sinus(m2);
		ans.add(s0); ans.add(s1); ans.add(s2);
		return ans;
	}
	public static void drawFunctions(ArrayList<function> ff) {drawFunctions(ff, GUI_Params.DEFAULT_GUI_PARAMS);}
	public static void drawFunctions(ArrayList<function> ff, GUI_Params gp) {
		drawFunctions(ff, gp.get_width(),gp.get_height(),gp.get_rx(), gp.get_ry(),gp.get_resolution());
	}
	public static void drawFunctions(ArrayList<function> ff, int width, int height, Range rx, Range ry, int res) {
		int n = res;
		StdDraw.setCanvasSize(width, height);
		int size = ff.size();
		double[] x = new double[n+1];
		double[][] yy = new double[size][n+1];
		double x_step = (rx.get_max()-rx.get_min())/n;
		double x0 = rx.get_min();
		for (int i=0; i<=n; i++) {
			x[i] = x0;
			for(int a=0;a<size;a++) {
				yy[a][i] = ff.get(a).f(x[i]);
			}
			x0+=x_step;
		}
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		
		
		// plot the approximation to the function
		for(int a=0;a<size;a++) {
			int c = a%Colors.length;
			StdDraw.setPenColor(Colors[c]);
		
			System.out.println(a+") "+Colors[a]+"  f(x)= "+ff.get(a));
			for (int i = 0; i < n; i++) {
				StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
			}
		}	
	}

	/**
	 * Simple example for inner class.
	 */
	static class GUI_Params {
		public static final int W=800, H=600, RES=100;
		public static final Range RX=new Range(0,10);
		public static final Range RY=new Range(-1.2,1.5);
		public static GUI_Params  DEFAULT_GUI_PARAMS = new GUI_Params(W,H,RX,RY,RES);
		private int _width;
		private int _height;
		private Range _rx;
		private Range _ry;
		private int _resolution;
		public GUI_Params(int w, int h, Range rx, Range ry, int res) {
			this._width = w;
			this._height = h;
			this._rx = rx;
			this._ry = ry;
			this._resolution = res;
		}
		public int get_width() {return this._width;}
		public int get_height() {return this._height;}
		public int get_resolution() {return this._resolution;}
		public Range get_rx() {return this._rx;}
		public Range get_ry() {return this._ry;}
	}
}
