package BusinessLogic;

import DataAccess.DTO.RegistroDTO;
import DataAccess.RegistroDAO;
import java.util.List;

public class RegistroBL {
    private RegistroDAO rDao = new RegistroDAO();

    public RegistroBL() {}

    public List<RegistroDTO> getAll() throws Exception {
        List<RegistroDTO> lst = rDao.readAll();
        return lst;
    }

    public RegistroDTO getBy(int IdRegistro) throws Exception {
        return rDao.readBy(IdRegistro);
    }

    // public boolean add(RegistroDTO registroDTO) throws Exception {
    //     return rDao.create(registroDTO);
    // }
    public boolean add(RegistroDTO registroDTO) throws Exception {
        return rDao.create(registroDTO);
    }
    
    public boolean update(RegistroDTO registroDTO) throws Exception {
        return rDao.update(registroDTO);
    }

    public boolean delete(int IdRegistro) throws Exception {
        return rDao.delete(IdRegistro);
    }
}


