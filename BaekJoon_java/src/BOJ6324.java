import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ6324 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        Pattern pProtocol = Pattern.compile("^\\w+:\\/\\/");
        Pattern pHost = Pattern.compile("\\/\\/[\\w\\.\\-]+");
        Pattern pPort = Pattern.compile("\\:\\d+");
        Pattern pPath = Pattern.compile("\\/(\\/)?[\\w]+");

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            String s = br.readLine();
            String protocol = "<default>";
            String host = "<default>";
            String port = "<default>";
            String path = "<default>";

            Matcher mProtocol = pProtocol.matcher(s);
            Matcher mHost = pHost.matcher(s);
            Matcher mPort = pPort.matcher(s);
            Matcher mPath = pPath.matcher(s);

            if (mProtocol.find()) {
                protocol = mProtocol.group().substring(0, mProtocol.group().length() - 3);
            }

            if (mHost.find()) {
                host = mHost.group().substring(2);
            }

            if (mPort.find()) {
                port = mPort.group().substring(1);
            }

            mPath.find();
            if (mPath.find()) {
                path = s.substring(mPath.start() + 1);
            }

            sb.append("URL #" + i
                    + "\n" + String.format("%-8s", "Protocol") + " = " + protocol
                    + "\n" + String.format("%-8s", "Host") + " = " + host
                    + "\n" + String.format("%-8s", "Port") + " = " + port
                    + "\n" + String.format("%-8s", "Path") + " = " + path + "\n\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
