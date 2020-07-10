package IO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Locale;

public class LiveData {

    private ByteArrayOutputStream outStream;

    public static void main(String[] args) {
        LiveData app = new LiveData();
        app.createData();
        app.readData();

    }

    public void createData() {
        try {
            outStream = new ByteArrayOutputStream();
            DataOutputStream wrtiter = new DataOutputStream(outStream);
            for (int i = 0; i < 10; i++) {
                Trade lastTrade = new Trade(i);
                wrtiter.writeInt(lastTrade.scripCode);
                wrtiter.write(lastTrade.time);
                wrtiter.writeDouble(lastTrade.bid);
                wrtiter.writeDouble(lastTrade.offer);
                wrtiter.writeDouble(lastTrade.high);
                wrtiter.writeDouble(lastTrade.low);
                wrtiter.writeLong(lastTrade.quantity);
            }
        } catch (Exception e) {
            System.out.println("Error while writing data to buffer");
        }
    }


    public void readData() {
        byte[] timeBuffer = new byte[8];
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        ByteArrayInputStream inStream = new ByteArrayInputStream(outStream.toByteArray());
        DataInputStream reader = new DataInputStream(inStream);

        try {
            for (int i = 0; i < 10; i++) {
                int scripCode = reader.readInt();
                reader.read(timeBuffer);
                String time = new String(timeBuffer);
                double bid = reader.readDouble();
                double offer = reader.readDouble();
                double high = reader.readDouble();
                double low = reader.readDouble();
                long volume = reader.readLong();

                formatter.format("ScripCode:%2d"
                                + "\tTime: %s"
                                + "\tBid:$ %05.2f"
                                + "\tOffer:$ %05.2f"
                                + "\tHigh:$ %05.2f"
                                + "\tLow:$ %05.2f"
                                + "\tVolume: %d",
                        scripCode, time, bid, offer, high, low, volume);
                System.out.println(sb);
                sb.delete(0, sb.length());
            }
        } catch (Exception e) {
            System.out.println("Error in reading data");
        }
    }

    private class Trade {
        private int scripCode;
        private byte[] time;
        private double bid, offer, high, low;
        private long quantity;

        public Trade(int i) {
            scripCode = i + 1;
            time = now("hh:mm:ss").getBytes();
            bid = Math.random() * 100;
            offer = Math.random() * 100;
            high = Math.random() * 100;
            low = Math.random() * 100;
            quantity = (long) Math.random() * 1000000000;
        }

        private String now(String dateFormat) {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            return sdf.format(cal.getTime());
        }

    }
}
