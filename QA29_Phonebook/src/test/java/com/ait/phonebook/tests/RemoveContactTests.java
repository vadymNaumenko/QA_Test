package com.ait.phonebook.tests;

import com.ait.phonebook.fw.models.Contact;
import com.ait.phonebook.fw.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase {


    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
        app.getUser().clickOnLoginLink();

        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("manuel@gm.com")
                .setPassword("Manuel1234$"));
        app.getUser().clickOnLoginButton();

        app.getContact().clickOnAddLink();

        app.getContact().fillContactForm(new Contact()
                .setName("Karl")
                .setLastname("Adam")
                .setPhone("123456789012")
                .setEmail("adam@gm.co")
                .setAddress("Koblenz")
                .setDescription("goalkeeper"));

        app.getContact().clickOnSaveButton();
    }

    @Test
    public void removeContactPositiveTest() {
        //get size of contacts before remove
        int sizeBefore = app.getContact().sizeOfContacts();

        app.getContact().removeContact();
        app.getContact().pause(1000);
        //get size of contacts after remove
        int sizeAfter = app.getContact().sizeOfContacts();
        //assert: contact is removed
        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }

}
