##  Requirements

### Pthread (for c):
- GCC compiler (Linux/Mac) or MinGW (Windows)
- Pthreads library (usually included with GCC)

### Java threads (for Java):
- Java JDK (version 8 or above)


##  How to Run

###  Pthread and Java Threads

```bash
#  Compile and run the Pthread
gcc pthread_matrix.c -o matrix -pthread
./matrix

#  Compile and run the Java threads
javac javathreads_matrix.java
java javathreads_matrix
