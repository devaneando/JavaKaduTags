package test.kadu.model;

import com.kadu.helper.OperationSystem;
import com.kadu.model.Directory;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TestDirectory
{

    @Test
    public void testEmpty()
    {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Directory dir = new Directory();
        Set<ConstraintViolation<Directory>> violations = validator.validate(dir);

        assertEquals(4, violations.size());

        String[] messages = {
            "The directory cannot be empty!",
            "The directory cannot be null!"
        };

        for (ConstraintViolation<Directory> violation : violations) {
            assertTrue(Arrays.asList(messages).contains(violation.getMessage()));
        }
    }

    @Test
    public void testNotExistentFolder() throws UnsupportedEncodingException, NoSuchAlgorithmException
    {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Directory dir = new Directory();
        String[] path = {"first", "second", "third"};
        dir.setPath(OperationSystem.concatenate(path));

        Set<ConstraintViolation<Directory>> violations = validator.validate(dir);
        assertEquals(2, violations.size());

        String[] messages = {
            "\"C:\\first\\second\\third\" does not exist!",
            "The folder is invalid!"
        };

        for (ConstraintViolation<Directory> violation : violations) {
            assertTrue(Arrays.asList(messages).contains(violation.getMessage()));
        }
    }

    @Test
    public void testFile() throws IOException, UnsupportedEncodingException, NoSuchAlgorithmException
    {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        String[] elements = {OperationSystem.tempFolder(), ".kaduTest"};
        String path = OperationSystem.concatenate(elements);
        File file = new File(path);
        file.createNewFile();

        Directory dir = new Directory();
        dir.setPath(file.getPath());

        Set<ConstraintViolation<Directory>> violations = validator.validate(dir);
        assertEquals(2, violations.size());

        String[] messages = {
            "\"" + path + "\" is not a directory!",
            "The folder is invalid!"
        };

        for (ConstraintViolation<Directory> violation : violations) {
            assertTrue(Arrays.asList(messages).contains(violation.getMessage()));
        }
    }

    @Test
    public void testRootFolder() throws IOException, UnsupportedEncodingException, NoSuchAlgorithmException
    {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Directory dir = new Directory();
        dir.setPath(OperationSystem.rootFolder());

        Set<ConstraintViolation<Directory>> violations = validator.validate(dir);
        assertEquals(2, violations.size());

        String[] messages = {
            "The ROOT folder is not allowed!",
            "The folder is invalid!"
        };

        for (ConstraintViolation<Directory> violation : violations) {
            assertTrue(Arrays.asList(messages).contains(violation.getMessage()));
        }
    }

    @Test
    public void testHomeFolder() throws IOException, UnsupportedEncodingException, NoSuchAlgorithmException
    {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Directory dir = new Directory();
        dir.setPath(OperationSystem.homeFolder());

        Set<ConstraintViolation<Directory>> violations = validator.validate(dir);
        assertEquals(2, violations.size());

        String[] messages = {
            "The HOME folder is not allowed!",
            "The folder is invalid!"
        };

        for (ConstraintViolation<Directory> violation : violations) {
            assertTrue(Arrays.asList(messages).contains(violation.getMessage()));
        }
    }

    @Test
    public void testGetHash() throws IOException, UnsupportedEncodingException, NoSuchAlgorithmException
    {
        String[] elements = {OperationSystem.tempFolder(), ".kaduFolder"};
        String path = OperationSystem.concatenate(elements);
        new File(path).mkdirs();

        Directory dir = new Directory();
        dir.setPath(path);

        assertEquals("18650aa03d3edfd0bbbceb42579ebea1", dir.getHash());
    }
}
