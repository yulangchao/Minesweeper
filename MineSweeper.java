import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class MineSweeper
{

	
	public static void main(String[] args) 
	{
		/*Initial Values*/
		int rowNum ;
		int colNum ;
		int rowb;
		int colb;

	    String[][] real = new String[8][8];
		String[][] user = new String[8][8];
		
		for (int i=0;i<8;i++){
			for(int j=0;j<8;j++){
			    real[i][j]=".";	
				user[i][j]=".";
			}			
		}
		
		System.out.println("  | 0 1 2 3 4 5 6 7");
		System.out.println("___________________");
		for(int k=0;k<8;k++){
			System.out.print(""+k+" |");			
			for(int j=0;j<8;j++){
				System.out.print(" "+real[k][j]);
			}
			System.out.println("");
		}
		
		initializeFullGrid(real);
		
		Scanner sc = new Scanner(System.in);
        int count=11;
		
		while(count>10){		
			System.out.println("");
			System.out.print("Select a cell. Row value (a digit between 0 and 7):");
			rowNum = sc.nextInt();
			System.out.print("Select a cell. Column value (a digit between 0 and 7):");
			colNum = sc.nextInt();

			while(rowNum >= 8 || colNum >= 8 || rowNum<0 || colNum<0)
			{
				System.out.println("You must enter a right number in the Grid.");
				System.out.print("Select a cell. Row value (a digit between 0 and 7):");
				rowNum = sc.nextInt();
				System.out.print("Select a cell. Column value (a digit between 0 and 7):");
				colNum = sc.nextInt();
			}
			
			revealGridCell(rowNum,colNum,real,user);
			count=0;
			for (int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					if(user[i][j].equals(".")){
						count++;
					}
				}			
			}	
		}

	}
    
	public static void initializeFullGrid(String[][] real){
		
		int x,y=0;
		Random ra =new Random();
		for(int i=0;i<10;){
            x=ra.nextInt(8);
			y=ra.nextInt(8);
			if(real[x][y].equals(".")){
				real[x][y]="B";
				i++;
			}
			
		}
		
		for(int k=0;k<8;k++){
			for(int j=0;j<8;j++){
			    if(real[k][j].equals(".")){
					int number=checkforneighbor(real,k,j);
					if(number==0){
						real[k][j]=" ";
					}else{
			            real[k][j]=""+number;
					}
				}		
			}
		}
		
	}
	//reaveal map
	public static void revealGridCell(int x, int y, String[][] real, String[][] user){
		if(real[x][y].equals("B")){
			System.out.println("Kaboom! Game Over!");
			//draw bomb after losing
			for (int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					if(real[i][j].equals("B")){
						user[i][j]=real[i][j];
					}
				}			
			}	
            user[x][y]="X";
			drawFullGrid(user);
			System.exit(-1);
		}else{
			if(real[x][y].equals(" ")){
				user[x][y]=real[x][y];
			displayneighbor(user,real,x,y);
			}else{
			user[x][y]=real[x][y];
			}
			drawFullGrid(user);
		}
	}
	
	
	//draw map
	public static void drawFullGrid( String[][] user){
		int count=0;
		for (int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(user[i][j].equals(".")){
					count++;
				}
			}			
		}		
		if(count==10){
		System.out.println("Congrats! You Won!");			
		}
		System.out.println("  | 0 1 2 3 4 5 6 7");
		System.out.println("___________________");
		for(int k=0;k<8;k++){
			System.out.print(""+k+" |");
			for(int j=0;j<8;j++){
				if(count==10 && user[k][j].equals(".")){
					user[k][j]="B";
				}
				System.out.print(" "+user[k][j]);
			}
			System.out.println("");
		}
	}
	
	public static void displayneighbor(String[][] user,String[][] real,int k,int j){
		//up
		if(k==0 && j==0){	   
		   user[k+1][j]=real[k+1][j];
		   user[k+1][j+1]=real[k+1][j+1];
		   user[k][j+1]=real[k][j+1];		   
		}
		else if(k==0 && j>0 && j<7){
		   user[k+1][j]=real[k+1][j];
		   user[k+1][j+1]=real[k+1][j+1];
		   user[k+1][j-1]=real[k+1][j-1];
		   user[k][j-1]=real[k][j-1];   
		   user[k][j+1]=real[k][j+1];  		   
		}else if(k==0 && j==7){
		   user[k+1][j]=real[k+1][j];
		   user[k+1][j-1]=real[k+1][j-1];
		   user[k][j-1]=real[k][j-1];
	    //left
		}else if(k>0 && j==0 && k<7){
		   user[k+1][j]=real[k+1][j];
		   user[k-1][j]=real[k-1][j];
		   user[k+1][j+1]=real[k+1][j+1];
		   user[k-1][j+1]=real[k-1][j+1];   
		   user[k][j+1]=real[k][j+1];
		//right
		}else if(k>0 && j==7 && k<7){
		   user[k+1][j]=real[k+1][j];
		   user[k-1][j]=real[k-1][j];
		   user[k+1][j-1]=real[k+1][j-1];
		   user[k-1][j-1]=real[k-1][j-1];   
		   user[k][j-1]=real[k][j-1];		
		//bot
		}else if(k==7 && j==0){
		   user[k-1][j]=real[k-1][j];
		   user[k-1][j+1]=real[k-1][j+1];
		   user[k][j+1]=real[k][j+1];	
		}else if(k==7 && j>0 && j<7){
		   user[k-1][j]=real[k-1][j];
		   user[k-1][j+1]=real[k-1][j+1];
		   user[k-1][j-1]=real[k-1][j-1];
		   user[k][j-1]=real[k][j-1];   
		   user[k][j+1]=real[k][j+1]; 
			
		}else if(k==7 && j==7){
		   user[k-1][j]=real[k-1][j];
		   user[k-1][j-1]=real[k-1][j-1];
		   user[k][j-1]=real[k][j-1];		   
		}else{
		   user[k+1][j]=real[k+1][j];
		   user[k+1][j+1]=real[k+1][j+1];
		   user[k+1][j-1]=real[k+1][j-1];
		   user[k][j-1]=real[k][j-1];   
		   user[k][j+1]=real[k][j+1];	
		   user[k-1][j]=real[k-1][j];
		   user[k-1][j+1]=real[k-1][j+1];
		   user[k-1][j-1]=real[k-1][j-1];		   
		}
		
	}	
	
	//initial elements which is not B.
	public static int checkforneighbor(String[][] real,int k,int j){
		int number=0;
		//up
		if(k==0 && j==0){
			
		   if(real[k+1][j].equals("B")){
			   number++;   
		   }
		   if(real[k][j+1].equals("B")){
			   number++;
		   }
		   
		   if(real[k+1][j+1].equals("B")){
			   number++;
		   }			   
		
		}
		else if(k==0 && j>0 && j<7){
			
		   if(real[k][j-1].equals("B")){
			   number++;   
		   }
		   if(real[k][j+1].equals("B")){
			   number++;
		   }
		   
		   if(real[k+1][j+1].equals("B")){
			   number++;
		   }			   
		   if(real[k+1][j].equals("B")){
			   number++;   
		   }
		   if(real[k+1][j-1].equals("B")){
			   number++;
		   }
			
		}else if(k==0 && j==7){
			
		   if(real[k+1][j].equals("B")){
			   number++;   
		   }
		   if(real[k][j-1].equals("B")){
			   number++;
		   }
		   
		   if(real[k+1][j-1].equals("B")){
			   number++;
		   }			   
					
	    //left
		}else if(k>0 && j==0 && k<7){
		   if(real[k-1][j].equals("B")){
			   number++;   
		   }
		   if(real[k+1][j].equals("B")){
			   number++;
		   }
		   
		   if(real[k][j+1].equals("B")){
			   number++;
		   }			   
		   if(real[k+1][j+1].equals("B")){
			   number++;   
		   }
		   if(real[k-1][j+1].equals("B")){
			   number++;
		   }			
			
		//right
		}else if(k>0 && j==7 && k<7){
		   if(real[k+1][j].equals("B")){
			   number++;   
		   }
		   if(real[k-1][j].equals("B")){
			   number++;
		   }
		   
		   if(real[k-1][j-1].equals("B")){
			   number++;
		   }			   
		   if(real[k+1][j-1].equals("B")){
			   number++;   
		   }
		   if(real[k][j-1].equals("B")){
			   number++;
		   }			
		//bot
		}else if(k==7 && j==0){
			
		   if(real[k-1][j].equals("B")){
			   number++;   
		   }
		   if(real[k][j+1].equals("B")){
			   number++;
		   }
		   
		   if(real[k-1][j+1].equals("B")){
			   number++;
		   }			   
		
		}else if(k==7 && j>0 && j<7){
			
		   if(real[k][j-1].equals("B")){
			   number++;   
		   }
		   if(real[k][j+1].equals("B")){
			   number++;
		   }
		   
		   if(real[k-1][j+1].equals("B")){
			   number++;
		   }			   
		   if(real[k-1][j].equals("B")){
			   number++;   
		   }
		   if(real[k-1][j-1].equals("B")){
			   number++;
		   }
			
		}else if(k==7 && j==7){
			
		   if(real[k-1][j-1].equals("B")){
			   number++;   
		   }
		   if(real[k][j-1].equals("B")){
			   number++;
		   }
		   
		   if(real[k-1][j].equals("B")){
			   number++;
		   }			   
		}else{
		   if(real[k+1][j-1].equals("B")){
			   number++;   
		   }
		   if(real[k+1][j+1].equals("B")){
			   number++;
		   }
		   
		   if(real[k+1][j].equals("B")){
			   number++;
		   }		   
		   if(real[k-1][j-1].equals("B")){
			   number++;   
		   }
		   if(real[k-1][j+1].equals("B")){
			   number++;
		   }
		   
		   if(real[k-1][j].equals("B")){
			   number++;
		   }		
		   if(real[k][j-1].equals("B")){
			   number++;   
		   }
		   if(real[k][j+1].equals("B")){
			   number++;
		   }
			   
		}		
		return number;
	
	}

}