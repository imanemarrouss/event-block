/*
package ma.sir.event.ws.facade.admin;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.EncodeException;
import javax.websocket.Session;

import ma.sir.event.bean.core.Evenement;
import ma.sir.event.service.facade.admin.EvenementAdminService;
import ma.sir.event.ws.converter.EvenementConverter;
import ma.sir.event.ws.dto.EvenementDto;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Component;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ServerEndpoint("/api/admin/evenement/salle/{salleId}")
@Component
public class EvenementWebSocket {

    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @Autowired
    private EvenementAdminService evenementService;

    @Autowired
    private EvenementConverter evenementConverter;
    @OnOpen
    public void onOpen(Session session, @PathParam("salleId") Long salleId) {
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(Session session, String message, @PathParam("salleId") Long salleId) {
        if ("subscribe".equals(message)) {
            // Get evenements for the specified salle
            List<EvenementDto> evenements = evenementService.findBySalleId(salleId)
                    .stream()
                    .map(e -> evenementConverter.toDto(e))
                    .collect(Collectors.toList());

            // Send the evenements to the client
            sendToSession(session, evenements);
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    private void sendToSession(Session session, Object message) {
        try {
            session.getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException e) {
            // Handle exception
        }
    }


}
*/
