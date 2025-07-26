#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define MAX 10  

int A[MAX][MAX];
int B[MAX][MAX];
int C[MAX][MAX];
int n;

typedef struct {
    int row;
    int col;
} ThreadData;

// Thread function 
void* matrix_multiply(void* arg) {
    ThreadData* data = (ThreadData*)arg;
    int r = data->row;
    int c = data->col;
    C[r][c] = 0;

    for (int i = 0; i < n; i++) {
        C[r][c] += A[r][i] * B[i][c];
    }

    pthread_exit(NULL);
}

int main() {
    printf("Enter matrix size (max %d): ", MAX);
    scanf("%d", &n);

    if (n > MAX || n <= 0) {
        printf("Error: n must be between 1 and %d.\n", MAX);
        return 1;
    }

// Input Matrix A 
    printf("\nEnter elements for Matrix A (%d x %d):\n", n, n);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            printf("Enter element A[%d][%d]: ", i+1, j+1);
            scanf("%d", &A[i][j]);
        }
    }

// Input Matrix B 
    printf("\nEnter elements for Matrix B (%d x %d):\n", n, n);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            printf("Enter element B[%d][%d]: ", i+1, j+1);
            scanf("%d", &B[i][j]);
        }
    }

    pthread_t threads[MAX][MAX];
    ThreadData thread_data[MAX][MAX];

    // Create threads for each element of the matrix C
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            thread_data[i][j].row = i;
            thread_data[i][j].col = j;
            pthread_create(&threads[i][j], NULL, matrix_multiply, &thread_data[i][j]);
        }
    }

    // Join threads
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            pthread_join(threads[i][j], NULL);

    // Print Result Matrix
    printf("\nResult Matrix C :\n");
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++)
            printf("%d ", C[i][j]);
        printf("\n");
    }

    return 0;
}