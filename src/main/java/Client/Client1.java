package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client1 { // Использую Blocking, т.к. надо дождаться ответа сервера,
                       // а потом продолжить работу программы
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 24000);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            String line;
            while (true) {
                System.out.println("Введите номер числа по каторое хотите получить сумму чисел Фибоначчи");
                System.out.println("Или введите 'end' чтобы закрыть программу");
                line = scanner.nextLine();
                out.println(line);
                if ("end".equals(line)) break;
                System.out.println("Server: " + in.readLine());
            }
        }

    }
}
