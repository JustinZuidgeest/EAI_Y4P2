package hanze.nl.infobord;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class QueueListener implements MessageListener {
    private InfoBord infoBord;
//TODO 	implementeer de messagelistener die het bericht ophaald en
//		doorstuurd naar verwerkBericht van het infoBord.
//		Ook moet setRegels aangeroepen worden.

    QueueListener(){
        this.infoBord = InfoBord.getInfoBord();
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                InfoBord.verwerkBericht(text);
                infoBord.setRegels();
            } else {
                System.out.println("Non-textMessage received: " + message);
            }
        } catch (JMSException e) {
            System.out.println("Something went wrong :(");
            e.printStackTrace();
        }
    }
}

