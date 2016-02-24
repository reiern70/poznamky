/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import myapp.conversation.screens.HomePage;
import myapp.conversation.WebApp;
import org.apache.wicket.util.tester.WicketTester;

/**
 *
 * @author te071784
 */
public class HomePageTest {
    
    private WicketTester tester;


    public void setUp() {
        tester = new WicketTester(new WebApp());
    }

    public void homepageRendersSuccessfully() {
        //start and render the test page
        tester.startPage(HomePage.class);
        //assert rendered page class
        tester.assertRenderedPage(HomePage.class);
    }
}
