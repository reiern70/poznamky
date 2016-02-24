/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.conversation;

import myapp.conversation.logging.Log;
import org.apache.wicket.cdi.DetachEvent;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import java.io.Serializable;

/**
 * @author te071784
 */
@ConversationScoped
public class Client implements Serializable {
    private String name;
    private String lastName;
    private String nickName;
    private int age;
    private String title;
    private int currentPageId;

    void onDetach(@Observes(notifyObserver = Reception.IF_EXISTS) DetachEvent detachEvent) {
        Log.AUDIT.debug("");
    }

    public int getCurrentPageId() {
        return currentPageId;
    }

    public void setCurrentPageId(int currentPageId) {
        this.currentPageId = currentPageId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        String clientString = String.format("name=%s lastname=%s nickname=%s age=%s title=%s", name, lastName, nickName, age, title);
        return clientString;
    }
}
