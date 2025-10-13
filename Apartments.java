import java.nio.*;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Collections;

public class Apartments{
    public static void main(String[] args) throws Exception {
        // NIO setup
        ReadableByteChannel in = Channels.newChannel(System.in);
        ByteBuffer buf = ByteBuffer.allocateDirect(4 << 20); // 4MB buffer
        int bytesRead;
        while ((bytesRead = in.read(buf)) > 0); // read full input
        buf.flip(); // switch to read mode

        int n = nextInt(buf);
        int m= nextInt(buf);
        int k= nextInt(buf);

        ArrayList<Integer> arrn = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            arrn.add(nextInt(buf));
        }
        ArrayList<Integer> arrm = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            arrm.add(nextInt(buf));
        }
        Collections.sort(arrn);
        Collections.sort(arrm);
        int i=0,j=0,cnt=0;
        while(i<n && j<m){
            if(arrm.get(j) < arrn.get(i)-k){
                j++;
            }
            else if(arrm.get(j) > arrn.get(i)+k){
                i++;
            }
            else{
                i++;
                j++;
                cnt++;
            }
        }
        System.out.println(cnt);
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