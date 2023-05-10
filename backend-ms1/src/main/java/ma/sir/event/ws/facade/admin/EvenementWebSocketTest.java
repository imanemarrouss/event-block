/*
package ma.sir.event.ws.facade.admin;

import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ma.sir.event.ws.facade.admin.EvenementWebSocket;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EvenementWebSocketTest {

    private static final String SERVER_URI = "ws://localhost:8080/api/admin/evenement/salle/1"; // Replace with your server URI
    private static final String SUBSCRIBE_MESSAGE = "subscribe";

    private CountDownLatch latch;

    @Test
    public void testWebSocket() throws Exception {
        latch = new CountDownLatch(1);
        WebSocketClientEndpoint client = new WebSocketClientEndpoint(new URI(SERVER_URI));
        client.connect();
        client.sendMessage(SUBSCRIBE_MESSAGE);
        latch.await(10, TimeUnit.SECONDS); // Wait for messages to be received
        client.close();
    }

    @ClientEndpoint
    public class WebSocketClientEndpoint {
        private Session session;

        public WebSocketClientEndpoint(URI uri) {
            try {
                ContainerProvider.getWebSocketContainer().connectToServer(this, uri);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @OnOpen
        public void onOpen(Session session) {
            this.session = session;
        }

        @OnMessage
        public void onMessage(String message) {
            // Handle incoming message
            System.out.println("Received message: " + message);
            latch.countDown(); // Signal that a message has been received
        }

        @OnClose
        public void onClose(CloseReason reason) {
            System.out.println("WebSocket closed: " + reason);
        }

        @OnError
        public void onError(Throwable error) {
            Assertions.fail("WebSocket error", error);
        }

        public void sendMessage(String message) throws Exception {
            session.getBasicRemote().sendText(message);
        }

        public void close() throws Exception {
            session.close();
        }
    }

}

*/
