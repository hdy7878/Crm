package mapper;

import entity.Emprole;

import java.util.List;

public interface EmproleMapper {
    public List<Emprole> selectAllEmprole();
    public int insertEmprole(Emprole emprole);
    public int subEmprole(Integer empid);
    public int updateEmprole(Emprole emprole);
    public Emprole selectRoleid(Integer empid);
    public Emprole selectEmproleById(Integer id);
    public List<Emprole> selectEmproleForAdmin(Integer roleid);
}
