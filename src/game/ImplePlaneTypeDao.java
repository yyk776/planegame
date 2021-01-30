package game;

import java.util.List;

public interface ImplePlaneTypeDao{
	List<planetype> readAll();
	boolean writeAll(List<planetype> list);
}
