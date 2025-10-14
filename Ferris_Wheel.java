import java.nio.*;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Collections;

public class Ferris_Wheel{
    public static void main(String[] args) throws Exception {
        // NIO setup
        ReadableByteChannel in = Channels.newChannel(System.in);
        ByteBuffer buf = ByteBuffer.allocateDirect(4 << 20); // 4MB buffer
        int bytesRead;
        while ((bytesRead = in.read(buf)) > 0); // read full input
        buf.flip(); // switch to read mode

        int n = nextInt(buf);
        int maxwieght= nextInt(buf);

        ArrayList<Integer> weight_arr = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            weight_arr .add(nextInt(buf));
        }

        Collections.sort(weight_arr);
        int i=0,j=n-1,cnt=0;
        while(i<=j){
            if(weight_arr.get(j) + weight_arr.get(i) <= maxwieght){//if the weight of lowest and highest is < maxweight
                j--;
                i++;
            }
            else{// if the combined weight is more than maxweight
                j--;
            }
            cnt++;//add one more pod
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