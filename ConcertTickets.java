import java.nio.*;
import java.nio.channels.*;
import java.util.TreeMap;


public class ConcertTickets{
    public static void main(String[] args) throws Exception {
        // NIO setup
        ReadableByteChannel in = Channels.newChannel(System.in);
        ByteBuffer buf = ByteBuffer.allocateDirect(4 << 20); // 4MB buffer
        int bytesRead;
        while ((bytesRead = in.read(buf)) > 0); // read full input
        buf.flip(); // switch to read mode

        int n = nextInt(buf);// tickets
        int m= nextInt(buf);// max_price

        TreeMap<Integer, Integer> ticket = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int price= nextInt(buf);
            ticket.put(price,ticket.getOrDefault(price,0)+1); // key value pair for ticket as key and ticket count as value
        }

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int maxPrice = nextInt(buf);
            Integer t = ticket.floorKey(maxPrice);

            if (t == null) {
                out.append("-1\n");
            } else {
                out.append(t).append('\n');
                if (ticket.get(t) == 1)
                    ticket.remove(t);
                else
                    ticket.put(t, ticket.get(t) - 1);
            }
        }
        System.out.print(out);
    }

    static int nextInt(ByteBuffer b) {
        int c = 0, x = 0, sign = 1;
        // skip whitespace
        while (b.hasRemaining() && (c = b.get()) <= ' ');

        if (!b.hasRemaining()) return 0;  // defensive

        if (c == '-') { sign = -1; c = b.get(); }

        while (b.hasRemaining() && c >= '0' && c <= '9') {
            x = x * 10 + (c - '0');
            if (!b.hasRemaining()) break;
            c = b.get();
        }
        return x * sign;
    }
}