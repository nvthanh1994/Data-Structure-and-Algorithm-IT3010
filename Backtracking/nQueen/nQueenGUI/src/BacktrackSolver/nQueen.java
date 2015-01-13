package BacktrackSolver;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Prime on 13/01/2015.
 */
public class nQueen {
    int n;
    int x[];
    int numOfSolution;
    ArrayList<Integer> result;

    nQueen(int n) {
        this.n = n;
        x = new int[n + 1];
        numOfSolution = 0;
        result=new ArrayList<Integer>();
        System.out.println("SOL        POSITION");
        System.out.printf("#    ");
        for(int i=1;i<=n;i++){
            System.out.printf("%-2d  ",i);
        }
        System.out.println();
        System.out.println("     ----------------------------              ");
    }

    public boolean check(int curRow, int val) {
        for (int prevRow = 1; prevRow < curRow; prevRow++) {
            if ((x[prevRow] == val) || (Math.abs(curRow - prevRow) == Math.abs(val - x[prevRow]))) return false;
        }
        return true;
    }

    public void findSolution(int curRow, int limitSolution) {
        if(numOfSolution>=limitSolution) return;
        for(int val=1;val<=n;val++){
            if(check(curRow,val)){
                x[curRow]=val;
                if(curRow==n){
                    ++numOfSolution;
                    result.removeAll(result);
                    for (int i = 1; i <= n; i++) {
                        result.add(x[i]-1);
                    }
                    System.out.println(result.size());
                    printSolution();
                }
                else findSolution(curRow + 1, limitSolution);
            }
        }
    }

    public ArrayList<Integer> getSolution(){
        return result;
    }

    public void printSolution() {
        System.out.printf("%-4d",numOfSolution);
        for (int i = 1; i <= n; i++) {
            System.out.printf(" %-2d ",x[i]);
        }
        System.out.println();
    }
    public int getNumOfSolution() {
        return  numOfSolution;
    }

    public int getN (){
        return n;
    }

    public void setN (int n){
        this.n=n;
    }

    public static void main(String[] args) {
        int temp=8;
        //Scanner in = new Scanner(System.in);
        //temp = in.nextInt();
        nQueen myQueen = new nQueen(temp);
        long timeStart=System.nanoTime();
        myQueen.findSolution(1,1);          // Find One Solution
        long timeEnd=System.nanoTime();

        System.out.println("Number of solutions : " + myQueen.getNumOfSolution());
        System.out.println("Time taken          : " + (double) (timeEnd-timeStart)/1000000000+" s");
    }
}