package mapper;

import entity.Limit;

import java.util.List;

public interface MainMapper {
    public List<Limit> findAllLimit(int empid);
}
