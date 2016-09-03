import java.util.*;
 
public class Stras
{
    public float[][] multiply(float[][] A, float[][] B)
    {        
        int n = A.length;
        float[][] C = new float[n][n];
        // base case
        if (n == 1)
            C[0][0] = A[0][0] * B[0][0];
        else
        {
            float[][] A11 = new float[n/2][n/2];
            float[][] A12 = new float[n/2][n/2];
            float[][] A21 = new float[n/2][n/2];
            float[][] A22 = new float[n/2][n/2];
            float[][] B11 = new float[n/2][n/2];
            float[][] B12 = new float[n/2][n/2];
            float[][] B21 = new float[n/2][n/2];
            float[][] B22 = new float[n/2][n/2];
 
            /** Dividing matrix A into 4 halves **/
            divide(A, A11, 0 , 0);
            divide(A, A12, 0 , n/2);
            divide(A, A21, n/2, 0);
            divide(A, A22, n/2, n/2);
            /** Dividing matrix B into 4 halves **/
            divide(B, B11, 0 , 0);
            divide(B, B12, 0 , n/2);
            divide(B, B21, n/2, 0);
            divide(B, B22, n/2, n/2);

 
            float [][] M1 = multiply(add(A11, A22), add(B11, B22));
            float [][] M2 = multiply(add(A21, A22), B11);
            float [][] M3 = multiply(A11, subtract(B12, B22));
            float [][] M4 = multiply(A22, subtract(B21, B11));
            float [][] M5 = multiply(add(A11, A12), B22);
            float [][] M6 = multiply(subtract(A21, A11), add(B11, B12));
            float [][] M7 = multiply(subtract(A12, A22), add(B21, B22));
 
            float [][] C11 = add(subtract(add(M1, M4), M5), M7);
            float [][] C12 = add(M3, M5);
            float [][] C21 = add(M2, M4);
            float [][] C22 = add(subtract(add(M1, M3), M2), M6);
 
            join(C11, C, 0 , 0);
            join(C12, C, 0 , n/2);
            join(C21, C, n/2, 0);
            join(C22, C, n/2, n/2);
        }   
        return C;
    }
    
    public float[][] subtract(float[][] A, float[][] B)
    {
        int n = A.length;
        float[][] P = new float[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                P[i][j] = A[i][j] - B[i][j];
        return P;
    }
    
    public float[][] add(float[][] A, float[][] B)
    {
        int n = A.length;
        float[][] Q = new float[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                Q[i][j] = A[i][j] + B[i][j];
        return Q;
    }
 
    public void divide(float[][] P, float[][] C, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }
   
    public void join(float[][] C, float[][] P, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }    
    /** Main function **/
    public static void main (String[] args) 
    {
        Scanner a = new Scanner(System.in);
		Random rand = new Random();
        System.out.println("Strassen Multiplication Algorithm Test\n");
        Stras s = new Stras();
 
        System.out.println("Enter order n :");
        int N = a.nextInt();
        /** Accept two 2d matrices **/
        System.out.println("Enter N order matrix 1\n");
        float[][] A = new float[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = rand.nextFloat();
 
        System.out.println("Enter N order matrix 2\n");
        float[][] B = new float[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                B[i][j] = rand.nextFloat();				
				
        double startTime = System.nanoTime();
        float[][] C = s.multiply(A,B);
		double stopTime=System.nanoTime();
		double elapsedTime=stopTime-startTime;
 
        System.out.println("\nProduct of matrices A and  B : ");
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
			{
                System.out.print(C[i][j] +" ");
				}
            System.out.println();
        }
       System.out.println(elapsedTime/1000000.0 +" ms");
    }
}