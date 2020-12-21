package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Server2 {
    public static void main(String[] args) throws IOException {
        final ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("Localhost", 24001));

        while (true) {
            try (SocketChannel socketChannel = serverChannel.accept()) {
                final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);

                while (socketChannel.isConnected()) {
                    int bytesCount = socketChannel.read(inputBuffer);

                    if (bytesCount == -1) break;

                    String msg = new String(inputBuffer.array(), 0, bytesCount, StandardCharsets.UTF_8);

                    System.out.println("Получено сообщение отклиента: " + msg);

                    socketChannel.write(ByteBuffer.wrap(msg.replaceAll("\\s+", "").getBytes(StandardCharsets.UTF_8)));

                }
            } catch (IOException err) {
                System.out.println(err.getMessage());
            }
        }
    }
}
