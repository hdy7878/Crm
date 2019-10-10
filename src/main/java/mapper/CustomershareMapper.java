package mapper;

import entity.Customershare;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CustomershareMapper {
    public int insertCustomershare(Customershare customershare);
    public int subCustomershare(Integer id);
    public int updateCustomershare(Customershare customershare);
    public List<Customershare> selectCustomershare();
    public Customershare selectCustomershareById(Integer id);
    public List<Customershare> selectCustomershareByEmpId(Integer empid);
    public Customershare findCustomershareByTerm(@Param("empid") Integer empid, @Param("cid") Integer cid);

    /*通过模糊查询所有的访问记录*/
    public List<Customershare> findAllCustomershareBycusname(@Param("cusname") String cusname);
    /*通过模糊查询专员自己下面的访问记录*/
    public List<Customershare> findAllCustomershareByIdcusname(@Param("empid") Integer empid,@Param("cusname") String cusname);
    
    
}
