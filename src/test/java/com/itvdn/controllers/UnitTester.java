package com.itvdn.controllers;

import com.andy.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/app-simple-ctx.xml"})
public class UnitTester {
    private static final Log LOG = LogFactory.getLog(UnitTester.class);
    @Autowired
    List<User> userList;

    @Autowired
    @Qualifier(value = "friend1")
    User user;

    @Test
    public void testUser() {
        Assert.assertEquals("Oleg", user.getName());
    }

    @Test
    public void testFriendship() {
        System.out.println("******************");
        System.out.println("info about friends");
        Iterator<User> it = userList.iterator();
        while (it.hasNext()) {
            User tempUser = it.next();
            if (it.hasNext()) {
                System.out.println(User.makeFriends(tempUser, it.next()));
            }
        }
    }

    @Test
    public void testFriendship2() {
        LOG.info("******************");
        LOG.info("info about friends");
        Iterator<User> it = userList.iterator();
        while (it.hasNext()) {
            User tempUser = it.next();
            if (it.hasNext()) {
                LOG.info(User.makeFriends(tempUser, it.next()));
            }
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUser2() {
        User.getUserName();
    }

    @Test(timeout = 15L)
    public void testUser3() {
        User.waitInQueue();
    }

    @Test(timeout = 40L)
    public void testUser4() {
        User.waitInQueue();
    }
}
