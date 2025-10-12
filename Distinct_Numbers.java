import java.nio.*;
import java.nio.channels.*;
import java.util.HashSet;

public class Distinct_Numbers {
    public static void main(String[] args) throws Exception {
        // NIO setup
        ReadableByteChannel in = Channels.newChannel(System.in);
        ByteBuffer buf = ByteBuffer.allocateDirect(4 << 20); // 4MB buffer
        int bytesRead;
        while ((bytesRead = in.read(buf)) > 0) ; // read full input
        buf.flip(); // switch to read mode

        int n = nextInt(buf);
        HashSet<Integer> set = new HashSet<>(n);

        for (int i = 0; i < n; i++) {
            set.add(nextInt(buf));
        }

        System.out.println(set.size());
    }

    static int nextInt(ByteBuffer b) {
        int c = 0, x = 0, sign = 1;
        // skip whitespace
        while (b.hasRemaining() && (c = b.get()) <= ' ') ;

        if (!b.hasRemaining()) return 0;  // defensive

        if (c == '-') {
            sign = -1;
            c = b.get();
        }

        while (b.hasRemaining() && c >= '0' && c <= '9') {
            x = x * 10 + (c - '0');
            if (!b.hasRemaining()) break;
            c = b.get();
        }
        return x * sign;
    }
}