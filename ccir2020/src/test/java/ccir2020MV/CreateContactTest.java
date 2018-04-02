package ccir2020MV;
import ccir2020MV.exceptions.InvalidFormatException;
import ccir2020MV.model.base.Contact;
import ccir2020MV.model.repository.classes.RepositoryContactFile;
import org.junit.Assert;
import org.junit.Test;
public class CreateContactTest {
    @Test
    public void testAdress() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("Nume","","0765443453");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("Invalid address",message);
    }

    @Test
    public void testName() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("","address","0756434532");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("Invalid name",message);
    }

    @Test
    public void testTelefon1() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("Nume","address","");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("Invalid phone number",message);
    }
    @Test
    public void testTelefon2() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("Nume","address","0756");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("Invalid phone number",message);
    }
    @Test
    public void testTelefon3() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("Nume","address","8976547382");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("Invalid phone number",message);
    }
    @Test
    public void testTelefon4() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("Nume","address","0754dg3452");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("Invalid phone number",message);
    }
    @Test
    public void testTelefon5() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        String phoneNR="";
        for(int i=0;i<254;i++)
        {
            phoneNR=phoneNR+i%10;

        }
        //System.out.println(phoneNR);
        try {
            repositoryContactFile.adaugContact("Nume","address", phoneNR);
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("Invalid phone number",message);
    }

    @Test
    public void succesAdd1() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("Nume","address","0756745839");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("",message);
    }
    @Test
    public void succesAdd2() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("N","address","0756745839");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("",message);
    }
    @Test
    public void succesAdd3() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("Nume","a","0756745839");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("",message);
    }
}
