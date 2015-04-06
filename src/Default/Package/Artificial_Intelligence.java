/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Default.Package;

import java.awt.Point;
import javax.swing.JOptionPane;

/**
 *
 * @author Sourav
 */
public class Artificial_Intelligence {
    
    Point p = new Point();
    int SZ;
    
    Point getOptimalMove(int[][] Board, int n, int PlayerNum) 
    {
        SZ = n;
        int bestMove, val;
        bestMove = -(1<<30);
        p = new Point();
        int diff = Main.difficulty;
        
        if(diff==2)
            diff=3;
        
        //Player 1 = Max Player
        //Player 2 = Min Player
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(Board[i][j]==0)
                {
                    Board[i][j]=PlayerNum;
                    val = MiniMax(Board, PlayerNum, new Point(i,j), diff);
                    
                    System.out.println("Utility = "+val+"N:" + n );
                    if (bestMove < val) 
                    {
                        bestMove = val;
                        p.x=i;p.y=j;
                    }
                    Board[i][j]=0;                        
                }                
            }
        }
        
        System.out.println("Computer's Best Move: "+p.x+", "+p.y + ", Depth:"+ diff +" Val:"+ bestMove);
        return p;
    }
    
     int MiniMax(int[][] Board, int PlayerNum, Point p, int depth)
    {
       // System.out.println("Player Number = "+PlayerNum+", LastMove = "+p.x+"-"+p.y+", Depth = "+depth+", Utility = "+Get_Utility_Score(Board, PlayerNum, p) );
        //PrintBoard(Board);
        if(depth==0) return Get_Utility_Score(Board, PlayerNum, p);
        if(Dead_Five(Board, p.x, p.y, PlayerNum)>=1) return Get_Utility_Score(Board, PlayerNum, p);
        int bestValue, val, utl;
        
        utl = Get_Utility_Score(Board, PlayerNum, p);
        if (PlayerNum == 1) 
        {
            bestValue = -(1<<30);
            for (int i = 0; i < SZ; i++) 
            {
                for (int j = 0; j < SZ; j++) 
                {
                    if (Board[i][j] == 0) 
                    {
                        Board[i][j] = 2;
                        val = MiniMax(Board, 2, new Point(i, j), depth-1);
                        if (bestValue < val) 
                           bestValue = val;
                        Board[i][j] = 0;
                    }
                }
                //System.out.println("");
            }
        }
        else
        {
            bestValue = (1<<30);
            for (int i = 0; i < SZ; i++) 
            {
                for (int j = 0; j < SZ; j++) 
                {
                    if (Board[i][j] == 0) 
                    {
                        Board[i][j] = 1;
                        val = MiniMax(Board, 1, new Point(i, j), depth-1);
                        if (bestValue > val) 
                           bestValue = val;
                        Board[i][j] = 0;
                    }
                }
                //System.out.println("");
            }            
        }
        return utl+bestValue;
    }
    
    int Dead_Five(int[][] Board, int x, int y, int PlayerNum)
    {
        /* 5 ta purno chal hoe gese */
        Find_Edge_Cell_Location S = new Find_Edge_Cell_Location(Board, x, y, SZ, PlayerNum);
        int i,j, kount;
        
        x=S.p1.x;
        y=S.p1.y;
        kount=0;
        for(i=x,j=y;j<SZ;j++) if(Board[i][j]==PlayerNum) kount++; else break;
        if(kount>=5) return 1;
        
        x=S.p2.x;
        y=S.p2.y;
        kount=0;
        for(i=x,j=y; j<SZ&&i<SZ; j++,i++) if(Board[i][j]==PlayerNum) kount++; else break;
        if(kount>=5) return 1;
        
        x=S.p3.x;
        y=S.p3.y;
        kount=0;
        for(i=x,j=y;i<SZ;i++) if(Board[i][j]==PlayerNum) kount++; else break;
        if(kount>=5) return 1;
        
        x=S.p4.x;
        y=S.p4.y;
        kount=0;
        for(i=x,j=y;j>=0&&i<SZ;j--,i++) if(Board[i][j]==PlayerNum) kount++; else break;
        if(kount>=5) return 1;
        
        return 0;
    }
    
    
    int Both_Side_Free(int[][] Board, int x, int y, int PlayerNum, int num)
    {
        /*
         live adjacent means : du pase faka [num sonkhok chal dea ache age theke]
         */
        Find_Edge_Cell_Location S = new Find_Edge_Cell_Location(Board, x, y, SZ, PlayerNum);
        int i,j, kount, total;
        total = 0;
        
        x=S.p1.x;
        y=S.p1.y;
        kount=0;
        for(i=x,j=y;j<SZ;j++) if(Board[i][j]==PlayerNum) kount++; else break;
        if(kount==num && isValid(i,j) && isValid(x,y-1) && Board[i][j]==0 && Board[x][y-1]==0) total++;
            
        
        
        x=S.p2.x;
        y=S.p2.y;
        kount=0;
        for(i=x,j=y; j<SZ&&i<SZ; j++,i++) if(Board[i][j]==PlayerNum) kount++; else break;
        if(kount==num&&isValid(i,j)&&isValid(x-1,y-1)&&Board[i][j]==0&&Board[x-1][y-1]==0) total++;
        
        x=S.p3.x;
        y=S.p3.y;
        kount=0;
        for(i=x,j=y;i<SZ;i++) if(Board[i][j]==PlayerNum) kount++; else break;
        if(kount==num&&isValid(i,j)&&isValid(x-1,y)&&Board[i][j]==0&&Board[x-1][y]==0) total++;
        
        x=S.p4.x;
        y=S.p4.y;
        kount=0;
        for(i=x,j=y;j>=0&&i<SZ;j--,i++) if(Board[i][j]==PlayerNum) kount++; else break;
        if(kount==num&&isValid(i,j)&&isValid(x-1,y+1)&&Board[i][j]==0&&Board[x-1][y+1]==0) total++;
        
        return total;
    }
    
    int Single_Side_Free(int[][] Board, int x, int y, int PlayerNum, int num)
    {
        /* jekoon ekpase faka ache means chal dear position ache only in one side
         and num songkhok chal dea ache
         */
        
        Find_Edge_Cell_Location S = new Find_Edge_Cell_Location(Board, x, y, SZ, PlayerNum);
        int i,j, kount, total;
        total = 0;
        
        x=S.p1.x;
        y=S.p1.y;
        kount=0;
        for(i=x,j=y;j<SZ;j++) if(Board[i][j]==PlayerNum) kount++; else break;
        if( (kount==num&&isValid(i,j)&&Board[i][j]==0) || (kount==num&&isValid(x,y-1)&&Board[x][y-1]==0) ) total++;
            
        
        
        x=S.p2.x;
        y=S.p2.y;
        kount=0;
        for(i=x,j=y; j<SZ&&i<SZ; j++,i++) if(Board[i][j]==PlayerNum) kount++; else break;
        if( (kount==num&&isValid(i,j)&&Board[i][j]==0)||(kount==num&&isValid(x-1,y-1)&&Board[x-1][y-1]==0) ) total++;
        
        x=S.p3.x;
        y=S.p3.y;
        kount=0;
        for(i=x,j=y;i<SZ;i++) if(Board[i][j]==PlayerNum) kount++; else break;
        if( (kount==num&&isValid(i,j)&&Board[i][j]==0) || (kount==num&&isValid(x-1,y)&&Board[x-1][y]==0) ) total++;
        
        x=S.p4.x;
        y=S.p4.y;
        kount=0;
        for(i=x,j=y;j>=0&&i<SZ;j--,i++) if(Board[i][j]==PlayerNum) kount++; else break;
        if((kount==num&&isValid(i,j)&&Board[i][j]==0) || (kount==num&&isValid(x-1,y+1)&&Board[x-1][y+1]==0) ) total++;
        
        return total;
    }
    
    int Single_In_Middle_Free(int[][] Board, int x, int y, int PlayerNum, int num)
    {
        
        /* num-1 songkhok same player er chal dea ache and jekono place e 
         * 1 ta valid chal debar place exist kore*/
        
        Find_Edge_Cell_Location S = new Find_Edge_Cell_Location(Board, x, y, SZ, PlayerNum);
        int i, j, total;
        int kount[] = new int[3];
        total = 0;
        x=S.p1.x; y=S.p1.y;
        kount[0]=0;kount[1]=0;kount[2]=0;
        for(i=x,j=y;j<y+num;j++) if(isValid(i, j)) kount[Board[i][j]]++;
        if(kount[PlayerNum]==num-1&&kount[0]==1&&Board[x][y]==PlayerNum&&isValid(x, y+4)&&Board[x][y+4]==PlayerNum) total++;
        
        x=S.p2.x; y=S.p2.y;
        kount[0]=0;kount[1]=0;kount[2]=0;
        for(i=x,j=y;j<y+num&&i<x+num;j++,i++) if(isValid(i, j)) kount[Board[i][j]]++;
        if(kount[PlayerNum]==num-1&&kount[0]==1&&Board[x][y]==PlayerNum&&isValid(x+4, y+4)&&Board[x+4][y+4]==PlayerNum) total++;
        
        x=S.p3.x; y=S.p3.y;
        kount[0]=0;kount[1]=0;kount[2]=0;
        for(i=x,j=y;i<x+num;i++) if(isValid(i, j)) kount[Board[i][j]]++;
        if(kount[PlayerNum]==num-1&&kount[0]==1&&Board[x][y]==PlayerNum&&isValid(x+4, y)&&Board[x+4][y]==PlayerNum) total++;
        
        x=S.p4.x; y=S.p4.y;
        kount[0]=0;kount[1]=0;kount[2]=0;
        for(i=x,j=y;j>y-num&&i<x+num;i++,j--) if(isValid(i, j)) kount[Board[i][j]]++;
        if(kount[PlayerNum]==num-1&&kount[0]==1&&Board[x][y]==PlayerNum&&isValid(x+4, y-4)&&Board[x+4][y-4]==PlayerNum) total++;
        
        return total;
    }
    
    private boolean isValid(int x, int y) 
    {
        if(x>=0&&y>=0&&x<SZ&&y<SZ) return true;
        return false;
    }
    
    
    int Get_Utility_Score(int[][] Board, int PlayerNum, Point p)
    {
        int X = p.x;
        int Y = p.y;
        int utility;
        if(PlayerNum==1) utility = -1;
        else utility = 1;
        
        /* For Complete Five In a row */
        if(Dead_Five(Board, X, Y, PlayerNum)>=1) return 500000*utility;

        
        /*For Live 4 in a row, Two Sleep 4 in a row, One Live 3 in a row + One Sleep 4 in a row, Two Tricky Adjacent*/
        if(Both_Side_Free(Board, X, Y, PlayerNum, 4)>=1) return 100000*utility;
        if(Single_Side_Free(Board, X, Y, PlayerNum, 4)>=2) return 100000*utility;
        if(Single_In_Middle_Free(Board, X, Y, PlayerNum, 5)>=2) return 100000*utility;
        if(Single_In_Middle_Free(Board, X, Y, PlayerNum, 5)>=1&&Single_Side_Free(Board, X, Y, PlayerNum, 4)>=1) return 100000*utility;
        if(Single_In_Middle_Free(Board, X, Y, PlayerNum, 5)>=1&&Both_Side_Free(Board, X, Y, PlayerNum, 3)>=2) return 100000*utility;
        if(Both_Side_Free(Board, X, Y, PlayerNum, 3)>=1 && Single_Side_Free(Board, X, Y, PlayerNum, 4)>=1) return 100000*utility;

        
        /* For Two Live 3 in a row */
        if(Both_Side_Free(Board, X, Y, PlayerNum, 3)>=2) return 40000*utility;
        
        /* For one sleep 3 in a row + one live 3 in a row */
        if(Single_Side_Free(Board, X, Y, PlayerNum, 3)>=1 && Both_Side_Free(Board, X, Y, PlayerNum, 3)>=1) return 10000*utility;
        
        
         /*One Sleep 4 in a Row Or One Tricky Adjecent*/
        if(Single_Side_Free(Board, X, Y, PlayerNum, 4)>=1) return 4000*utility;
        if(Single_In_Middle_Free(Board, X, Y, PlayerNum, 5)>=1) return 3999*utility;

        
        /* For One Live 3 in a Row*/
        if(Both_Side_Free(Board, X, Y, PlayerNum, 3)>=1) return 1000*utility;

       
        
        /* For Two Live 2 in a row*/
        if(Both_Side_Free(Board, X, Y, PlayerNum, 2)>=2) return 400*utility;

         /* For One Sleep 3 in a row*/
        if(Single_Side_Free(Board, X, Y, PlayerNum, 3)>=1) return 100*utility;

        /*For Two Sleep 2 in row*/
        if(Single_Side_Free(Board, X, Y, PlayerNum, 2)>=2) return 40*utility;

        /*For one Live 2 in a row*/
        if(Both_Side_Free(Board, X, Y, PlayerNum, 2)>=1) return 10*utility;

        /*For One sleep 2 in a row*/
        if(Single_Side_Free(Board, X, Y, PlayerNum, 2)>=1) return 4*utility;

        if(X==SZ/2&&Y==SZ/2) return 1;
        return 0;
    }
    
    
}


class Find_Edge_Cell_Location
{
    Point p1, p2, p3, p4;
    /*
     * x for Horizontal[row] and y for Vertical[column]
     * P1 = Left Most Search location 
     * p2 = Left-Top Most Search Location 
     * p3 = Top Most Search Location 
     * p4 = Right-Top Most Search Location
     */

    public Find_Edge_Cell_Location(int[][] Board, int x, int y, int n, int playerNum) 
    {
        int i, j;
        for(i=x,j=y; j>=0;j--) if(Board[i][j]!=playerNum)  break;
        p1 = new Point(i, j+1);
        
        for(i=x,j=y; i>=0&&j>=0; i--,j--) if(Board[i][j]!=playerNum) break;
        p2 = new Point(i+1, j+1);
        
        for(i=x,j=y;i>=0;i--) if(Board[i][j]!=playerNum) break;
        p3 = new Point(i+1, j);
        
        for(i=x,j=y; i>=0&&j<n; i--,j++) if(Board[i][j]!=playerNum) break;
        p4 = new Point(i+1,j-1);   
    }
}