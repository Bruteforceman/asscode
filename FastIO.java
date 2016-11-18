import java.io.*;
import java.util.*;

public class Main {
    public void solve() throws IOException {
        // put your code here
        String s = in.next();
        int n = in.nextInt();
        long l = in.nextLong();
        for (int i = 0; i < 100; i++) {
            out.printNext(i);
        }
        out.println();
        out.println(s);

      

    }

    FastScanner in;
    FastWriter out;
    long infLong = Long.MAX_VALUE;
    int infInt = Integer.MAX_VALUE;

    public void run() {
        try {
            in = new FastScanner();
            out = new FastWriter();

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    class FastWriter extends PrintWriter {
        FastWriter() throws FileNotFoundException {
            super(System.out);
        }

        void printNext(int i) {
            print(i);
            print(' ');
        }

        void printNext(long i) {
            print(i);
            print(' ');
        }

        void printNext(char ch) {
            print(ch);
            print(' ');
        }


        <T extends Iterable<Integer>> void printIterable(T o) {
            for (Integer t : o) {
                printNext(t);
            }
        }

        void printlnArray(int[] a) {
            for (int i : a) {
                printNext(i);
            }
            println();
        }

        void printlnArray(long[] a) {
            for (long i : a) {
                printNext(i);
            }
            println();
        }


    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;


        boolean ready() throws IOException {
            return (br.ready() || st.hasMoreTokens());
        }

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

    }

    public static void main(String[] arg) {
        new Main().run();
    }
}
