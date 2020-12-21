package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Stream;

public class Server1 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(24000);

        while (true) {
            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    int result = Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
                            .limit(Integer.parseInt(line))
                            .map(t -> t[0])
                            .mapToInt(Integer::intValue)
                            .sum();
                    out.println("Сумма чисел Фибоначчи равна: " + result);

                    if (line.equals("end")) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}
