package bg.uni.sofia.fmi.echo.server.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.nio.channels.ServerSocketChannel;

import java.util.Set;

public class EchoServer {

	public static void main(String[] args) {

		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(4444));
			Selector selector = Selector.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			ByteBuffer buffer = ByteBuffer.allocate(256);

			while (true) {

				int readyChannels = selector.select();
				if (readyChannels == 0)
					continue;

				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
				while (keyIterator.hasNext()) {
					SelectionKey key = keyIterator.next();
					if (key.isAcceptable()) {
						ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) key.channel();
						SocketChannel socketChannel = serverSocketChannel1.accept();
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ);

					}
					if (key.isReadable()) {
						try (SocketChannel sc = (SocketChannel) key.channel();) {
							buffer.clear();
							int i = sc.read(buffer);
							if (i <= 0) {
								continue;
							}
							buffer.flip();
							StringBuilder reversed = new StringBuilder();
							reversed.append(new String(buffer.array()).trim());
							reversed = reversed.reverse();
							buffer.clear();
						 buffer.put((reversed.toString() +"/n" ).getBytes());
							buffer.flip();
							sc.write(buffer);
						}
					}

					keyIterator.remove();
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
