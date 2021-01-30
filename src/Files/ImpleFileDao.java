package Files;

import java.util.List;

public interface ImpleFileDao {
	List<Files> readAll();
	boolean writeAll(List<Files> list);
}
