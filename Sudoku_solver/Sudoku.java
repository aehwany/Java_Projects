import java.util.*;
import java.io.*;
public class Sudoku {




    /* SIZE is the size parameter of the Sudoku puzzle, and N is the square of the size.  For 
     * a standard Sudoku puzzle, SIZE is 3 and N is 9. */
    int SIZE, N;

    /* The grid contains all the numbers in the Sudoku puzzle.  Numbers which have
     * not yet been revealed are stored as 0. */
    int Grid[][];
    public String find(int hor, int ver)
    {
    	 String result="";
    	
    			//System.out.println("ok");
	    	int x=hor*SIZE;
	    	int y=ver*SIZE;
	    	//System.out.println(x+""+y);
	    	int j=0;
    	
	    		for(int i=1;i<=N;i++)
	    		{
	    			j=0;
		    		//System.out.println(i);
		    		for(int r=0;r<3;r++)
		    		{
		    			for(int c=0;c<3;c++)
			    			{
		    				//System.out.println(i+" "+ Grid[r][c]);
		    				if(Grid[r+x][c+y]==i){
		    					//System.out.println(i);
		    					j=i;  				
		    					}
	    				
			    			}
		    		}
	    		//System.out.println(Grid[0][6]);
		    		if(j==0)
		    		{
		    			//return i;
	    			//System.out.println(i);
	    			result=result+i;
	    		
		    		}
	    		}
	    	if(result.equals("")){result=" ";}	
    	
    	
    	return result;
    	
    }
    public String findall()
    {
    	//System.out.println("a7a");
    	String res="";
    	for(int i=0;i<3;i++)
    	{
    		for(int j=0;j<3;j++)
    		{
    			String x=find(i,j);
    			//if(!(x.equals(""))){
    			 res=res+x+"-";
    			 //System.out.println(res);
    			//}
    			 //System.out.println(i+""+j);
    		}
    	}
    	return res;
    }

    /* The solve() method should remove all the unknown characters ('x') in the Grid
     * and replace them with the numbers from 1-9 that satisfy the Sudoku puzzle. */
    
   //***************///////******************
    
   //****************///////****************
    
    
    
    public Boolean algr(String res, int count)
    {
    
    	//System.out.println("start ");
    	String[]array=res.split("-");	//convert String res into array 
    	System.out.println(res+" "+array.length);
    	if(count==array.length){
    		//print();
    		//System.out.println("end rec");
    		return true;
    		}
    	else
    	{
    		for(int i=0;i<array[count].length();i++)
    		{
    			//System.out.println(array[count].length());
    			//System.out.println("loop" +Character.getNumericValue(array[count].charAt(i)));
    			//System.out.println(res);
    			String zero_pos=findIndex(count,0);
    			
    			while(zero_pos.equals(""))
    			{
    				//System.out.println("empty square");
    				count++;
    				zero_pos=findIndex(count,0);
    			}
    			int x=Character.getNumericValue(zero_pos.charAt(0));
    			int y=Character.getNumericValue(zero_pos.charAt(1));
    			Grid[x][y]=Character.getNumericValue(array[count].charAt(i));
    			if(isValid(zero_pos))
    			{
    				//System.out.println("valid");
    				
    				//String sub=res.substring(0, i)+res.substring(i+1, res.length());
    				
    				String []subarray=new String[array.length];
    			
    				for(int m=0;m<array.length;m++)
    				{
    					subarray[m]=array[m];
    				}
    				subarray[count]=subarray[count].substring(0, i)+subarray[count].substring(i+1, subarray[count].length());
    				String sub="";
    				for(int l=0;l<subarray.length;l++)
    				{
    					if(subarray[l].length()==0){
    						//System.out.println("empty array");
    						count++;sub=sub+" "+"-";}
    					
    					else{sub=sub+subarray[l]+"-";}
    				}
    				
    				//System.out.println("sub is "+sub);
    				//print();
    				//System.out.println("");
    				Boolean ret=algr(sub,count);
    				
    				//System.out.println("back "+ret);
    				if(ret){
    					//System.out.println("break out");
    				
    					return true;
    					}
    				//System.out.println(sub);
    				//a=sub.substring(0, i)+x+sub.substring(ind, a.length());
    				//System.out.println(sub);
    			}
    			Grid[x][y]=0;
    			//System.out.println("invalid");
    		}
    		//System.out.println("end for loop");
    	}
    	//System.out.println("end call");
    	return false;
    	
    }
    
    public void solve()
    {
    	//System.out.println(Grid[SIZE-1][SIZE-1]);
    	int count=0;
    	String x=findall();
    	algr(x, count);
    	//System.out.println(res);
 
        /* INSERT YOUR CODE HERE */
    }
    int c=0;
    public int solve(int a)
    {
    	System.out.println("start");
    	if(a==0){return 0;};
    	for(int i=0;i<9;i++){
    		c++;
    		System.out.println(c);
    	int res=solve(a-1);
    	}
    	return 0;
    	
    }
    public String findIndex(int c,int in)
    {
    	String val="";
    	int row=c/3*SIZE;
    	int col=c%3*SIZE;
    	//System.out.println("row "+row);
    	//System.out.println("col "+col);
    	for(int i=0;i<3;i++)
    	{
    		for(int j=0;j<3;j++)
    		{
    			//System.out.println("  "+Grid[row+i][col+j]);
    			if(Grid[row+i][col+j]==in){
    				//System.out.println(Grid[row+i][col+j]);
    				val+=row+i;
    				val+=col+j;
    				
    				return val;
    				}
    		}
    	}
    	//System.out.println("a7a");
    	return val;
    }
    public Boolean isValid(String s)
    {
    	//System.out.println(Grid[4][2]);
    	int x=Character.getNumericValue(s.charAt(0));
    	int y=Character.getNumericValue(s.charAt(1));
    	//System.out.println(x);
    	int val=Grid[x][y];
    	for(int i=0; i<9;i++)
    	{
    		if(Grid[x][i]==val){
    			if(i==y){;}
    			else{return false;}
    		}
    		if(Grid[i][y]==val){
    			if(i==x){;}
    			else{return false;}
    		}
    	}
    	return true;
    }


    /*****************************************************************************/
    /* NOTE: YOU SHOULD NOT HAVE TO MODIFY ANY OF THE FUNCTIONS BELOW THIS LINE. */
    /*****************************************************************************/
 
    /* Default constructor.  This will initialize all positions to the default 0
     * value.  Use the read() function to load the Sudoku puzzle from a file or
     * the standard input. */
    public Sudoku( int size )
    {
        SIZE = size;
        N = size*size;

        Grid = new int[N][N];
        for( int i = 0; i < N; i++ ) 
            for( int j = 0; j < N; j++ ) 
                Grid[i][j] = 0;
    }


    /* readInteger is a helper function for the reading of the input file.  It reads
     * words until it finds one that represents an integer. For convenience, it will also
     * recognize the string "x" as equivalent to "0". */
    static int readInteger( InputStream in ) throws Exception
    {
        int result = 0;
        boolean success = false;

        while( !success ) {
            String word = readWord( in );

            try {
                result = Integer.parseInt( word );
                success = true;
            } catch( Exception e ) {
                // Convert 'x' words into 0's
                if( word.compareTo("x") == 0 ) {
                    result = 0;
                    success = true;
                }
                // Ignore all other words that are not integers
            }
        }
        
        return result;
    }


    /* readWord is a helper function that reads a word separated by white space. */
    static String readWord( InputStream in ) throws Exception
    {
        StringBuffer result = new StringBuffer();
        int currentChar = in.read();
	String whiteSpace = " \t\r\n";
        // Ignore any leading white space
        while( whiteSpace.indexOf(currentChar) > -1 ) {
            currentChar = in.read();
        }

        // Read all characters until you reach white space
        while( whiteSpace.indexOf(currentChar) == -1 ) {
            result.append( (char) currentChar );
            currentChar = in.read();
        }
        return result.toString();
    }


    /* This function reads a Sudoku puzzle from the input stream in.  The Sudoku
     * grid is filled in one row at at time, from left to right.  All non-valid
     * characters are ignored by this function and may be used in the Sudoku file
     * to increase its legibility. */
    public void read( InputStream in ) throws Exception
    {
        for( int i = 0; i < N; i++ ) {
            for( int j = 0; j < N; j++ ) {
                Grid[i][j] = readInteger( in );
            }
        }
    }


    /* Helper function for the printing of Sudoku puzzle.  This function will print
     * out text, preceded by enough ' ' characters to make sure that the printint out
     * takes at least width characters.  */
    void printFixedWidth( String text, int width )
    {
        for( int i = 0; i < width - text.length(); i++ )
            System.out.print( " " );
        System.out.print( text );
    }


    /* The print() function outputs the Sudoku grid to the standard output, using
     * a bit of extra formatting to make the result clearly readable. */
    public void print()
    {
        // Compute the number of digits necessary to print out each number in the Sudoku puzzle
        int digits = (int) Math.floor(Math.log(N) / Math.log(10)) + 1;

        // Create a dashed line to separate the boxes 
        int lineLength = (digits + 1) * N + 2 * SIZE - 3;
        StringBuffer line = new StringBuffer();
        for( int lineInit = 0; lineInit < lineLength; lineInit++ )
            line.append('-');

        // Go through the Grid, printing out its values separated by spaces
        for( int i = 0; i < N; i++ ) {
            for( int j = 0; j < N; j++ ) {
                printFixedWidth( String.valueOf( Grid[i][j] ), digits );
                // Print the vertical lines between boxes 
                if( (j < N-1) && ((j+1) % SIZE == 0) )
                    System.out.print( " |" );
                System.out.print( " " );
            }
            System.out.println();

            // Print the horizontal line between boxes
            if( (i < N-1) && ((i+1) % SIZE == 0) )
                System.out.println( line.toString() );
        }
    }


    /* The main function reads in a Sudoku puzzle from the standard input, 
     * unless a file name is provided as a run-time argument, in which case the
     * Sudoku puzzle is loaded from that file.  It then solves the puzzle, and
     * outputs the completed puzzle to the standard output. */
    public static void main( String args[] ) throws Exception
    {
        InputStream in;
        if( args.length > 0 ) 
            in = new FileInputStream( args[0] );
        else
            in = System.in;

        // The first number in all Sudoku files must represent the size of the puzzle.  See
        // the example files for the file format.
        int puzzleSize = readInteger( in );
        if( puzzleSize > 100 || puzzleSize < 1 ) {
            System.out.println("Error: The Sudoku puzzle size must be between 1 and 100.");
            System.exit(-1);
        }
        //System.out.println(puzzleSize);

        Sudoku s = new Sudoku( puzzleSize );

        // read the rest of the Sudoku puzzle
        s.read( in );
        s.print();
        System.out.println("");
        System.out.println("The solution is ");
        System.out.println("");
        // Solve the puzzle.  We don't currently check to verify that the puzzle can be
        // successfully completed.  You may add that check if you want to, but it is not
        // necessary.
        s.solve();
    	
        // Print out the (hopefully completed!) puzzle
        s.print();
    }
}
