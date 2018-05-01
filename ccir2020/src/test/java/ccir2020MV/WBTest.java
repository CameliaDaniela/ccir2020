package ccir2020MV;

import ccir2020MV.model.base.Activity;
import ccir2020MV.model.base.Contact;
import ccir2020MV.model.repository.classes.RepositoryActivityFile;
import ccir2020MV.model.repository.classes.RepositoryContactFile;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WBTest {
    @Test
    public void testAddInsidePeriod()
    {
        RepositoryActivityFile repositoryActivityFile = null;
        try {
           repositoryActivityFile =new RepositoryActivityFile(new RepositoryContactFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(repositoryActivityFile.getActivities().size());
        Calendar c=Calendar.getInstance();
        c.set(2012,Calendar.JANUARY,12,12,12,0);
        Date start=c.getTime();
        c.set(2012,Calendar.JANUARY,14,13,10,0);
        Date end=c.getTime();
        List<Contact> cl= null;
        Activity a=new Activity("Camelia", start, end,cl,"descr" );
        Assert.assertTrue(repositoryActivityFile.addActivity(a));
        //System.out.println(repositoryActivityFile.getActivities().size());
        c.set(2012,Calendar.JANUARY,13,11,12,1);
        start=c.getTime();
        c.set(2012,Calendar.JANUARY,13,16,10,0);
        end=c.getTime();
        Activity b=new Activity("Camelia", start, end,cl,"descr" );

        Assert.assertFalse(repositoryActivityFile.addActivity(b));


    }
    @Test
    public void testIfIsVid()
    {
        RepositoryActivityFile repositoryActivityFile = null;
        try {
            repositoryActivityFile =new RepositoryActivityFile(new RepositoryContactFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
       while (repositoryActivityFile.getActivities().size()>0)
       {
           repositoryActivityFile.removeActivityByID(0);
       }
        System.out.println(repositoryActivityFile.getActivities().size());
        Calendar c=Calendar.getInstance();
        c.set(2012,Calendar.JANUARY,12,12,12,0);
        Date start=c.getTime();
        c.set(2012,Calendar.JANUARY,14,13,10,0);
        Date end=c.getTime();
        List<Contact> cl= null;
        Activity a=new Activity("Camelia", start, end,cl,"descr" );
        Assert.assertTrue(repositoryActivityFile.addActivity(a));


    }
    @Test
    public void testAddAfterPeriod()
    {
        RepositoryActivityFile repositoryActivityFile = null;
        try {
            repositoryActivityFile =new RepositoryActivityFile(new RepositoryContactFile());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar c=Calendar.getInstance();
        c.set(2012,Calendar.JANUARY,12,12,12,0);
        Date start=c.getTime();
        c.set(2012,Calendar.JANUARY,14,13,10,0);
        Date end=c.getTime();
        List<Contact> cl= null;
        Activity a=new Activity("Camelia", start, end,cl,"descr" );
        Assert.assertTrue(repositoryActivityFile.addActivity(a));
        //System.out.println(repositoryActivityFile.getActivities().size());
        c.set(2012,Calendar.JANUARY,15,11,12,1);
        start=c.getTime();
        c.set(2012,Calendar.JANUARY,16,16,10,0);
        end=c.getTime();
        Activity b=new Activity("Camelia", start, end,cl,"descr" );

        Assert.assertTrue(repositoryActivityFile.addActivity(b));
    }
    @Test
    public void testAddBeforePeriod()
    {
        RepositoryActivityFile repositoryActivityFile = null;
        try {
            repositoryActivityFile =new RepositoryActivityFile(new RepositoryContactFile());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar c=Calendar.getInstance();
        c.set(2012,Calendar.JANUARY,12,12,12,0);
        Date start=c.getTime();
        c.set(2012,Calendar.JANUARY,14,13,10,0);
        Date end=c.getTime();
        List<Contact> cl= null;
        Activity a=new Activity("Camelia", start, end,cl,"descr" );
        Assert.assertTrue(repositoryActivityFile.addActivity(a));
        //System.out.println(repositoryActivityFile.getActivities().size());
        c.set(2012,Calendar.JANUARY,10,11,12,1);
        start=c.getTime();
        c.set(2012,Calendar.JANUARY,11,16,10,0);
        end=c.getTime();
        Activity b=new Activity("Camelia", start, end,cl,"descr" );

        Assert.assertTrue(repositoryActivityFile.addActivity(b));


    }
}
