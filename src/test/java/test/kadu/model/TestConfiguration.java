package test.kadu.model;

import com.kadu.helper.OperationSystem;
import com.kadu.model.Configuration;
import com.kadu.model.Directory;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TestConfiguration
{

    @Test
    public void testEmpty()
    {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Configuration config = new Configuration();
        Set<ConstraintViolation<Configuration>> violations = validator.validate(config);

        assertEquals(0, violations.size());
    }

    @Test
    public void testAddDirectory() throws UnsupportedEncodingException, NoSuchAlgorithmException
    {
        Configuration config = new Configuration();
        assertEquals(0, config.getDirectories().size());

        Directory dir01 = new Directory();
        String[] path01 = {"first", "second", "third"};
        dir01.setPath(OperationSystem.concatenate(path01));

        assertTrue(config.addDirectory(dir01));
        assertEquals(1, config.getDirectories().size());

        Directory dir02 = new Directory();
        String[] path02 = {"one", "two", "three"};
        dir02.setPath(OperationSystem.concatenate(path02));

        config.addDirectory(dir02);
        assertEquals(2, config.getDirectories().size());

        assertFalse(config.addDirectory(dir01));
        assertEquals(2, config.getDirectories().size());
    }

    @Test
    public void testSetDirectories() throws UnsupportedEncodingException, NoSuchAlgorithmException
    {
        ArrayList<Directory> directories = new ArrayList();

        Directory dir01 = new Directory();
        String[] path01 = {"first", "second", "third"};
        dir01.setPath(OperationSystem.concatenate(path01));
        directories.add(dir01);

        Directory dir02 = new Directory();
        String[] path02 = {"one", "two", "three"};
        dir02.setPath(OperationSystem.concatenate(path02));
        directories.add(dir02);

        Configuration config = new Configuration();
        assertEquals(0, config.getDirectories().size());

        config.setDirectories(directories);
        assertEquals(2, config.getDirectories().size());

    }

    @Test
    public void testValidateDirectory() throws UnsupportedEncodingException, NoSuchAlgorithmException
    {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Configuration config = new Configuration();

        Directory dir01 = new Directory();
        String[] path01 = {"first", "second", "third"};
        dir01.setPath(OperationSystem.concatenate(path01));

        config.addDirectory(dir01);
        assertEquals(1, config.getDirectories().size());

        Set<ConstraintViolation<Configuration>> violations = validator.validate(config);
        assertEquals(2, violations.size());
    }

    @Test
    public void testRemoveDirectory() throws UnsupportedEncodingException, NoSuchAlgorithmException
    {
        Configuration config = new Configuration();

        Directory dir01 = new Directory();
        String[] path01 = {"first", "second", "third"};
        dir01.setPath(OperationSystem.concatenate(path01));

        Directory dir02 = new Directory();
        String[] path02 = {"one", "two", "three"};
        dir02.setPath(OperationSystem.concatenate(path02));

        config.addDirectory(dir01);
        config.addDirectory(dir02);
        assertEquals(2, config.getDirectories().size());

        assertTrue(config.removeDirectory(dir01));
        assertEquals(1, config.getDirectories().size());

        assertFalse(config.removeDirectory(dir01));
        assertEquals(1, config.getDirectories().size());
    }

}
