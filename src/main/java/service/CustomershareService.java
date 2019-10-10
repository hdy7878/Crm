package service;

import entity.Customershare;

import java.util.List;

public interface CustomershareService {
    public List<Customershare> findAllCustomershare();
    public int subCustomershare(Integer id);
    public int updateCustomershare(Customershare customershare);
    public int insertCustomershare(Customershare customershare);
    public Customershare findCustomershareById(Integer id);
    public List<Customershare> findCustomershareByEmpId(Integer empid);
	
    public Customershare findCustomershareByTerm(Integer empid, Integer cid);
    /*通过模糊查询所有的访问记录*/
    public List<Customershare> findAllCustomershareBycusname(String cusname);
    /*通过模糊查询专员自己下面的访问记录*/
    public List<Customershare> findAllCustomershareByIdcusname(Integer empid,String cusname);
}
