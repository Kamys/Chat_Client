package client.gui.Chat;



import client.gui.Chat.componentsChat.JMessageList;
import client.gui.Chat.componentsChat.JMessageListBloc;
import client.gui.Chat.componentsChat.Message;
import client.observer.ObservableMessages;
import client.observer.ObserverMessage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by HNKNTOC on 03.01.2016.
 */
public class ChatGUIFacade implements ObservableMessages,ObserverMessage {
    private FrameChat frameChat;
    private ArrayList<ObserverMessage> OBSERVER_MESSAGES = new ArrayList<>();
    private JMessageList jMessageList = new JMessageListBloc();

    public ChatGUIFacade() {

    }

    public void setJMessageList(JMessageList jMessageList){
        this.jMessageList=jMessageList;
    }

    public void chatStart(){
        frameChat = new FrameChat(jMessageList);
        frameChat.addListenerButtonPrint(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyNewMessages("Hnkntoc Client",frameChat.getTextNewMessage(),"Data Client");
            }
        });
        frameChat.start();
    }

    @Override
    public void registerObserver(ObserverMessage o) {
        OBSERVER_MESSAGES.add(o);
    }

    @Override
    public void removeObserver(ObserverMessage o) {
        OBSERVER_MESSAGES.remove(o);
    }

    @Override
    public void notifyNewMessages(String nameAuthor, String content, String data) {
        for(ObserverMessage observerMessage:OBSERVER_MESSAGES){
            observerMessage.updateNewMessage(nameAuthor, content, data);
        }
    }

    @Override
    public void updateNewMessage(String nameAuthor, String content, String data) {
        frameChat.getMessageDisplay().addMessage(new Message(nameAuthor,content,data));
    }
}
