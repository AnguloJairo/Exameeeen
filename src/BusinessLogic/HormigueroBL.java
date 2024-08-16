
package BusinessLogic;

import DataAccess.DTO.HormigueroDTO;
import DataAccess.HormigueroDAO;
import java.util.List;

public class HormigueroBL {
    private HormigueroDTO ecDTO;
    private HormigueroDAO esDAO = new HormigueroDAO();


    public HormigueroBL() {}
    

    public List<HormigueroDTO> getAll() throws Exception{
        List<HormigueroDTO> lst = esDAO.readAll();
        return lst;
    }
    public HormigueroDTO getBy(int idReg) throws Exception{
        ecDTO = esDAO.readBy(idReg);
        return ecDTO;
    }
    public boolean add(HormigueroDTO regDTO) throws Exception{   
        return esDAO.create(regDTO);
    }
    public boolean update(HormigueroDTO regDTO) throws Exception{
        return esDAO.update(regDTO);
    }

    public boolean delete(int idReg) throws Exception {
        return esDAO.delete(idReg);
    }
    
    
    public Integer getRowCount() throws Exception{
        return esDAO.getRowCount();
    }
}
