package service;

import entity.Emprole;

import java.util.List;

public interface EmproleService {
    public int addEmprole(Emprole emprole);
    public int subEmprole(Integer empid);
    public int updateEmprole(Emprole emprole);
    public List<Emprole> findAllEmprole();
    public Emprole findRoleid(Integer empid);
    public Emprole findEmproleById(Integer id);
    public List<Emprole> findEmproleForAdmin(Integer roleid);
}
