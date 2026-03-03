import java.util.Random;

public class ED_Eugenia {

    public static void main(String[] args) {
        // --- SECCIÓN 1: FIBONACCI Y FACTORIAL (Resultados y Tiempos) ---
        int[] valoresN = {5, 10, 15, 20, 25, 30};
        System.out.println("=== RESULTADOS Y TIEMPOS: FIBONACCI Y FACTORIAL ===");
        System.out.printf("%-3s | %-12s | %-12s | %-15s | %-15s\n", "n", "Método", "Resultado", "Tiempo (ms)", "Cita Big-O");
        System.out.println("--------------------------------------------------------------------------------");

        for (int n : valoresN) {
            // Fibonacci Iterativo
            long inicio = System.nanoTime();
            long resFibIter = fibIterativo(n);
            double tFibIter = (System.nanoTime() - inicio) / 1_000_000.0;
            System.out.printf("%-3d | Fib. Iter  | %-12d | %-15.6f | O(n)\n", n, resFibIter, tFibIter);

            // Fibonacci Recursivo
            inicio = System.nanoTime();
            long resFibRec = fibRecursivo(n);
            double tFibRec = (System.nanoTime() - inicio) / 1_000_000.0;
            System.out.printf("%-3d | Fib. Rec   | %-12d | %-15.6f | O(2^n)\n", n, resFibRec, tFibRec);

            // Factorial Iterativo (Usamos double por el tamaño del número)
            inicio = System.nanoTime();
            double resFactIter = factIterativo(n);
            double tFactIter = (System.nanoTime() - inicio) / 1_000_000.0;
            System.out.printf("%-3d | Fact. Iter | %-12.0f | %-15.6f | O(n)\n", n, resFactIter, tFactIter);

            // Factorial Recursivo
            inicio = System.nanoTime();
            double resFactRec = factRecursivo(n);
            double tFactRec = (System.nanoTime() - inicio) / 1_000_000.0;
            System.out.printf("%-3d | Fact. Rec  | %-12.0f | %-15.6f | O(n)\n", n, resFactRec, tFactRec);
            System.out.println("--------------------------------------------------------------------------------");
        }

        // --- SECCIÓN 2: BÚSQUEDA Y BURBUJA (Arreglos grandes) ---
        // (Aquí solo mostramos tiempos ya que los arreglos son muy grandes para imprimir)
        int[] tamaños = {100, 500, 1000, 5000, 10000};
        System.out.println("\n=== ANÁLISIS DE ESCALABILIDAD (Búsqueda y Burbuja) ===");
        for (int n : tamaños) {
            int[] arr = generarArreglo(n);
            System.out.println("Tamaño n = " + n);
            medir("Burbuja Iterativo", () -> burbujaIterativo(arr.clone()));
            medir("Búsqueda Lineal Iter", () -> busquedaIterativa(arr, -1));
        }
    }

    // --- MÉTODOS LÓGICOS ---

    public static long fibIterativo(int n) {
        if (n <= 1) return n;
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long temp = a + b; a = b; b = temp;
        }
        return b;
    }

    public static long fibRecursivo(int n) {
        if (n <= 1) return n;
        return fibRecursivo(n - 1) + fibRecursivo(n - 2);
    }

    public static double factIterativo(int n) {
        double res = 1;
        for (int i = 2; i <= n; i++) res *= i;
        return res;
    }

    public static double factRecursivo(int n) {
        if (n <= 1) return 1;
        return n * factRecursivo(n - 1);
    }

    public static void burbujaIterativo(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++)
            for (int j = 0; j < arr.length - i - 1; j++)
                if (arr[j] > arr[j + 1]) { int t = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = t; }
    }

    public static int busquedaIterativa(int[] arr, int obj) {
        for (int i = 0; i < arr.length; i++) if (arr[i] == obj) return i;
        return -1;
    }

    public static void medir(String nombre, Runnable algoritmo) {
        long inicio = System.nanoTime();
        algoritmo.run();
        long fin = System.nanoTime();
        System.out.printf("   > %-20s: %.4f ms\n", nombre, (fin - inicio) / 1_000_000.0);
    }

    public static int[] generarArreglo(int n) {
        int[] arr = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) arr[i] = r.nextInt(10000);
        return arr;
    }
}