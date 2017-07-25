package test.java.main.java.com.djrapitops.plan.data;

import com.djrapitops.plugin.utilities.player.IOfflinePlayer;
import main.java.com.djrapitops.plan.Plan;
import main.java.com.djrapitops.plan.data.SessionData;
import main.java.com.djrapitops.plan.data.UserData;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import test.java.utils.MockUtils;
import test.java.utils.TestInit;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Rsl1122
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(JavaPlugin.class)
public class UserDataTest {

    private UserData test;
    private Plan plan;

    /**
     *
     */
    public UserDataTest() {
    }

    /**
     *
     */
    @Before
    public void setUp() throws Exception {
//        TestInit t = TestInit.init();
        test = new UserData(UUID.fromString("7f8149a0-b5a5-4fcd-80b5-6cff083a99f1"), 0, true, "CREATIVE", "Testname", true);
    }

    /**
     *
     * @throws UnknownHostException
     */
    @Test
    public void testAddIpAddress() throws UnknownHostException {
        InetAddress ip = InetAddress.getByName("247.183.163.155");
        InetAddress ip2 = InetAddress.getByName("95.19.221.226");
        test.addIpAddress(ip);
        test.addIpAddress(ip2);
        test.addIpAddress(ip2);
        test.addIpAddress(null);
        assertTrue("Didn't add 1", test.getIps().contains(ip));
        assertTrue("Didn't add 2", test.getIps().contains(ip2));
        assertTrue("Added null", !test.getIps().contains(null));
        assertTrue("Added multiples", test.getIps().size() == 2);
    }

    /**
     *
     * @throws UnknownHostException
     */
    @Test
    public void testAddIpAddresses() throws UnknownHostException {
        List<InetAddress> ips = new ArrayList<>();
        InetAddress ip = InetAddress.getByName("247.183.163.155");
        InetAddress ip2 = InetAddress.getByName("95.19.221.226");
        ips.add(ip);
        ips.add(ip2);
        ips.add(ip2);
        ips.add(null);
        test.addIpAddresses(ips);
        assertTrue("Didn't add 1", test.getIps().contains(ip));
        assertTrue("Didn't add 2", test.getIps().contains(ip2));
        assertTrue("Added null", !test.getIps().contains(null));
        assertTrue("Added multiples", test.getIps().size() == 2);
    }

    /**
     *
     */
    @Test
    public void testAddIpAddressesEmpty() {
        List<InetAddress> ips = new ArrayList<>();
        test.addIpAddresses(ips);
        assertTrue("Added something", test.getIps().isEmpty());
    }

    /**
     *
     */
    @Test
    public void testAddNickname() {
        String one = "Test1";
        String two = "Test2";
        boolean n1 = test.addNickname(two);
        boolean n2 = test.addNickname(two);
        boolean n = test.addNickname(one);
        test.addNickname(null);
        assertTrue("Didn't add 1", test.getNicknames().contains(one));
        assertTrue("Didn't add 2", test.getNicknames().contains(two));
        assertTrue("1 is supposed to be new", n);
        assertTrue("2 is supposed to be new", n1);
        assertTrue("2 is not supposed to be new", !n2);
        assertTrue("Added null", !test.getNicknames().contains(null));
        assertTrue("Added multiples", test.getNicknames().size() == 2);
        assertTrue("Last nickname was not one", test.getLastNick().equals(one));
    }

    /**
     *
     */
    @Test
    public void testAddNicknames() {
        String one = "Test1";
        String two = "Test2";
        List<String> o = new ArrayList<>();
        o.add(one);
        o.add(two);
        o.add(two);
        o.add(null);
        test.addNicknames(o);
        assertTrue("Didn't add 1", test.getNicknames().contains(one));
        assertTrue("Didn't add 2", test.getNicknames().contains(two));
        assertTrue("Added null", !test.getNicknames().contains(null));
        assertTrue("Added multiples", test.getNicknames().size() == 2);
    }

    /**
     *
     */
    @Test
    public void testAddNicknamesEmpty() {
        List<String> o = new ArrayList<>();
        test.addNicknames(o);
        assertTrue("Added something", test.getNicknames().isEmpty());
    }

    /**
     *
     */
    @Test
    public void testSetGMTime() {
        test.setGMTime("SURVIVAL", 1L);
        final Long result = test.getGmTimes().get("SURVIVAL");
        assertTrue("" + result, result == 1L);
    }

    /**
     *
     */
    @Test
    public void testSetGMTimeWhenGMTimesNull() {
        test.setGmTimes(null);
        String gm = "SURVIVAL";
        test.setGMTime(gm, 1L);
        final Long result = test.getGmTimes().get(gm);
        assertTrue("" + result, result == 1L);
    }

    /**
     *
     */
    @Test
    public void testSetAllGMTimes() {
        HashMap<String, Long> gmTimes = new HashMap<>();
        gmTimes.put(null, 0L);
        test.setGmTimes(gmTimes);
        test.setAllGMTimes(1L, 2L, 3L, 4L);
        Map<String, Long> times = test.getGmTimes();
        assertTrue("Cleared gmTimes", !times.containsKey(null));
        assertTrue("Not equal 0", times.get("SURVIVAL") == 1L);
        assertTrue("Not equal 1", times.get("CREATIVE") == 2L);
        assertTrue("Not equal 2", times.get("ADVENTURE") == 3L);
        assertTrue("Not equal 3", times.get("SPECTATOR") == 4L);
    }

    /**
     *
     */
    @Test
    public void testAddSession() {
        SessionData correct = new SessionData(0, 1);
        test.addSession(correct);
        assertTrue("Didn't add correct one", test.getSessions().contains(correct));
    }

    /**
     *
     */
    @Test
    public void testAddSessionIncorrect() {
        SessionData incorrect = new SessionData(0);
        test.addSession(incorrect);
        assertTrue("Added incorrect one", !test.getSessions().contains(incorrect));
    }

    /**
     *
     */
    @Test
    public void testAddSessionNull() {
        SessionData incorrect = null;
        test.addSession(incorrect);
        assertTrue("Added null", !test.getSessions().contains(incorrect));
    }

    /**
     *
     */
    @Test
    public void testAddSessions() {
        SessionData correct = new SessionData(0, 1);
        SessionData incorrect = new SessionData(0);
        List<SessionData> o = new ArrayList<>();
        o.add(correct);
        o.add(incorrect);
        o.add(null);
        test.addSessions(o);
        assertTrue("Didn't add correct one", test.getSessions().contains(correct));
        assertTrue("Added incorrect one", !test.getSessions().contains(incorrect));
        assertTrue("Added null", !test.getSessions().contains(incorrect));
    }

    /**
     *
     */
    @Test
    public void testAddSessionsEmpty() {
        List<SessionData> o = new ArrayList<>();
        test.addSessions(o);
        assertTrue("Added something", test.getSessions().isEmpty());
    }

    /**
     *
     */
    @Test
    public void testSetCurrentSession() {
        SessionData s = new SessionData(0);
        test.setCurrentSession(s);
        assertEquals(test.getCurrentSession(), s);
    }

    /**
     *
     */
    @Test
    public void testUpdateBanned() {
        test.updateBanned(true);
        assertTrue("Not true", test.isBanned());
        test.updateBanned(false);
        assertTrue("True", !test.isBanned());
    }

    /**
     *
     */
    @Test
    public void testIsAccessed() {
        test.access();
        assertTrue("Not accessed, even though accessing", test.isAccessed());
        test.access();
        test.stopAccessing();
        assertTrue("Not accessed, even though accessing", test.isAccessed());
        test.stopAccessing();
        assertTrue("Accessed, even though not accessing", !test.isAccessed());
    }

    /**
     *
     */
    @Test
    public void testAccess() {
        test.access();
        assertTrue("Not accessed, even though accessing", test.isAccessed());
    }

    /**
     *
     */
    @Test
    public void testStopAccessing() {
        test.access();
        test.stopAccessing();
        assertTrue("Accessed, even though not accessing", !test.isAccessed());
    }

    /**
     *
     */
    @Test
    public void testEquals() {
        assertTrue("Not Equals!", test.equals(new UserData(UUID.fromString("7f8149a0-b5a5-4fcd-80b5-6cff083a99f1"), 0, true, "CREATIVE", "Testname", true)));
    }

    /**
     *
     */
    @Test
    public void testEqualsNot() {
        UserData notEqual = new UserData(UUID.fromString("7f8149a0-b5a5-4fcd-80b5-6cff083a99f1"), 0, true, "CREATIVE", "WRONG", true);
        assertTrue("Equals!", !notEqual.equals(test));
    }

    /**
     *
     */
    @Test
    public void testEqualsNot2() {
        Object o = "NOT";
        assertTrue("Equals!", !test.equals(o));
    }

    /**
     *
     */
    @Test
    public void testCopyConstructor() {
        UserData copy = new UserData(test);
        assertTrue("Not copied properly", test.equals(copy));
    }

    /**
     *
     */
    @Test
    public void testPlayerConstructor() {
        test = new UserData(MockUtils.mockIPlayer());
        UserData expected = new UserData(UUID.fromString("45b0dfdb-f71d-4cf3-8c21-27c9d4c651db"), 1234567L, true, "SURVIVAL", "TestName", true);
        expected.updateBanned(true);
        assertTrue("Not equal!", test.equals(expected));
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void testPlayerConstructorBrokenBanned() throws Exception {
        TestInit.init();
        test = new UserData(MockUtils.mockBrokenPlayer());
        UserData expected = new UserData(UUID.fromString("45b0dfdb-f71d-4cf3-8c21-27c9d4c651db"), 1234567L, true, "SURVIVAL", "TestName", true);
        expected.updateBanned(false);
        assertTrue("Not equal!", test.equals(expected));
    }

    /**
     *
     */
    @Test
    public void testOfflinePlayerConstructor() {
        test = new UserData((IOfflinePlayer) MockUtils.mockIPlayer());
        UserData expected = new UserData(UUID.fromString("45b0dfdb-f71d-4cf3-8c21-27c9d4c651db"), 1234567L, true, "SURVIVAL", "TestName", true);
        expected.updateBanned(true);
        assertTrue("Not equal!", test.equals(expected));
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void testOfflinePlayerConstructorBrokenBanned() throws Exception {
        TestInit.init();
        test = new UserData((IOfflinePlayer) MockUtils.mockBrokenPlayer());
        UserData expected = new UserData(UUID.fromString("45b0dfdb-f71d-4cf3-8c21-27c9d4c651db"), 1234567L, true, "SURVIVAL", "TestName", true);
        expected.updateBanned(false);
        assertTrue("Not equal!", test.equals(expected));
    }

    /**
     *
     */
    @Test
    public void testGetUUID() {
        assertEquals(test.getUuid(), UUID.fromString("7f8149a0-b5a5-4fcd-80b5-6cff083a99f1"));
    }
}
