package test.kadu.helper;

import com.kadu.helper.OperationSystem;
import static com.kadu.helper.OperationSystem.rootFolder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TestOperationSystem
{

    @Test
    public void testIsWindows()
    {
        boolean isWindowsLike = System.getProperty("os.name").trim().toLowerCase().startsWith("windows");
        assertEquals(isWindowsLike, OperationSystem.isWindows());
    }

    @Test
    public void testIsLinux()
    {
        boolean isWindowsLike = System.getProperty("os.name").trim().toLowerCase().startsWith("windows");
        assertNotEquals(isWindowsLike, OperationSystem.isLinux());
    }

    @Test
    public void testSeparator()
    {
        assertEquals("\\", OperationSystem.separator());
    }

    @Test
    public void testRootFolder()
    {
        if (OperationSystem.isWindows()) {
            assertEquals("C:\\", OperationSystem.rootFolder());
        } else {
            assertEquals("/", OperationSystem.rootFolder());
        }
    }

    @Test
    public void testTempFolder()
    {
        assertTrue(OperationSystem.tempFolder().startsWith(OperationSystem.rootFolder()));
    }

    @Test
    public void testHomeFolder()
    {
        String username = System.getProperty("user.name");
        assertTrue(OperationSystem.homeFolder().endsWith(username));
    }

    @Test
    public void testConcatenateValid()
    {
        String[] elements = {"C:\\first", "second", "third", "fourth"};
        String path = OperationSystem.concatenate(elements);
        assertEquals("C:\\first\\second\\third\\fourth", path);
    }

    @Test
    public void testConcatenateEmpty()
    {
        String[] elements = {};
        String path = OperationSystem.concatenate(elements);
        String root = rootFolder().substring(0, rootFolder().length() - 1) + OperationSystem.separator();
        assertEquals(root, path);
    }

    @Test
    public void testConcatenateNoRoot()
    {
        String[] elements = {"first", "second", "third", "fourth"};
        String path = OperationSystem.concatenate(elements);
        String root = rootFolder().substring(0, rootFolder().length() - 1);
        assertEquals("C:\\first\\second\\third\\fourth", path);
    }
}
