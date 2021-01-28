package Files;

import java.util.List;

public interface ImpleFileDao {
	List<File> readAll();
	boolean writeAll(List<File> list);
	String generateId();
}
