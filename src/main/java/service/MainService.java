package service;

import entity.Limit;

import java.util.List;

public interface MainService {
    public List<Limit> findAllLimit(int empid);

}
