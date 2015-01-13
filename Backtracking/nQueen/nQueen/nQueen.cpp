#include <iostream>
#include <cstdio>
#include <cmath>
#include <ctime>
using namespace std;

#define MAX 10

int n;
int numOfSolution;
int x[MAX];


bool check(int row, int tempCol){
    for(int prevRow=1;prevRow<row;prevRow++){
        if(x[prevRow]==tempCol || (abs(tempCol-x[prevRow])==abs(row-prevRow))) return false;
    }
    return true;
}

void printSolution(){
    printf("%-4d    %d",numOfSolution,x[1]);
    for(int i=2;i<=n;i++){
        printf(" %d",x[i]);
    }
    printf("\n");
}

void Backtrack(int i){
    if(numOfSolution>=1) return;
    for(int tempCol=1;tempCol<=n;tempCol++){
        if(check(i,tempCol)){
            x[i]=tempCol;
            if(i==n){
                ++numOfSolution;
                printSolution();
                return;
            }
            else Backtrack(i+1);
        }
    }
}

void BacktrackOne(int i){
    if(numOfSolution>=1) return;
    for(int tempCol=1;tempCol<=n;tempCol++){
        if(check(i,tempCol)){
            x[i]=tempCol;
            if(i==n){
                ++numOfSolution;
                printSolution();
                return;
            }
            else BacktrackOne(i+1);
        }
    }
}




int main(){
    //freopen("res.txt","r",stdout);
    cin >> n;

    numOfSolution=0;

    printf("SOL         Position\n");
    printf("#       ");
    for(int i=1;i<=n;i++){
        printf("%d ",i);
        if(i==n) printf("\n");
    }
    printf("-----------------------\n");

    clock_t timeStart=clock();
    //Backtrack(1);
    BacktrackOne(1);
    printf("\nTotal solution found : %d\nTime taken :           %.4f s\n",numOfSolution, (double)(clock()-timeStart)/CLOCKS_PER_SEC);



    return 0;
}

/**
    Người dùng nhập vào n, chương trình tính toán và in ra số lời giải + tất cả lời giải

    - n là số quân hậu
    - x_i là chỉ số cột của con hậu ở hàng i. x_i \in {1->8}
*/
