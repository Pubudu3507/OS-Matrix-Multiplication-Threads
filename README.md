##  Requirements

### Pthread (for c):
- GCC compiler (Linux/Mac) or MinGW (Windows)
- Pthreads library (usually included with GCC)

### Java threads (for Java):
- Java JDK (version 8 or above)

---

## 💻 How to Run

### 🔹 C (Pthreads) and Java (Threads)

```bash
# 👉 Compile and run the C version
gcc matrix_pthreads.c -o matrix -lpthread
./matrix

# 👉 Compile and run the Java version
javac javathreads_matrix.java
java javathreads_matrix
